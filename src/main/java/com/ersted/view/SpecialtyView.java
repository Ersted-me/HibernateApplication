package com.ersted.view;

import com.ersted.controller.SpecialtyController;
import com.ersted.model.Specialty;

import java.util.List;
import java.util.Scanner;

public class SpecialtyView extends BaseView{
    private final SpecialtyController controller;

    public SpecialtyView(SpecialtyController controller, Scanner scanner) {
        super(scanner);
        this.controller = controller;
    }

    @Override
    public void create() {
        System.out.print("Enter specialty: ");
        String specialtyName = scanner.nextLine();

        Specialty specialty = controller.create(new Specialty(null, specialtyName));
        if(specialty !=null)
            System.out.printf("ID:\t%d\tName:\t%s%n", specialty.getId(), specialty.getName());
    }

    @Override
    public void delete() {
        Long id = getIdFromUser();
        controller.deleteById(id);

        System.out.println("The specialty was successfully deleted.");
    }

    @Override
    public void update() {
        Long id = getIdFromUser();
        System.out.println("Enter the new name of specialty.");
        String newName = scanner.nextLine();

        Specialty updatedSpecialty = controller.update(new Specialty(id, newName));

        if(updatedSpecialty != null)
            System.out.printf("ID:\t%d\tName:\t%s%n", updatedSpecialty.getId(), updatedSpecialty.getName());
    }

    @Override
    public void getById() {
        Long id = getIdFromUser();

        Specialty specialty = controller.getById(id);

        if(specialty != null)
            System.out.printf("ID:\t%d\tName:\t%s%n", specialty.getId(), specialty.getName());

    }

    @Override
    public void getAll() {
        List<Specialty> specialties = controller.getAll();
        if (specialties == null) {
            System.out.println("Список пуст.");
            return;
        }

        for (Specialty specialty : specialties) {
            System.out.printf("ID:\t%d\tName:\t%s%n", specialty.getId(), specialty.getName());
        }
    }
}
