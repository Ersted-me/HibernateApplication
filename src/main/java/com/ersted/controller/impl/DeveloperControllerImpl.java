package com.ersted.controller.impl;

import com.ersted.controller.DeveloperController;
import com.ersted.model.Developer;
import com.ersted.service.DeveloperService;

import java.util.List;

public class DeveloperControllerImpl implements DeveloperController {
    private final DeveloperService service;

    public DeveloperControllerImpl(DeveloperService service) {
        this.service = service;
    }

    @Override
    public Developer create(Developer obj) {
        return service.create(obj);
    }

    @Override
    public Developer getById(Long id) {
        return service.getById(id);
    }

    @Override
    public Developer update(Developer obj) {
        return service.update(obj);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteById(id);
    }

    @Override
    public List<Developer> getAll() {
        return service.getAll();
    }
}
