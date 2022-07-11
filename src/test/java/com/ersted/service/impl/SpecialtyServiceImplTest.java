package com.ersted.service.impl;

import com.ersted.model.Specialty;
import com.ersted.repository.SpecialtyRepository;
import com.ersted.service.SpecialtyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class SpecialtyServiceImplTest {

    @Mock
    private SpecialtyRepository repository;
    private SpecialtyService service;

    @Before
    public void setUp(){
        this.service = new SpecialtyServiceImpl(repository);
    }

    @Test
    public void createSpecialtyWithoutIdThenReturnSpecialtyWithId() {
        Specialty newSpecialty = new Specialty(null,"test");
        Specialty expected = new Specialty(1L, "test");

        when(repository.create(newSpecialty))
                .thenReturn(new Specialty(1L, "test"));

        Specialty actual = service.create(newSpecialty);

        assertEquals(expected, actual);
    }

    @Test
    public void createSpecialtyWithRandomIdThenReturnSpecialtyWithId() {
        Specialty newSpecialty = new Specialty(999L,"test");
        Specialty expected = new Specialty(1L, "test");

        when(repository.create(newSpecialty))
                .thenReturn(new Specialty(1L, "test"));

        Specialty actual = service.create(newSpecialty);

        assertEquals(expected, actual);
    }

    @Test
    public void createSpecialtyWithAlreadyExistNameThenReturnNull(){
        Specialty newSpecialty = new Specialty(null,"test");
        Specialty expected = null;

        when(repository.create(newSpecialty))
                .thenReturn(null);

        Specialty actual = service.create(newSpecialty);

        assertEquals(expected, actual);
    }

    @Test
    public void getExistSpecialtyByIdThenReturnSpecialty(){
        Specialty existSpecialty = new Specialty(1L, "test");
        Specialty expected = new Specialty(1L, "test");

        when(repository.getById(1L))
                .thenReturn(existSpecialty);

        Specialty actual = service.getById(1L);

        assertEquals(expected, actual);

    }

    @Test
    public void getNotExistSpecialtyByIdThenReturnNull(){
        Specialty notExistSpecialty = null;
        Specialty expected = null;

        when(repository.getById(1L))
                .thenReturn(notExistSpecialty);

        Specialty actual = service.getById(1L);

        assertEquals(expected, actual);
    }

    @Test
    public void changeSpecialtyNameToNotExistThenReturnUpdatedSpecialty(){
        Specialty oldSpecialty = new Specialty(1L,"old");
        Specialty expected = new Specialty(1L,"updated");

        when(repository.update(oldSpecialty))
                .thenReturn(new Specialty(1L,"updated"));

        Specialty actual = service.update(oldSpecialty);

        assertEquals(expected, actual);
    }

    @Test
    public void changeSpecialtyNameToExistThenReturnNull(){
        Specialty oldSpecialty = new Specialty(1L,"old");
        Specialty expected = null;

        when(repository.update(oldSpecialty))
                .thenReturn(null);

        Specialty actual = service.update(oldSpecialty);

        assertEquals(expected, actual);
    }

    @Test
    public void changeNotExistSpecialtyThenReturnNull(){
        Specialty notExistSpecialty = new Specialty(1L,"old");
        Specialty expected = null;

        when(repository.update(notExistSpecialty))
                .thenReturn(null);

        Specialty actual = service.update(notExistSpecialty);

        assertEquals(expected, actual);
    }

    @Test
    public void whenListIsEmptyReturnEmptyList(){
        when(repository.getAll())
                .thenReturn(Collections.emptyList());
        List<Specialty> expected = Collections.emptyList();
        List<Specialty> actual = service.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void whenSpecialtyAddedThenReturnListSize1(){
        Specialty specialty = new Specialty(1L, "test");
        when(repository.getAll()).thenReturn(Collections.singletonList(specialty));

        List<Specialty> expected = Collections.singletonList(specialty);
        List<Specialty> actual = service.getAll();

        assertEquals(expected, actual);
    }
}