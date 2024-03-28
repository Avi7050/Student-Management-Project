package com.Springboot.Student.Management.Project.service;

import com.Springboot.Student.Management.Project.entity.Student;
import com.Springboot.Student.Management.Project.exception.StudentNotFoundException;
import com.Springboot.Student.Management.Project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private StudentRepository repository;
    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }
    @Override
    public Student save(Student student) {
        return repository.save(student);
    }
    @Override
    public List<Student> findALl() {
        List<Student> students = repository.findAll();
        return students;
    }
    @Override
    public Student findById(int id) {
        Optional<Student> result = repository.findById(id);

        Student student = null;
        if(result.isPresent()){
            student = result.get();
        }else{
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
        return student;
    }
    @Override
    @Transactional
    public Student update(Student student) {
       Optional<Student> result = repository.findById(student.getId());
       Student theStudent = null;
       if(result.isPresent()){
           theStudent = result.get();
           theStudent.setFirstName(student.getFirstName());
           theStudent.setLastName(student.getLastName());
           theStudent.setEmail(student.getEmail());
       }else{
           throw new StudentNotFoundException("Student not found with id: " + student.getId());
       }
       return repository.save(theStudent);
    }
    @Override
    public void deleteById(int id) {
        Optional<Student> result = repository.findById(id);
        Student student = null;
        if(result.isPresent()){
            student = result.get();
        }else{
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
