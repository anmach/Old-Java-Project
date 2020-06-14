package pl.polsl.lab.view;
import pl.polsl.lab.model.NullCategoryException;
import pl.polsl.lab.model.Organizer;

/**
 * Main class of view package. Creating frames and showing them when needed.
 *
 * @author Anna Mach
 * @version 1
 */
public class ViewMain{
    
    /**
     * Main function, checking in loop variables from frames which tells function which frame needs to be shown.
     * @param args ---
     */
    public static void main(String[] args) {
        Organizer organizer = new Organizer();
        CategoriesFrame categoriesFrame;
        TasksFrame tasksFrame = null;
        categoriesFrame = new CategoriesFrame(organizer);
        categoriesFrame.showFrame();
        boolean doWeShowTasks = false;
        
        while(true){
            try{
            Thread.sleep(100);
            }
            catch(InterruptedException e){   
            }
            
            if(categoriesFrame.doWeWantToRefreshCategories()){
                categoriesFrame.showFrame();
                categoriesFrame.resetDoWeRefresh();
            }

            else if(doWeShowTasks){
                categoriesFrame.resetDoWeShowTasks();
                doWeShowTasks = false;
                categoriesFrame.hide();
                try{
                tasksFrame = new TasksFrame(organizer.getCategoryByName(categoriesFrame.getChosenCategory()));
                tasksFrame.showFrame();
                } catch(NullCategoryException exc){
                    ErrorFrame errFrame = new ErrorFrame();
                    errFrame.show();
                }
            } 
            
            else if(tasksFrame != null){
                if(tasksFrame.doWeWantToShowCategories()){
                    tasksFrame.resetDoWeShowCategories();
                    tasksFrame.hide();
                    categoriesFrame.showFrame();
                }
                else if(tasksFrame.doWeWantToRefreshTasks()){
                    tasksFrame.showFrame();
                    tasksFrame.resetDoWeRefresh();
                }
            }
            
            doWeShowTasks = categoriesFrame.doWeWantToShowTasks();
        }
    }
}
