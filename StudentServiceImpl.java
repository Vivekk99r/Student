package com.api.hiring.studenthiring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.hiring.studenthiring.entity.StudentEntity;
import com.api.hiring.studenthiring.model.Student;
import com.api.hiring.studenthiring.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentEntity saveStudentData(String name, String phone, String email, MultipartFile photo,
            MultipartFile resume) throws Exception {
        String photoName = photo.getOriginalFilename();
        String photoType = photo.getContentType();
        byte[] photoData = photo.getBytes();

        String resumeName = resume.getOriginalFilename();
        String resumeType = resume.getContentType();
        byte[] resumeData = resume.getBytes();

        StudentEntity studentEntity = StudentEntity.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .photoName(photoName)
                .photoType(photoType)
                .photoData(photoData)
                .resumeName(resumeName)
                .resumeType(resumeType)
                .resumeData(resumeData)
                .build();
        return studentRepository.save(studentEntity);
    }

    // @Override
    // public Optional<Student> getStudentMetadata(Long studentId) {
        
    // }

    @Override
    public Optional<StudentEntity> getStudent(Long studentId) {
        return studentRepository.findById(studentId);
    }


    
}
