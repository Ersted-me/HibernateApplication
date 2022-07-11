package com.ersted.view;

import com.ersted.controller.SkillController;
import com.ersted.model.Skill;

import java.util.List;
import java.util.Scanner;

public class SkillView extends BaseView {
    private final SkillController controller;

    public SkillView(SkillController controller, Scanner scanner) {
        super(scanner);
        this.controller = controller;
    }

    @Override
    public void create() {
        System.out.print("Enter skill: ");
        String skillName = scanner.nextLine();

        Skill skill = controller.create(new Skill(null,skillName));
        if(skill !=null)
            System.out.printf("ID:\t%d\tName:\t%s%n", skill.getId(), skill.getName());
    }

    @Override
    public void delete() {
        Long id = getIdFromUser();
        controller.deleteById(id);

        System.out.println("The skill was successfully deleted.");
    }

    @Override
    public void update() {
        Long id = getIdFromUser(); // Enter the skill
        System.out.println("Enter the new name of skill.");
        String newName = scanner.nextLine();

        Skill updatedSkill = controller.update(new Skill(id, newName));

        if(updatedSkill != null)
            System.out.printf("ID:\t%d\tName:\t%s%n", updatedSkill.getId(), updatedSkill.getName());
    }

    @Override
    public void getById() {
        Long id = getIdFromUser();

        Skill skill = controller.getById(id);

        if(skill != null)
            System.out.printf("ID:\t%d\tName:\t%s%n",skill.getId(),skill.getName());

    }

    @Override
    public void getAll() {
        List<Skill> skills = controller.getAll();
        if (skills == null) {
            System.out.println("Список пуст.");
            return;
        }

        for (Skill skill : skills) {
            System.out.printf("ID:\t%d\tName:\t%s%n",skill.getId(), skill.getName());
        }
    }
}
