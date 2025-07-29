package com.amrit.springBoot.repo;

import com.amrit.springBoot.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.name LIKE %:keyword%")
    Page<Student> searchByName(String keyword, Pageable pageable);
}
