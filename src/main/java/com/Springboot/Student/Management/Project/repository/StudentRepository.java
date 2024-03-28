package com.Springboot.Student.Management.Project.repository;

import com.Springboot.Student.Management.Project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
