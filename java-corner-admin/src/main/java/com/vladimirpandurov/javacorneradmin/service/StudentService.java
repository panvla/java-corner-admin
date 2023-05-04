package com.vladimirpandurov.javacorneradmin.service;

import com.vladimirpandurov.javacorneradmin.dto.StudentDTO;
import com.vladimirpandurov.javacorneradmin.entity.Student;
import org.springframework.data.domain.Page;

public interface StudentService {

    Student loadStudentById(Long studentId);

    Page<StudentDTO> loadStudentsByName(String name, int page, int size);

    StudentDTO loadStudentByEmail(String email);

    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO updateStudent(StudentDTO studentDTO);

    void removeStudent(Long studentId);
}
