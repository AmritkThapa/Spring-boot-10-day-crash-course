package com.amrit.springBoot.service.impl;

import com.amrit.springBoot.entity.Student;
import com.amrit.springBoot.exception.ConflictException;
import com.amrit.springBoot.exception.NotFoundException;
import com.amrit.springBoot.repo.StudentRepository;
import com.amrit.springBoot.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        Student studentByEmail = studentRepository.findByEmail(student.getEmail());
        if (studentByEmail != null){
            throw new ConflictException("Email already exists: " + student.getEmail());
        }
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()){
            throw new NotFoundException("Student with ID " + id + " not found");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public Student getByEmail(String email) {
        Student student = studentRepository.findByEmail(email);
        if (student!=null){
            return student;
        }
        throw new NotFoundException("Student with email " + email + " not found");
    }
}
