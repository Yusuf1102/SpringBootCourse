package com.week2.springbootwebtutorial.springbootwebtutorial.services;

import com.week2.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.week2.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.week2.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.week2.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;

import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private  final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//       Optional <EmployeeEntity> employeeEntity = employeeRepository.findById(id);
//        return modelMapper.map(employeeEntity,EmployeeDTO.class);
        return employeeRepository
                .findById(id).
                map(employeeEntity -> modelMapper
                        .map(employeeEntity,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
       return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity toSaveEntity  = modelMapper.map(employeeDTO,EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return  modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        isExitsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity , EmployeeDTO.class);

    }
    public boolean isExitsByEmployeeId(Long employeeId){
        boolean exits = employeeRepository.existsById(employeeId);
        if (!exits) throw new ResourceNotFoundException("Employee not found with id : "+employeeId);
      return  true;
}

    public boolean deleteEmployeeById(Long employeeId) {
         isExitsByEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        isExitsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field,value) ->{
           Field fieldtoBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
            fieldtoBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldtoBeUpdated,employeeEntity,value);
        });
        return  modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }
}
