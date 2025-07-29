package com.amrit.springBoot.repo;

import com.amrit.springBoot.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
