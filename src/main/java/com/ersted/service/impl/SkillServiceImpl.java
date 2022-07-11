package com.ersted.service.impl;

import com.ersted.model.Skill;
import com.ersted.repository.SkillRepository;
import com.ersted.service.SkillService;

import java.util.List;

public class SkillServiceImpl implements SkillService {
    private final SkillRepository repository;

    public SkillServiceImpl(SkillRepository repository) {
        this.repository = repository;
    }

    @Override
    public Skill create(Skill obj) {
        return repository.create(obj);
    }

    @Override
    public Skill getById(Long id) {
        return repository.getById(id);
    }


    @Override
    public Skill update(Skill obj) {
        return repository.update(obj);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Skill> getAll() {
        return repository.getAll();
    }
}
