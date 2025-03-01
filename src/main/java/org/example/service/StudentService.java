package org.example.service;

import org.example.model.Student;

import java.util.List;

public interface StudentService {
    void add(Student student);

    List<Student> get();

    Student get(long id);


    Student delete(long id);

    void update(Student student);
}
