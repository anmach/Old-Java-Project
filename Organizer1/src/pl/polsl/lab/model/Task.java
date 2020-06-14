package pl.polsl.lab.model;

import java.util.Collections;

/**
 * Class representing one task, which user uant to be done by himself.
 *
 * @author Anna Mach
 * @version 1
 */
public class Task {
    /**
     * Name of the task.
     */
    private final String name;
    /**
     * Priority of the task.
     */
    private final PriorityLevel priority;
    /**
     * Value true tells us that task was already done
     */
    private boolean isDone;
    
    /**
     * Enum with avalible priorites of task.
     */
    public enum PriorityLevel {HIGH, MEDIUM, LOW};

    /**
     * Constructor adding given in parameters values to variables.
     *
     * @param taskName name of the Task, which we want to create
     * @param priorityOfTask priority of the Task, which we want to create
     */
    Task(String taskName, PriorityLevel priorityOfTask){
        isDone = false;
        this.priority = priorityOfTask;
        this.name = taskName;
    }
    /**
     * Getter, returning String representing name of the task
     * 
     * @return name of the Task
     */
    public String getName(){
        return name;
    }
    
    /**
     * Getter
     * 
     * @return priority of the Task
     */
    public PriorityLevel getPriority(){
        return priority;
    }
    
    /**
     * Getter
     * 
     * @return isDone, like name says 
     */
    public boolean isDone(){
        return isDone;
    }
    
    /**
     * Change value of isDone for true
     */
    public void makeTaskDone(){
        isDone = true;
    }
    
    /**
     * Change value of isDone for false
     */
    public void makeTaskUndone(){
        isDone = false;
    }
}
