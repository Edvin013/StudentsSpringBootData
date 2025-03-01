package org.example.service;

import org.example.model.Auto;

import java.util.List;

public interface AutoService {
    void add(long idStudent ,Auto auto);

    List<Auto> get();

    Auto get(long id);

    Auto delete(long id);

    void update(Auto auto);
}
