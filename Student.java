package com.api.hiring.studenthiring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
// @NoArgsConstructor
@Builder
public class Student {
    private long id;
    private String name;
    private String phone;
    private String email;

    private String photoName;
    private String photoType;

    private String resumeName;
    private String resumeType;
}
