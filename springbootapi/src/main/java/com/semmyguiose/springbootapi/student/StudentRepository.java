package com.semmyguiose.springbootapi.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // deux solutions

    @Query("SELECT s from Student s  where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    // ou Optional<Student> findStudentByEmail(String email);
}
