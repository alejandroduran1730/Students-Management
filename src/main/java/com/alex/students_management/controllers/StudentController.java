package com.alex.students_management.controllers;

import com.alex.students_management.domain.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    // Resources list
    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Alejandro Duran", "alejandroduran@mail.com", 20, "Computer Science"),
            new Student(2, "Juan Lopez", "juanlopez@mail.com", 30, "Industrial Engineering"),
            new Student(3, "David Bastian", "davidbastian@mail.com", 20, "Business Management"),
            new Student(4, "Ernesto Cordoba", "ernestoc@mail.com", 27, "Mechanical Engineer")
    ));

    // Show all students endpoint
    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    // Show one student by email endpoint
    @GetMapping("/{email}")
    public Student getStudent(@PathVariable String email) {
        for (Student s : students) {
            if (s.getEmail().equalsIgnoreCase(email)) {
                return s;
            }
        }
        return null; // Returning a null is a bad practice, but for practice purposes I will leave it this way
    }

    // Create a new student endpoint
    @PostMapping
    public Student postStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    // Update one student (whole data)
    @PutMapping
    public Student putStudent(@RequestBody Student student) {
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                s.setName(student.getName());
                s.setEmail(student.getEmail());
                s.setAge(student.getAge());
                s.setCourse(student.getCourse());

                return s; // Returning a null is a bad practice, but for practice purposes I will leave it this way
            }
        }
        return null;
    }

    // Update one student (partial data)
    @PatchMapping
    public Student patchStudent(@RequestBody Student student) {
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                if (student.getName() != null) {
                    s.setName(student.getName());
                }
                if (student.getEmail() != null) {
                    s.setEmail(student.getEmail());
                }
                if (student.getAge() != 0) {
                    s.setAge(student.getAge());
                }

                if (student.getCourse() != null) {
                    s.setCourse(student.getCourse());
                }
                return s;
            }
        }
        return null; // Returning a null is a bad practice, but for practice purposes I will leave it this way
    }

    // Delete one student endpoint
    @DeleteMapping("/{id}")
    public Student deleteStudent(@PathVariable int id) {
        for (Student s: students) {
            if (s.getId() == id) {
                students.remove(s);

                return s;
            }
        }
        return null; // Returning a null is a bad practice, but for practice purposes I will leave it this way
    }
}
