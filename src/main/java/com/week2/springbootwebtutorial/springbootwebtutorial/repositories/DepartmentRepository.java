package com.week2.springbootwebtutorial.springbootwebtutorial.repositories;

import com.week2.springbootwebtutorial.springbootwebtutorial.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {

}
