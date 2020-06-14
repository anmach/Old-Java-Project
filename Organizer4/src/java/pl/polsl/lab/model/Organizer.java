package pl.polsl.lab.model;

import pl.polsl.lab.exceptions.NullCategoryException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class of model, creating default categories.
 *
 * @author Anna Mach
 * @version 1.4
 */
public class Organizer {

    /**
     * List containing categories avalible
     */
    private List<Category> categoriesList;

    /**
     * Constructor of main class of model 
     */
    public Organizer() {
        categoriesList = new ArrayList();
    }

    /**
     * Constructor of main class of model Makes main categories, which are
     * avalible at start
     *
     * @param names array of names of categories avalible at start
     */
    public Organizer(String[] names) {
        categoriesList = new ArrayList();

        for (String s : names) {
            categoriesList.add(new Category(s));
        }
    }

    /**
     * Getter return list of the categories
     *
     * @return categoriesList
     */
    public List<Category> getCategoriesList() {
        return categoriesList;
    }

    /**
     * Method creating and adding new Category of given name
     *
     * @param newCategory name of new category
     */
    public void addCategory(String newCategory) {
        categoriesList.add(new Category(newCategory));
    }

    /**
     * Method for getting Category with given name which occurrs first in list
     *
     * @param categoryName name of Category we are looking for
     * @return Category, which we are looking for
     * @throws pl.polsl.lab.exceptions.NullCategoryException - exception might be thrown when searched category won't exist
     */
    public Category getCategoryByName(String categoryName) throws NullCategoryException {
        for (Category cat : categoriesList) {
            if (cat.getName().equals(categoryName)) {
                return cat;
            }
        }
        throw new NullCategoryException();
    }

    /**
     * Method getting names of all categories avalible and adding them to one
     * string
     *
     * @return string with categories' names
     */
    public String getNamesOfCategoriesInOneString() {
        String names = "";
        for (Category cat : categoriesList) {
            names += cat.getName();
            names += " ;; ";
        }

        if (names.equals("")) {
            names = "No categories found";
        }
        return names;
    }

    /**
     * Method for deleting all done tasks in all categories
     */
    public void deleteAllDoneTasks() {
        for (Category category : categoriesList) {
            category.deleteDoneTasks();
        }
    }
}

