package com.amrit.springBoot.service;

import com.amrit.springBoot.entity.Student;

import java.util.List;

public interface StudentService {
    public Student saveStudent(Student student);
    public List<Student> getAllStudents();
    public void deleteStudent(Long id);
    public Student getByEmail(String email);
}
