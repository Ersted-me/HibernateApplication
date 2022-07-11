package com.ersted.service.impl;

import com.ersted.model.Skill;
import com.ersted.repository.SkillRepository;
import com.ersted.service.SkillService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceImplTest {

    @Mock
    private SkillRepository repository;
    private SkillService service;

    @Before
    public void setUp(){
        this.service = new SkillServiceImpl(repository);
    }

    @Test
    public void createSkillWithoutIdThenReturnSkillWithId() {
        Skill newSkill = new Skill(null,"test");
        Skill expected = new Skill(1L, "test");

        when(repository.create(newSkill))
                .thenReturn(new Skill(1L, "test"));

        Skill actual = service.create(newSkill);

        assertEquals(expected, actual);
    }

    @Test
    public void createSkillWithRandomIdThenReturnSkillWithId() {
        Skill newSkill = new Skill(999L,"test");
        Skill expected = new Skill(1L, "test");

        when(repository.create(newSkill))
                .thenReturn(new Skill(1L, "test"));

        Skill actual = service.create(newSkill);

        assertEquals(expected, actual);
    }

    @Test
    public void createSkillWithAlreadyExistNameThenReturnNull(){
        Skill newSkill = new Skill(null,"test");
        Skill expected = null;

        when(repository.create(newSkill))
                .thenReturn(null);

        Skill actual = service.create(newSkill);

        assertEquals(expected, actual);
    }

    @Test
    public void getExistSkillByIdThenReturnSkill(){
        Skill existSkill = new Skill(1L, "test");
        Skill expected = new Skill(1L, "test");

        when(repository.getById(1L))
                .thenReturn(existSkill);

        Skill actual = service.getById(1L);

        assertEquals(expected, actual);

    }

    @Test
    public void getNotExistSkillByIdThenReturnNull(){
        Skill notExistSkill = null;
        Skill expected = null;

        when(repository.getById(1L))
                .thenReturn(notExistSkill);

        Skill actual = service.getById(1L);

        assertEquals(expected, actual);
    }

    @Test
    public void changeSkillNameToNotExistThenReturnUpdatedSkill(){
        Skill oldSkill = new Skill(1L,"old");
        Skill expected = new Skill(1L,"updated");

        when(repository.update(oldSkill))
                .thenReturn(new Skill(1L,"updated"));

        Skill actual = service.update(oldSkill);

        assertEquals(expected, actual);
    }

    @Test
    public void changeSkillNameToExistThenReturnNull(){
        Skill oldSkill = new Skill(1L,"old");
        Skill expected = null;

        when(repository.update(oldSkill))
                .thenReturn(null);

        Skill actual = service.update(oldSkill);

        assertEquals(expected, actual);
    }

    @Test
    public void changeNotExistSkillThenReturnNull(){
        Skill notExistSkill = new Skill(1L,"old");
        Skill expected = null;

        when(repository.update(notExistSkill))
                .thenReturn(null);

        Skill actual = service.update(notExistSkill);

        assertEquals(expected, actual);
    }

    @Test
    public void whenListIsEmptyReturnEmptyList(){
        when(repository.getAll())
                .thenReturn(Collections.emptyList());
        List<Skill> expected = Collections.emptyList();
        List<Skill> actual = service.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void whenSkillAddedThenReturnListSize1(){
        Skill skill = new Skill(1L, "test");
        when(repository.getAll()).thenReturn(Collections.singletonList(skill));

        List<Skill> expected = Collections.singletonList(skill);
        List<Skill> actual = service.getAll();

        assertEquals(expected, actual);
    }
}