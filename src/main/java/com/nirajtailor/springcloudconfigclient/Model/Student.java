package com.nirajtailor.springcloudconfigclient.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
    private int id;
    private String name;
    private Long rollNumber;
    private Double cgpa;
}
