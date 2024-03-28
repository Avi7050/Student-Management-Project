package com.Springboot.Student.Management.Project.rest;

import com.Springboot.Student.Management.Project.entity.Student;
import com.Springboot.Student.Management.Project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    private StudentService service;
    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }
    @PostMapping("/students")
    public Student save(@RequestBody Student student){
        student.setId(0);
        return service.save(student);
    }
    @GetMapping("/students")
    public List<Student> findAll(){
        List<Student> students = service.findALl();
        return students;
    }
    @GetMapping("/students/{id}")
    public Student findById(@PathVariable int id){
        return service.findById(id);
    }
    @PutMapping("/students")
    public Student update(@RequestBody Student student){
        return service.update(student);
    }
    @DeleteMapping("/students/{id}")
    public String deleteById( @PathVariable int id){
        service.deleteById(id);
        return "Student deleted with id: " +id;
    }
    @DeleteMapping("/students")
    public String deleteAll(){
        service.deleteAll();
        return "All Students deleted ";
    }

}
