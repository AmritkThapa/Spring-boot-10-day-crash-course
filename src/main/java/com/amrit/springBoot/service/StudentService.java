package com.amrit.springBoot.service;

import com.amrit.springBoot.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    public Student saveStudent(Student student);
    public List<Student> getAllStudents();
    public void deleteStudent(Long id);
    public Student getByEmail(String email);
    public Page<Student> searchStudents(String keyword, Pageable pageable);
}
