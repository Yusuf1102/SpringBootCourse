package com.week2.springbootwebtutorial.springbootwebtutorial.controllers;

import com.week2.springbootwebtutorial.springbootwebtutorial.dto.DepartmentDTO;
import com.week2.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.week2.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.week2.springbootwebtutorial.springbootwebtutorial.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
        private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/departmentId")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable @Valid Long deparmentId){
        Optional <DepartmentDTO> departmentDTO = departmentService.getDeparmentById(deparmentId);
        return departmentDTO
                .map(departmentDTO1 -> ResponseEntity.ok(departmentDTO1))
                .orElseThrow(()->new ResourceNotFoundException("Deparment not found"));

    }
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDeparments(@RequestParam (required = false) @Valid String title){
        return ResponseEntity.ok(departmentService.getAllDeparments());
    }
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO){
        DepartmentDTO savedDeparment = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(savedDeparment, HttpStatus.CREATED);

    }
    @PutMapping(path = "/departmentId")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@PathVariable @Valid Long departmentId, @RequestBody DepartmentDTO departmentDTO){
        return ResponseEntity.ok(departmentService.updateDepartmentById(departmentId,departmentDTO));

    }



    @DeleteMapping(path = "/departmentId")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable @Valid Long departmentId){
        boolean gotDeleted = departmentService.deleteDepartmentById(departmentId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();

    }
    @PatchMapping(path = "/departmentId")
    public  ResponseEntity<DepartmentDTO>  updatePartialDepartmentById(@PathVariable @Valid Long departmentId,
                                                                     @RequestBody Map<String,Object> updates){
        DepartmentDTO departmentDTO = departmentService.updatePartialDepartmentById(departmentId,updates);
        if(departmentDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(departmentDTO);

    }
}
