package org.example.service;

import org.example.model.Auto;
import org.example.model.Student;
import org.example.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoServiceImpl implements AutoService {
    private AutoRepository autoRepository;
    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setAutoRepository(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    @Override
    public void add(long idStudent, Auto auto) {
        try {
            Student student = studentService.get(idStudent);
            auto.setStudent(student);
            this.autoRepository.save(auto);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Авто уже есть в базе!");
        }
    }

    @Override
    public List<Auto> get() {
        return this.autoRepository.findAll();
    }

    @Override
    public Auto get(long id) {
        return this.autoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Авто не найден!"));
    }

    @Override
    public Auto delete(long id) {
        Auto auto = this.get(id);
        this.autoRepository.deleteById(id);
        return auto;
    }

    @Override
    public void update(Auto auto) {
        try {
            Auto old = this.get(auto.getId());
            old.setBrand(auto.getBrand());
            old.setPower(auto.getPower());
            old.setYear(auto.getYear());
            this.autoRepository.save(old);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Авто уже есть в базе!");
        }
    }
}
