package com.amrit.springBoot.controller;

import com.amrit.springBoot.entity.TestStudent;
import com.amrit.springBoot.exception.ConflictException;
import com.amrit.springBoot.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/studentsTest")
public class StudentTestController {
    private final List<TestStudent> testStudents = new ArrayList<>();

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
    public TestStudent addStudent(@RequestBody TestStudent testStudent) {
        testStudent.setId((long) (testStudents.size() + 1));
        for (TestStudent s : testStudents) {
            if (s.getEmail().equals(testStudent.getEmail())) {
                throw new ConflictException("Email already exists: " + testStudent.getEmail());
            }
        }
        testStudents.add(testStudent);
        return testStudent;
    }

    @GetMapping("/all")
    public List<TestStudent> getAllStudents() {
        if (testStudents.isEmpty()){
            throw new NotFoundException("No students found");
        }
        return testStudents;
    }

    @PutMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody TestStudent updatedTestStudent) {
        for (TestStudent testStudent : testStudents) {
            if (testStudent.getId().equals(id)) {
                testStudent.setName(updatedTestStudent.getName());
                testStudent.setEmail(updatedTestStudent.getEmail());
                return "Student with ID " + id + " updated successfully. Updated Name: " + updatedTestStudent.getName() + ", Updated Email: " + updatedTestStudent.getEmail();
            }
        }
        throw new NotFoundException("Student with ID " + id + " not found");
    }

    @PatchMapping("/patchUpdate/{id}")  //PutMapping can also be used here
    public String updateStudentEmail(@PathVariable Long id, @RequestParam String email) {
        for (TestStudent s : testStudents) {
            if (s.getId().equals(id)) {
                s.setEmail(email);
                return "Student with ID " + id + " updated successfully."+"Updated Email: " + s.getEmail();

            }
        }
        throw new NotFoundException("Student with ID " + id + " not found");
    }


    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        for (TestStudent testStudent : testStudents) {
            if (testStudent.getId().equals(id)) {
                testStudents.remove(testStudent);
                return "Student with ID " + id + " deleted successfully.";
            }
        }
        throw new NotFoundException("Student with ID " + id + " not found");
    }
}
