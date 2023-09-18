package com.semmyguiose.springbootapi.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    // @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getstudents() {

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<List<Student>> entity = new ResponseEntity<>(studentService.getStudent(), headers,
                HttpStatus.OK);

        return entity;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getstudent(@PathVariable String id) {

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Object> entity;
        Optional<Student> student = Optional.empty();

        if (studentService.getStudent() != null && id != null) {
            student = studentService.getStudent().stream()
                    .filter(Objects::nonNull)
                    .filter(x -> x.getId() != null && x.getId().equals(Long.parseLong(id)))
                    .findFirst();
        }
        entity = new ResponseEntity<>(student.isPresent() ? student.get() : List.of(), headers, HttpStatus.OK);

        return entity;

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable String id, @RequestBody Student requestStudent) {

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Object> entity;
        Student student = null;

        student = studentService.updateStudent(Long.parseLong(id), requestStudent);

        entity = new ResponseEntity<>(student, headers, HttpStatus.OK);

        return entity;

    }

    @PostMapping()
    public ResponseEntity<Object> createstudent(@RequestBody Student requestStudent) {

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Object> entity;
        Optional<Student> student = Optional.empty();
        student = Optional.of(studentService.createStudent(requestStudent));
        entity = new ResponseEntity<>(student.isPresent() ? student.get() : List.of(), headers, HttpStatus.OK);

        return entity;

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> createstudent(@PathVariable String id) {

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Object> entity;
        Student student = null;
        studentService.deleteStudent(Long.parseLong(id));
        student = studentService.getStudent(Long.parseLong(id));
        entity = new ResponseEntity<>(student, headers, HttpStatus.OK);

        return entity;

    }
}
