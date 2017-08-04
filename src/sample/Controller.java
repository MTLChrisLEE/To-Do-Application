package sample;

import sample.datamodel.TodoItem;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<TodoItem> todoItems;

    public void initialize(){
        TodoItem item1=new TodoItem("This is ToDoApplication","Please enjoy", LocalDate.of(2017, Month.AUGUST,04));
        TodoItem item2=new TodoItem("First JavaFX","----", LocalDate.of(2017,Month.AUGUST,04));

        todoItems = new ArrayList<TodoItem>();
        todoItems.add(item1);
        todoItems.add(item2);
    }





}
