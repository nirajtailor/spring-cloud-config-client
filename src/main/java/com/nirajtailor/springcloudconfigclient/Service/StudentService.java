package com.nirajtailor.springcloudconfigclient.Service;

import com.nirajtailor.springcloudconfigclient.Model.Response;
import com.nirajtailor.springcloudconfigclient.Model.Student;
import com.nirajtailor.springcloudconfigclient.Persistence.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    public Response getStudents(Long rollNumber, String name, Double cgpa) {
        List<Student> students = studentRepository.getAllStudents(rollNumber, name, cgpa);
        return new Response<>(students, HttpStatus.OK);
    }

    public Response getStudentByRollNumber(Long rollNumber) {
        if (rollNumber==null || rollNumber < 0) {
            return new Response("Invalid RollNumber", HttpStatus.BAD_REQUEST);
        }
        Student student = studentRepository.getStudentByRollNumber(rollNumber);
        return new Response(student, HttpStatus.OK);
    }
}
