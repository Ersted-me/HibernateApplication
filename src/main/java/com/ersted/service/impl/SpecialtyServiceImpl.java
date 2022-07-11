package com.ersted.service.impl;

import com.ersted.model.Specialty;
import com.ersted.repository.SpecialtyRepository;
import com.ersted.service.SpecialtyService;

import java.util.List;

public class SpecialtyServiceImpl implements SpecialtyService {
    private final SpecialtyRepository repository;

    public SpecialtyServiceImpl(SpecialtyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Specialty create(Specialty obj) {
        return repository.create(obj);
    }

    @Override
    public Specialty getById(Long id) {
        return repository.getById(id);
    }


    @Override
    public Specialty update(Specialty obj) {
        return repository.update(obj);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Specialty> getAll() {
        return repository.getAll();
    }
}
