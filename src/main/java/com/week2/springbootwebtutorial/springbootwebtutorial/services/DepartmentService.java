package com.week2.springbootwebtutorial.springbootwebtutorial.services;

import com.week2.springbootwebtutorial.springbootwebtutorial.dto.DepartmentDTO;
import com.week2.springbootwebtutorial.springbootwebtutorial.entities.DepartmentEntity;
import com.week2.springbootwebtutorial.springbootwebtutorial.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<DepartmentDTO> getDeparmentById(Long deparmentId) {
        return departmentRepository
                .findById(deparmentId)
                .map(departmentEntity -> modelMapper
                        .map(departmentEntity,DepartmentDTO.class));
    }

    public List<DepartmentDTO> getAllDeparments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(departmentEntity -> modelMapper
                        .map(departmentEntity,DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity toSaveDepartment = modelMapper.map(departmentDTO,DepartmentEntity.class);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(toSaveDepartment);
        return modelMapper
                .map(savedDepartmentEntity,DepartmentDTO.class);

    }
    public boolean isExitsByDepartmentId(Long departmentId){
        return departmentRepository.existsById(departmentId);
    }
    public boolean deleteDepartmentById(Long departmentId) {
        boolean exits = isExitsByDepartmentId(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }

    public DepartmentDTO updateDepartmentById(Long departmentId, DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO,DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(departmentEntity);
        return modelMapper
                .map(savedDepartmentEntity,DepartmentDTO.class);
    }

    public DepartmentDTO updatePartialDepartmentById(Long departmentId, Map<String, Object> updates) {
        boolean exits = isExitsByDepartmentId(departmentId);
        if(!exits ) return  null;
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).get();
        updates.forEach((field,value) ->{
            Field feildtoUpdated = ReflectionUtils.findRequiredField(DepartmentEntity.class,field);
            feildtoUpdated.setAccessible(true);
            ReflectionUtils.setField(feildtoUpdated,departmentEntity,value);
        });
        return modelMapper
                .map(departmentRepository.save(departmentEntity),DepartmentDTO.class);
    }
}
