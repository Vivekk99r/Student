package com.api.hiring.studenthiring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Builder
@Table(name = "student_data")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String phone;
    private String email;

    private String photoName;
    private String photoType;

    @Lob
    private byte[] photoData;

    private String resumeName;
    private String resumeType;

    @Lob
    private byte[] resumeData;
}
