package com.amrit.springBoot.controller;

import com.amrit.springBoot.entity.Student;
import com.amrit.springBoot.service.FileUploadService;
import com.amrit.springBoot.service.StudentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/students/file")
public class FileUploadController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Student createStudent(@RequestPart("file") MultipartFile file,
                                 @RequestPart("student") Student student) throws IOException {
        // Upload the file and get the path
        String filePath = fileUploadService.uploadFile(file);

        // Set the file path (pic) in the Student entity
        student.setFile(filePath);

        // Save the student (with the file path)
        return studentService.saveStudent(student);
    }

    @GetMapping("/all")
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }
}
