package com.ersted.controller.impl;

import com.ersted.controller.SpecialtyController;
import com.ersted.model.Specialty;
import com.ersted.service.SpecialtyService;

import java.util.List;

public class SpecialtyControllerImpl implements SpecialtyController {
    private final SpecialtyService service;

    public SpecialtyControllerImpl(SpecialtyService service) {
        this.service = service;
    }

    @Override
    public Specialty create(Specialty obj) {
        return service.create(obj);
    }

    @Override
    public Specialty getById(Long id) {
        return service.getById(id);
    }

    @Override
    public Specialty update(Specialty obj) {
        return service.update(obj);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteById(id);
    }

    @Override
    public List<Specialty> getAll() {
        return service.getAll();
    }
}
