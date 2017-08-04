package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import sample.datamodel.TodoItem;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<TodoItem> todoItems;

    @FXML
    private ListView<TodoItem> todoListView;

    @FXML
    private TextArea itemDetailsTextArea;

    public void initialize(){
        TodoItem item1=new TodoItem("This is ToDoApplication","Please enjoy", LocalDate.of(2017, Month.AUGUST,04));
        TodoItem item2=new TodoItem("First JavaFX","----", LocalDate.of(2017,Month.AUGUST,04));

        todoItems = new ArrayList<TodoItem>();
        todoItems.add(item1);
        todoItems.add(item2);

        todoListView.getItems().setAll(todoItems);

        //Can only select one item at a time
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void handleClickListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        //System.out.println("Selected Item is:" + item);
        StringBuilder sb = new StringBuilder(item.getDetails());
        sb.append("\n\n\n");
        sb.append("DUE: ");
        sb.append(item.getDeadline());
        itemDetailsTextArea.setText(sb.toString());
    }
}
