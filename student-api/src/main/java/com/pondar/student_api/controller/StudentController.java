package com.pondar.student_api.controller;

import com.pondar.student_api.model.Student;
import com.pondar.student_api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Empty student list");
        }else {
            return ResponseEntity.ok(students);
        }
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);

        if (student.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with id " + id + " not found");
        }else {
            return ResponseEntity.ok(student.get());
        }
    }
    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {

        if(student.getName().isEmpty() || student.getCourse().isNull()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name and course are required");
        }else {
            Student newStudent = studentService.addStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
        }

    }
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);

        if (updatedStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with id " + id + " not found");
        }
        return ResponseEntity.ok(updatedStudent);
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with id " + id + " not found");
        }
        return ResponseEntity.ok("Student with id " + id + " deleted successfully");
    }
}