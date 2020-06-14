package pl.polsl.lab.modelTests;

import static org.junit.Assert.fail;
import org.junit.Test;
import pl.polsl.lab.model.Category;
import pl.polsl.lab.exceptions.NullCategoryException;
import pl.polsl.lab.model.Organizer;
import pl.polsl.lab.model.Task;

/**
 * Tests for Organizer class
 *
 * @author Anna Mach
 * @version 1.2
 */
public class OrganizerTest {

    String name1 = "lala";
    String name2 = "21;; df.";
    String name3 = "";
    String name4 = "zombie";
    String[] names = {name1, name2, name3};

    Organizer organizer = new Organizer(names);

    @Test
    public void testGetCategoryByName() {
        Category testedCategory;

        try {
            testedCategory = organizer.getCategoryByName(name4);
            if (!testedCategory.getName().equals(name4)) {
                fail();
            }
            fail("Getter for non-existing category should throw exception");
        } catch (NullCategoryException e) {
        }

        try {
            testedCategory = organizer.getCategoryByName(name1);
            if (!testedCategory.getName().equals(name1)) {
                fail();
            }
        } catch (NullCategoryException e) {
            fail("Getting category or constructor don't work");
        }

        try {
            testedCategory = organizer.getCategoryByName(name2);
            if (!testedCategory.getName().equals(name2)) {
                fail();
            }
        } catch (NullCategoryException e) {
            fail("Getting category or constructor don't work");
        }

        try {
            testedCategory = organizer.getCategoryByName(name3);
            if (!testedCategory.getName().equals(name3)) {
                fail();
            }
        } catch (NullCategoryException e) {
            fail("Getting category or constructor don't work");
        }
    }

    @Test
    public void testGetNamesOfCategoriesInOneString() {
        String string1 = organizer.getNamesOfCategoriesInOneString();
        String string2 = "";

        string2 += name1;
        string2 += " ;; ";
        string2 += name2;
        string2 += " ;; ";
        string2 += name3;
        string2 += " ;; ";

        if (!string1.equals(string2)) {
            fail();
        }

        organizer.addCategory(name1);
        string1 = organizer.getNamesOfCategoriesInOneString();
        if (string1.equals(string2)) {
            fail();
        }
    }

    @Test
    public void testDeleteAllDoneTasks() {
        Category category;
        try {
            category = organizer.getCategoryByName(name1);
            category.addNewTask(name1, Task.PriorityLevel.LOW);
            category.addNewTask(name1, Task.PriorityLevel.LOW);
            category.addNewTask(name1, Task.PriorityLevel.LOW);
            category.getFirstUndoneTaskByName(name1, Task.PriorityLevel.LOW).makeTaskDone();
            category.getFirstUndoneTaskByName(name1, Task.PriorityLevel.LOW).makeTaskDone();
        } catch (NullCategoryException e) {
        }
        try {
            category = organizer.getCategoryByName(name2);
            category.addNewTask(name2, Task.PriorityLevel.LOW);
            category.addNewTask(name2, Task.PriorityLevel.LOW);
            category.addNewTask(name2, Task.PriorityLevel.LOW);
            category.getFirstUndoneTaskByName(name2, Task.PriorityLevel.LOW).makeTaskDone();
        } catch (NullCategoryException e) {
        }
        organizer.deleteAllDoneTasks();

        try {
            category = organizer.getCategoryByName(name1);
            if (category.getLastDoneTaskByName(name1, Task.PriorityLevel.LOW) != null) {
                fail();
            }
        } catch (NullCategoryException e) {
        }
        try {
            category = organizer.getCategoryByName(name2);
            if (category.getLastDoneTaskByName(name2, Task.PriorityLevel.LOW) != null) {
                fail();
            }
        } catch (NullCategoryException e) {
        }
        try {
            category = organizer.getCategoryByName(name2);
            if (category.getLastDoneTaskByName(name2, Task.PriorityLevel.LOW) != null) {
                fail();
            }
        } catch (NullCategoryException e) {
        }
    }
}
