package com.semmyguiose.springbootapi.student;

import java.util.List;
import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
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
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        Boolean existStudent = studentRepository.existsById(id);
        if (!existStudent) {
            throw new IllegalStateException("student" + id + " not exist");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public Student updateStudent(Long id, Student student) {
        Boolean studentExist = studentRepository.existsById(id);

        if (studentExist) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            Student studentUpdate = studentRepository.findById(id).get();
            // Appliquez les modifications sur l'étudiant existant
            studentUpdate.setName(student.getName());
            studentUpdate.setDob(student.getDob());
            studentUpdate.setEmail(student.getEmail());
            // Appliquez d'autres modifications si nécessaire

            // Sauvegardez l'étudiant mis à jour dans la base de données
            // studentRepository.save(studentUpdate);
            return studentUpdate;
        } else {
            throw new IllegalStateException("student" + id + " not exist");
        }

    }

}
