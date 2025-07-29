package com.amrit.springBoot.service.impl;

import com.amrit.springBoot.entity.Subject;
import com.amrit.springBoot.entity.Student;
import com.amrit.springBoot.exception.ConflictException;
import com.amrit.springBoot.exception.NotFoundException;
import com.amrit.springBoot.repo.SubjectRepository;
import com.amrit.springBoot.repo.StudentRepository;
import com.amrit.springBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Student saveStudent(Student student) {
        Student studentByEmail = studentRepository.findByEmail(student.getEmail());
        if (studentByEmail != null){
            throw new ConflictException("Email already exists: " + student.getEmail());
        }
        Student savedStudent =  studentRepository.save(student);
        List<Subject> subject = student.getSubjects();
        for (Subject s : subject) {
            s.setStudent(savedStudent);
        }
        subjectRepository.saveAll(subject);
        return savedStudent;
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

    @Override
    public Page<Student> searchStudents(String keyword, Pageable pageable) {
        return studentRepository.searchByName(keyword, pageable);
    }
}
