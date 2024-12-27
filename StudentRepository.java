package com.api.hiring.studenthiring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hiring.studenthiring.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    
}
