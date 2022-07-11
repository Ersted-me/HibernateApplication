package com.ersted.service.impl;

import com.ersted.model.Developer;
import com.ersted.model.Status;
import com.ersted.repository.DeveloperRepository;
import com.ersted.service.DeveloperService;

import java.util.List;
import java.util.stream.Collectors;

public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository repository;

    public DeveloperServiceImpl(DeveloperRepository repository) {
        this.repository = repository;
    }

    @Override
    public Developer create(Developer obj) {
        return repository.create(obj);
    }

    @Override
    public Developer getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Developer update(Developer obj) {
        return repository.update(obj);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Developer> getAll() {
        return repository.getAll().stream()
                .filter(dev -> dev.getStatus().equals(Status.ACTIVE))
                .collect(Collectors.toList());
    }
}
