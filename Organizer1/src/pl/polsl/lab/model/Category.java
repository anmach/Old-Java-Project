package pl.polsl.lab.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class representing category containing group of tasks.
 *
 * @author Anna Mach
 * @version 1
 */
public class Category {
    /**
     * Name of the category.
     */
    private final String name;
    /**
     * List containing tasks belonging to category.
     */
    private List<Task> taskList;
    
    /**
     * Costructor for class Category.
     * 
     * @param categoryName name of the category
     */
    public Category(String categoryName){
        this.name = categoryName;
        taskList = new ArrayList();
    }
    
    /**
     * Method adding new task to taskList and sorting them by priority of tasks.
     * 
     * @param nameOfNewTask Name of new task, which will be added to task list in this method
     * @param priorityOfNewTask Priority of new task
     */
    public void addNewTask(String nameOfNewTask, Task.PriorityLevel priorityOfNewTask){
        taskList.add(new Task(nameOfNewTask,priorityOfNewTask));
        
        Collections.sort(taskList, new Comparator<Task>() {
        @Override
            public int compare(Task task1, Task task2) {
                return task1.getPriority().compareTo(task2.getPriority());
            }
        });
    }
    
    /**
     * Getter, returning name of category.
     * 
     * @return name of the Category
     */
    public String getName(){
        return name;
    }
    
    /**
     * Getter, returning list of the tasks belonging to Category
     * 
     * @return taskList of Category
     */
    public List<Task> getTasksList(){
        return taskList;
    }
    
    /**
     * Getter, searching for Task in taskList, which have name given in the parameters
     * 
     * @param taskName name of the Task, which we are looking for
     * @return null or Task
     */
    public Task getTaskByName(String taskName){
        for(int i =0; i<taskList.size(); i++){
            if(taskList.get(i).getName().equals(taskName)){
                return taskList.get(i);
            }
        }
        return null;
    }
    
    /**
     * Getter, searching for first occurrence of Task in taskList, but only for these for which method isDone() returns false
     * 
     * @param taskName name of the Task, which we are looking for
     * @return null or Task
     */
    public Task getFirstUndoneTaskByName(String taskName){
        for(int i =0; i<taskList.size(); i++){
            if(taskList.get(i).getName().equals(taskName) && !taskList.get(i).isDone()){
                return taskList.get(i);
            }
        }
        return null;
    }
    
    /**
     * Getter, searching for last occurrence of Task in taskList, but only for these for which method isDone() returns true
     * 
     * @param taskName name of the Task, which we are looking for
     * @return null or Task
     */
    public Task getLastDoneTaskByName(String taskName){
        for(int i =taskList.size() -1; i>=0; i--){
            if(taskList.get(i).getName().equals(taskName) && taskList.get(i).isDone()){
                return taskList.get(i);
            }
        }
        return null;
    }
    
}
