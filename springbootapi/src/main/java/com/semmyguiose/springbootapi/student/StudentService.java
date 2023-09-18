package com.semmyguiose.springbootapi.student;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // @Autowired
    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Transactional
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public Student updateStudent(Student student) {
        Student studentUpdate = studentRepository.findById(student.getId()).orElse(null);
        if (studentUpdate != null) {
            // Appliquez les modifications sur l'étudiant existant
            studentUpdate.setName(studentUpdate.getName());
            studentUpdate.setDob(studentUpdate.getDob());
            studentUpdate.setEmail(studentUpdate.getEmail());
            // Appliquez d'autres modifications si nécessaire

            // Sauvegardez l'étudiant mis à jour dans la base de données
            return studentRepository.save(studentUpdate);
        }
        return studentUpdate;
    }

}
