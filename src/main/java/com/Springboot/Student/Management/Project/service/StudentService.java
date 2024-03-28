package com.Springboot.Student.Management.Project.service;
import com.Springboot.Student.Management.Project.entity.Student;
import java.util.List;
public interface StudentService {
    Student save(Student student);
    List<Student> findALl();
    Student findById(int id);
    Student update(Student student);
    void deleteById(int id);
    void deleteAll();
}
