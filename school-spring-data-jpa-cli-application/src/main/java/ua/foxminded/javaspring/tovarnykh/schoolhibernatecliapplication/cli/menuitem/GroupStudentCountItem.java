package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.cli.menuitem;

import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.cli.CommandLineInterface;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.dao.entity.Group;
import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.service.GroupService;

@Component
public class GroupStudentCountItem extends CommandLineInterface implements Item {

    private final String itemName;

    private GroupService groupService;

    GroupStudentCountItem(GroupService groupService) {
        this.groupService = groupService;
        itemName = "Get Groups by Students amount";
    }

    @Override
    public void draw() {
        System.out.println("Please, enter number of students to find groups with it`s amount:");
        int numberOfStudents = readNumber();

        System.out.print("""
                ╔════════════════════════════════════════╗
                ║                  Groups                ║
                ╟────────────────────────────────────────╢
                 """);

        List<Group> groups = groupService.getGroupsWithLessStudents(numberOfStudents);

        System.out.printf(" %12s |  %s | %s %n", "id", "name", "inscribed");
        System.out.println(DELIMITER);
        if (!groups.isEmpty()) {
            groups.forEach(group -> System.out.printf(" %12d | %s | %s %n", 
                    group.getId(), 
                    group.getName(),
                    group.getStudents().size()));
        }
        closeSection();

    }

    @Override
    public String getName() {
        return itemName;
    }

}
