package com.anitha.question2_student_api.controller.student;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anitha.question2_student_api.models.student.Student;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    private List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(1L, "Alice", "Smith", "alice@edu.com", "Computer Science", 3.8));
        students.add(new Student(2L, "Bob", "Jones", "bob@edu.com", "Mathematics", 3.2));
        students.add(new Student(3L, "Charlie", "Brown", "charlie@edu.com", "Computer Science", 3.5));
        students.add(new Student(4L, "Diana", "Prince", "diana@edu.com", "Physics", 3.9));
        students.add(new Student(5L, "Ethan", "Hunt", "ethan@edu.com", "Business", 2.8));
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return students;
    }

    @GetMapping("/{studentsId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId){
        return students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst()
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/major/{major}")
    public List<Student> getStudentByMajor(@PathVariable String major){
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .collect(Collectors.toList());
    }

    @GetMapping("/filter")
    public List<Student> filterByGpa(@RequestParam Double gpa) {
        return students.stream()
                .filter(s -> s.getGpa() >= gpa)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        students.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(studentId)) {
                updatedStudent.setStudentId(studentId);
                students.set(i, updatedStudent);
                return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
