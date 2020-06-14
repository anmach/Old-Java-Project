package pl.polsl.lab.modelTests;

import java.util.List;
import static org.junit.Assert.fail;
import org.junit.Test;
import pl.polsl.lab.model.Category;
import pl.polsl.lab.model.Task;
import pl.polsl.lab.model.Task.PriorityLevel;

/**
 * Tests for Category class
 *
 * @author Anna Mach
 * @version 1.0
 */
public class CategoryTest {

    Category category = new Category("Test category");

    @Test
    public void testSortingInAddNewTask() {
        category.addNewTask("xxx", PriorityLevel.LOW);
        category.addNewTask("aaa", PriorityLevel.HIGH);
        category.addNewTask("fff", PriorityLevel.LOW);
        category.addNewTask("hhh", PriorityLevel.MEDIUM);
        category.addNewTask("ccc", PriorityLevel.LOW);
        category.addNewTask("bbb", PriorityLevel.HIGH);
        category.addNewTask("eee", PriorityLevel.HIGH);
        category.addNewTask("lll", PriorityLevel.LOW);
        category.addNewTask("iii", PriorityLevel.MEDIUM);

        List<Task> testList = category.getTasksList();

        PriorityLevel previousPriority = PriorityLevel.HIGH;
        for (Task task : testList) {

            switch (previousPriority) {
                case HIGH:
                    if (task.getPriority() == PriorityLevel.MEDIUM) {
                        previousPriority = PriorityLevel.MEDIUM;
                    } else if (task.getPriority() == PriorityLevel.LOW) {
                        previousPriority = PriorityLevel.LOW;
                    }   break;
                case MEDIUM:
                    if (task.getPriority() == PriorityLevel.HIGH) {
                        fail();
                    } else if (task.getPriority() == PriorityLevel.LOW) {
                        previousPriority = PriorityLevel.LOW;
                    }   break;
                case LOW:
                    if (task.getPriority() == PriorityLevel.HIGH || task.getPriority() == PriorityLevel.MEDIUM) {
                        fail();
                    }   break;
                default:
                    break;
            }
        }
    }
}
