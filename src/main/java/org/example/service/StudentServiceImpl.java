package org.example.service;

import org.example.model.Student;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void add(Student student) {
        try {
            this.studentRepository.save(student);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Студент уже есть в базе!");
        }
    }

    @Override
    public List<Student> get() {
        return this.studentRepository.findAll();
    }

    @Override
    public Student get(long id) {
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Студент не найден!"));
    }

    @Override
    public Student delete(long id){
        Student student = this.get(id);
        this.studentRepository.deleteById(id);
        return student;
    }

    @Override
    public void update(Student student) {
        try {
            Student old = this.get(student.getId());
            old.setNum(student.getNum());
            old.setFio(student.getFio());
            old.setAge(student.getAge());
            old.setSalary(student.getSalary());
            this.studentRepository.save(old);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Студент уже есть в базе!");
        }
    }
}
