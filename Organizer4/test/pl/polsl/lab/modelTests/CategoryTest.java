package pl.polsl.lab.modelTests;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import pl.polsl.lab.model.Category;
import pl.polsl.lab.model.Task;
import pl.polsl.lab.model.Task.PriorityLevel;

/**
 * Tests for Category class
 *
 * @author Anna Mach
 * @version 1.1
 */
public class CategoryTest {

    Category category = new Category("Test category");
    String name1 = "aaaa";
    PriorityLevel level1 = PriorityLevel.LOW;
    String name2 = "12sf3";
    PriorityLevel level2 = PriorityLevel.HIGH;
    String name3 = "___...";
    PriorityLevel level3 = PriorityLevel.LOW;
    String name4 = "";
    PriorityLevel level4 = PriorityLevel.MEDIUM;
    String name5 = " ";
    PriorityLevel level5 = PriorityLevel.LOW;
    String name6 = "bbbbbbbb";
    PriorityLevel level6 = PriorityLevel.LOW;

    @Before
    public void setup() {
        category.addNewTask(name1, level1);
        category.addNewTask(name2, level2);
        category.addNewTask("fFFffF", PriorityLevel.LOW);
        category.addNewTask(name3, level3);
        category.addNewTask(name4, level4);
        category.addNewTask("b'ds''b", PriorityLevel.HIGH);
        category.addNewTask("e_ee", PriorityLevel.HIGH);
        category.addNewTask(name5, level5);
        category.addNewTask("fdm...vc", PriorityLevel.MEDIUM);
    }

    @Test
    public void testSortingInAddNewTask() {
        List<Task> testList = category.getTasksList();

        PriorityLevel previousPriority = PriorityLevel.HIGH;
        for (Task task : testList) {
            switch (previousPriority) {
                case HIGH:
                    if (task.getPriority() == PriorityLevel.MEDIUM) {
                        previousPriority = PriorityLevel.MEDIUM;
                    } else if (task.getPriority() == PriorityLevel.LOW) {
                        previousPriority = PriorityLevel.LOW;
                    }
                    break;
                case MEDIUM:
                    if (task.getPriority() == PriorityLevel.HIGH) {
                        fail();
                    } else if (task.getPriority() == PriorityLevel.LOW) {
                        previousPriority = PriorityLevel.LOW;
                    }
                    break;
                case LOW:
                    if (task.getPriority() == PriorityLevel.HIGH || task.getPriority() == PriorityLevel.MEDIUM) {
                        fail();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Test
    public void testGetTaskByName() {
        Task tested;

        tested = category.getTaskByName(name1);
        if (tested == null) {
            fail();
        } else {
            assertEquals("Names are not the same!", name1, tested.getName());
        }

        tested = category.getTaskByName(name2);
        if (tested == null) {
            fail();
        } else {
            assertEquals("Names are not the same!", name2, tested.getName());
        }

        tested = category.getTaskByName(name3);
        if (tested == null) {
            fail();
        } else {
            assertEquals("Names are not the same!", name3, tested.getName());
        }

        tested = category.getTaskByName(name4);
        if (tested == null) {
            fail();
        } else {
            assertEquals("Names are not the same!", name4, tested.getName());
        }

        tested = category.getTaskByName(name5);
        if (tested == null) {
            fail();
        } else {
            assertEquals("Names are not the same!", name5, tested.getName());
        }

        tested = category.getTaskByName(name6);
        if (tested != null) {
            fail();
        }
    }

    @Test
    public void testGetFirstUndoneTaskByName() {
        Task tested;

        category.addNewTask(name1, level1);
        category.addNewTask(name1, level1);
        tested = category.getTaskByName(name1);
        tested.makeTaskDone();
        tested = category.getTaskByName(name2);
        tested.makeTaskDone();
        category.addNewTask(name1, level2);
        tested = category.getFirstUndoneTaskByName(name1, level1);
        if (tested == null) {
            fail();
        } else {
            assertEquals("Names are not the same!", name1, tested.getName());
            assertEquals("Priority levels are not the same!", level1, tested.getPriority());
            if (tested.isDone()) {
                fail();
            }
        }

        tested = category.getFirstUndoneTaskByName(name2, level2);
        if (tested != null) {
            fail();
        }

        tested = category.getFirstUndoneTaskByName(name6, level6);
        if (tested != null) {
            fail();
        }
    }

    @Test
    public void testGetLastDoneTaskByName() {
        Task tested;

        category.addNewTask(name1, level1);
        tested = category.getTaskByName(name1);
        tested.makeTaskDone();
        category.addNewTask(name1, level2);
        tested = category.getLastDoneTaskByName(name1, level1);
        if (tested == null) {
            fail();
        } else {
            assertEquals("Names are not the same!", name1, tested.getName());
            assertEquals("Priority levels are not the same!", level1, tested.getPriority());
            if (!tested.isDone()) {
                fail();
            }
        }

        tested = category.getLastDoneTaskByName(name6, level6);
        if (tested != null) {
            fail();
        }
    }

    @Test
    public void testDeleteDoneTasks() {
        Task task;
        category.addNewTask(name1, level1);
        task = category.getTaskByName(name1);
        task.makeTaskDone();
        category.addNewTask(name2, level2);
        task = category.getTaskByName(name2);
        task.makeTaskDone();
        category.addNewTask(name3, level3);
        task = category.getTaskByName(name3);
        task.makeTaskDone();

        category.deleteDoneTasks();

        List<Task> testList = category.getTasksList();
        for (Task t : testList) {
            if (t.isDone()) {
                fail();
            }
        }
    }

    @Test
    public void testGetTasksNameAndStatusInOneString() {
        Category newCategory = new Category("xxx");

        newCategory.addNewTask(name1, level1);
        newCategory.addNewTask(name1, level1);
        newCategory.getFirstUndoneTaskByName(name1, level1).makeTaskDone();
        newCategory.addNewTask(name2, level2);

        String str1 = newCategory.getTasksNamesAndStatusInOneString();
        String str2 = "UNDONE -- " + name2 + " -- " + level2 + " ;; DONE -- " + name1 + " -- " + level1 + " ;; UNDONE -- " + name1 + " -- " + level1 + " ;; ";
        if (!str1.equals(str2)) {
            fail();
        }
        newCategory.addNewTask(name3, level1);
        str1 = newCategory.getTasksNamesAndStatusInOneString();
        if (str1.equals(str2)) {
            fail();
        }
    }
}
