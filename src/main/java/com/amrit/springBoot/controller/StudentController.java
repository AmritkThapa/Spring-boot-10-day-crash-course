package com.amrit.springBoot.controller;

import com.amrit.springBoot.entity.Student;
import com.amrit.springBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/all")
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Deleted student with ID: " + id;
    }

    @GetMapping("/email/{email}")
    public Student getStudentByEmail(@PathVariable String email) {
        return studentService.getByEmail(email);
    }

    @GetMapping("/search")
    public Page<Student> searchStudents(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        return studentService.searchStudents(keyword,
                PageRequest.of(page, size, Sort.by(sortBy)));
    }
}
