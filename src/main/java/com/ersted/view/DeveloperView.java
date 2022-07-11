package com.ersted.view;

import com.ersted.controller.DeveloperController;
import com.ersted.controller.SkillController;
import com.ersted.controller.SpecialtyController;
import com.ersted.model.Developer;
import com.ersted.model.Skill;
import com.ersted.model.Specialty;
import com.ersted.model.Status;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView extends BaseView {
    private final DeveloperController developerController;
    private final SpecialtyController specialtyController;
    private final SkillController skillController;

    public DeveloperView(DeveloperController developerService,
                         SpecialtyController specialtyRepository,
                         SkillController skillService,
                         Scanner scanner) {

        super(scanner);
        this.developerController = developerService;
        this.specialtyController = specialtyRepository;
        this.skillController = skillService;
    }

    @Override
    public void create() {
        System.out.println("Enter information about developer:");
        System.out.print("First name: ");
        String firstname = scanner.nextLine();

        System.out.print("Lastname: ");
        String lastname = scanner.nextLine();

        List<Specialty> specialties = specialtyController.getAll();
        if (specialties == null) {
            System.out.println("List is empty.");
        } else {
            for (Specialty specialty : specialties) {
                System.out.printf("ID:\t%d\t\tName:\t%s%n", specialty.getId(), specialty.getName());
            }
        }

        System.out.println("Enter the specialty id: ");
        Specialty specialty = specialtyController.getById(Long.parseLong(scanner.nextLine()));

        List<Skill> skillList = skillController.getAll();
        if (skillList == null) {
            System.out.println("List is empty.");
            return;
        }

        for (Skill skill : skillList) {
            System.out.printf("ID:\t%d\t\tName:\t%s%n", skill.getId(), skill.getName());
        }

        System.out.print("Enter skills id: ");
        ArrayList<Skill> skills = new ArrayList<>();

        while (true) {
            String str = scanner.nextLine();
            if (str.equals("exit"))
                break;
            skills.add(skillController.getById(Long.parseLong(str)));
        }

        developerController.create(
                new Developer(
                        null,
                        firstname,
                        lastname,
                        specialty,
                        skills,
                        Status.ACTIVE
                )
        );
        System.out.println("Developer successful created.");
    }

    @Override
    public void delete() {
        System.out.println("Enter developer's id:");
        developerController.deleteById(scanner.nextLong());
        System.out.println("Developer successful deleted.");
    }

    @Override
    public void update() {
        System.out.println("Enter developer's ID: ");
        Long id = scanner.nextLong();

        System.out.print("Firstname: ");
        String firstname = scanner.nextLine();

        System.out.print("Lastname: ");
        String lastname = scanner.nextLine();

        List<Specialty> specialties = specialtyController.getAll();
        if (specialties == null) {
            System.out.println("List is empty.");
        } else {
            for (Specialty specialty : specialties) {
                System.out.printf("ID:\t%d\t\tName:\t%s%n", specialty.getId(), specialty.getName());
            }
        }

        System.out.println("Enter the specialty ID: ");
        Specialty specialty = specialtyController.getById(Long.parseLong(scanner.nextLine()));


        System.out.println("Enter skills id: ");
        ArrayList<Skill> skills = new ArrayList<>();
        while (true) {
            String str = scanner.nextLine();
            if (str.equals("exit"))
                break;
            skills.add(skillController.getById(Long.parseLong(str)));

        }
        developerController.update(
                new Developer(
                        id,
                        firstname,
                        lastname,
                        specialty,
                        skills,
                        Status.ACTIVE
                )
        );
        System.out.println("Developer successful updated.");
    }

    @Override
    public void getById() {
        System.out.print("Enter developer's id: ");

        Developer developer = developerController.getById(scanner.nextLong());
        System.out.printf("ID:\t%d%nFirstname:\t%s%nLastname:\t%s%nSpecialty:\t%s%n",
                developer.getId(),
                developer.getFirstName(),
                developer.getLastName(),
                developer.getSpecialty().getName());

        System.out.print("Skills:");
        for(Skill skill : developer.getSkills())
            System.out.print("\t" + skill.getName() + ";");
    }

    @Override
    public void getAll() {
        List<Developer> developers = developerController.getAll();
        if (developers == null) {
            System.out.println("List is empty.");
            return;
        }

        for (Developer developer : developers) {
            System.out.printf("ID:\t%d\t\tFirstname:\t%s\t\tLastname:\t%s\t\tSpecialty:\t%s%n",
                    developer.getId(),
                    developer.getFirstName(),
                    developer.getLastName(),
                    developer.getSpecialty().getName());
        }
    }
}
