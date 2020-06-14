package pl.polsl.lab.modelTests;

import static org.junit.Assert.fail;
import org.junit.Test;
import pl.polsl.lab.model.NullCategoryException;
import pl.polsl.lab.model.Organizer;

/**
 * Tests for Organizer class
 *
 * @author Anna Mach
 * @version 1.0
 */
public class OrganizerTest {

    Organizer organizer = new Organizer();

    @Test
    public void testGetCategoryByName() {
        try {
            organizer.getCategoryByName("Zombie");
            fail("Getter for non-existing category should throw exception");
        } catch (NullCategoryException e) {}

        organizer.addCategory("Family friendly");
        try {
            organizer.getCategoryByName("Family friendly");
        } catch (NullCategoryException e) {
            fail("Getting or adding Category don't work");
        }
    }
}
