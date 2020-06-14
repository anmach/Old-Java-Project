package pl.polsl.lab.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class of model, creating default categories.
 *
 * @author Anna Mach
 * @version 1.1
 */
public class Organizer {

    /**
     * List containing categories avalible
     */
    private List<Category> categoriesList;

    /**
     * Constructor of main class of model Makes main categories, which are
     * avalible at start
     */
    public Organizer() {
        categoriesList = new ArrayList();

        categoriesList.add(new Category("Shopping list"));
        categoriesList.add(new Category("Homework"));
        categoriesList.add(new Category("Housework"));
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
     * @throws pl.polsl.lab.model.NullCategoryException when there is no
     * category of given name, exception is thrown
     */
    public Category getCategoryByName(String categoryName) throws NullCategoryException {
        for (Category cat : categoriesList) {
            if (cat.getName().equals(categoryName)) {
                return cat;
            }
        }
        throw new NullCategoryException();
    }
}
