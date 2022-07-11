package com.ersted.service.impl;

import com.ersted.model.Developer;
import com.ersted.model.Specialty;
import com.ersted.model.Status;
import com.ersted.repository.DeveloperRepository;
import com.ersted.service.DeveloperService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.OptimisticLockException;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeveloperServiceImplTest {

    @Mock
    private DeveloperRepository repository;
    private DeveloperService service;

    @Before
    public void setUp(){
        this.service = new DeveloperServiceImpl(repository);
    }


    @Test
    public void createDeveloperWithoutIdThenReturnWithId() {
        Developer developer = new Developer();

        Developer expected = new Developer();
        expected.setId(1L);

        when(repository.create(developer)).thenReturn(expected);

        Developer actual = service.create(developer);

        assertEquals(expected, actual);
    }

    @Test
    public void createDeveloperWithWrongIdThenReturnDeveloperWithRightId(){
        Developer developer = new Developer();
        developer.setId(999L);

        Developer expected = new Developer();
        expected.setId(1L);

        when(repository.create(developer)).thenReturn(expected);

        Developer actual = service.create(developer);

        assertEquals(expected, actual);
    }

    @Test
    public void getDeveloperByWrongIdThenReturnNull(){

        Developer expected = null;

        when(repository.getById(999L)).thenReturn(expected);

        Developer actual = service.getById(999L);

        assertEquals(expected, actual);
    }

    @Test
    public void getDeveloperByRightIdThenReturnDeveloper(){

        Developer expected = new Developer();
        expected.setId(1L);

        when(repository.getById(1L)).thenReturn(expected);

        Developer actual = service.getById(1L);

        assertEquals(expected, actual);
    }

    @Test(expected = OptimisticLockException.class)
    public void updateDeveloperWithWrongIdThenReturnNull(){
        when(repository.update(any(Developer.class)))
                .thenThrow(new OptimisticLockException());

        Developer actual = service.update(new Developer());
    }

    @Test
    public void updateDeveloperWithRightIdThenReturnDeveloper(){
        Developer expected = new Developer();

        when(repository.update(expected))
                .thenReturn(expected);

        Developer actual = service.update(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void getListWhenListIsEmptyThenReturnEmptyList(){
        List<Developer> expected = Collections.emptyList();

        when(repository.getAll()).thenReturn(expected);

        List<Developer> actual = service.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void getListWhenHasOneActiveDeveloperThenReturnSingletonList(){
        Developer activeDeveloper = new Developer();
        activeDeveloper.setStatus(Status.ACTIVE);
        List<Developer> expected = Collections.singletonList(activeDeveloper);

        when(repository.getAll()).thenReturn(expected);

        List<Developer> actual = service.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void getListWhenHasOneDeletedDeveloperThenReturnEmptyList(){
        Developer activeDeveloper = new Developer();
        activeDeveloper.setStatus(Status.DELETED);
        List<Developer> expected = Collections.emptyList();

        when(repository.getAll()).thenReturn(Collections.singletonList(activeDeveloper));

        List<Developer> actual = service.getAll();

        assertEquals(expected, actual);
    }
}