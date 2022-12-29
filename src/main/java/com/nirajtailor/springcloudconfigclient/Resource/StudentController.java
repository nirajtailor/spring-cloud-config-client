package com.nirajtailor.springcloudconfigclient.Resource;

import com.nirajtailor.springcloudconfigclient.Model.Response;
import com.nirajtailor.springcloudconfigclient.Model.Student;
import com.nirajtailor.springcloudconfigclient.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/getAll",
    method = RequestMethod.GET)
    public ResponseEntity<Object> getStudents(
            @RequestHeader(value = "version", required=false) String version,
            @RequestHeader(value = "source", required=false) String source,
            @RequestParam(value = "rollNumber", required = false) Long rollNumber,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "cgpa", required = false) Double cgpa
    ){
        Response response = studentService.getStudents(rollNumber, name, cgpa);
        return new ResponseEntity<>(response.getBody(), response.getStatus());
    }

    @GetMapping(value = "{rollNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getById(
            @PathVariable Long rollNumber,
            @RequestHeader(value = "version", required=false) String version,
            @RequestHeader(value = "source", required=false) String source
    ){
        Response response = studentService.getStudentByRollNumber(rollNumber);
        return new ResponseEntity<>(response.getBody(), response.getStatus());
    }

}
