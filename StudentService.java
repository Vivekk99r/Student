package com.api.hiring.studenthiring.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.api.hiring.studenthiring.entity.StudentEntity;
import com.api.hiring.studenthiring.model.Student;

public interface StudentService {
    public StudentEntity saveStudentData(String name, String phone, String email, MultipartFile photo, MultipartFile resume) throws Exception;

    // Optional<Student> getStudentMetadata(Long studentId);

    Optional<StudentEntity> getStudent(Long studentId);
}
