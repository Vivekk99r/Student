package com.api.hiring.studenthiring.controller;

// import java.net.http.HttpHeaders;
import java.util.Optional;
import java.io.IOException;

// import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.hiring.studenthiring.entity.StudentEntity;
import com.api.hiring.studenthiring.model.Student;
import com.api.hiring.studenthiring.service.StudentService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;



@RestController
public class StudentController {
    

    @Autowired
    private StudentService studentService;

    // Upload Details
    @PostMapping("/students/upload")
    public String uploadStudentDetails(
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("resume") MultipartFile resume
        ) {
            try{ 
                StudentEntity savedStudent = studentService.saveStudentData(name, phone, email, photo, resume);
                return "Student details uploaded successfully with ID: " + savedStudent.getId();
            }
            catch(Exception e) {
                return "Files upload failed: " + e.getMessage();
            }
    }

    //Download Photo
    @GetMapping("/students/{id}/photo")
    public String dowanloadPhoto(@PathVariable Long id, HttpServletResponse response) {
        Optional<StudentEntity> studentEntityOpt = studentService.getStudent(id);
        if(studentEntityOpt.isPresent()) {
            StudentEntity studentEntity = studentEntityOpt.get();
            try {
                response.setContentType(MediaType.parseMediaType(studentEntity.getPhotoType()).toString());
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, 
                            "attachment; filename=\"" + studentEntity.getPhotoName() + "\"");
                response.getOutputStream().write(studentEntity.getPhotoData());
                response.getOutputStream().flush();
                return "Photo Download started";
            } catch (IOException e) {
                return "Error while downloading the photo: " + e.getMessage();
            }
        }
        else {
            return "Photo not found.";
        }
    }

    // Download Resume
    @GetMapping("/students/{id}/resume")
    public String downloadResume(@PathVariable Long id, HttpServletResponse response) {
        Optional<StudentEntity> studentEntityOpt = studentService.getStudent(id);
        if(studentEntityOpt.isPresent()) {
            StudentEntity studentEntity = studentEntityOpt.get();
            try {
                response.setContentType(MediaType.parseMediaType(studentEntity.getResumeType()).toString());
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, 
                            "attachment; filename=\"" + studentEntity.getResumeName() + "\"");
                response.getOutputStream().write(studentEntity.getResumeData());
                response.getOutputStream().flush();
                return "Resume Download started";
            } catch (IOException e) {
                return "Error while downloading the photo: " + e.getMessage();
            }
        }
        else {
            return "Resume not found.";
        }
    }

    // Metadata
    // @GetMapping("/students/id/metadata")
    // public String getMethodName(@PathVariable Long id) {
    //     Optional<Student> studentMetadata = studentService.getStudentMetadata(id);
    //     if (studentMetadata.isPresent()) {
    //         Student metadata = studentMetadata.get();
    //         return "Student Metadata - Id: " + metadata.getId() + ", Name: " + metadata.getName() + ", Phone: " + metadata.getPhone()+ ", Email: " + metadata.getEmail() + ", Photo Name: " + metadata.getPhotoName() +
    //                ", Resume Name: " + metadata.getResumeName();
    //     } else {
    //         return "File metadata not found.";
    //     }
    // }
    
    
    
    
}
