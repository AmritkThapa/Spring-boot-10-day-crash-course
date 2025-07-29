package com.amrit.springBoot.controller;

import com.amrit.springBoot.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final List<Student> students = new ArrayList<>();

    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Hello " + name + "! Welcome to the Student API!";
    }

    @GetMapping("/greet/{name}")
    public String  greetWithPath(
            @PathVariable String name) {
        return "Hello " + name + "! Welcome to the Student API!";
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        student.setId((long) (students.size() + 1));
        for (Student s : students) {
            if (s.getEmail().equals(student.getEmail())) {
                return null;
            }
        }
        students.add(student);
        return student;
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return students;
    }

    @PutMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                student.setName(updatedStudent.getName());
                student.setEmail(updatedStudent.getEmail());
                return "Student with ID " + id + " updated successfully. Updated Name: " + updatedStudent.getName() + ", Updated Email: " + updatedStudent.getEmail();
            }
        }
        return "Student with ID " + id + " not found";
    }

    @PatchMapping("/patchUpdate/{id}")  //PutMapping can also be used here
    public String updateStudentEmail(@PathVariable Long id, @RequestParam String email) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                s.setEmail(email);
                return "Student with ID " + id + " updated successfully."+"Updated Email: " + s.getEmail();

            }
        }
        return "Student with ID " + id + " not found.";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                students.remove(student);
                return "Student with ID " + id + " deleted successfully.";
            }
        }
        return "Student with ID " + id + " not found.";
    }
}
