package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import sample.datamodel.TodoData;
import sample.datamodel.TodoItem;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Controller {
    private List<TodoItem> todoItems;

    @FXML
    private ListView<TodoItem> todoListView;

    @FXML
    private TextArea itemDetailsTextArea;

    @FXML
    private Label deadlineLabel;

    @FXML
    private BorderPane mainBorderPane;

    public void initialize(){
        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
                if(newValue != null){
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails());
                    deadlineLabel.setText(item.getDeadline().toString());
                }
            }
        });

        todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());

        //Can only select one item at a time
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }



    //Method showing a dialogpane to add an item
    @FXML
    public void showNewItemDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add new Todo Tiem");
        FXMLLoader fxmlLoader =  new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try{

            dialog.getDialogPane().setContent(fxmlLoader.load());

        }catch(IOException e){
            System.out.println("ERROR");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            DialogController controller =fxmlLoader.getController();
            TodoItem newItem= controller.processResults();
            todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
            todoListView.getSelectionModel().select(newItem);
            System.out.println("OK pressed");
        }else{
            System.out.println("Cancel");
        }

    }

    @FXML
    public void handleClickListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        //System.out.println("Selected Item is:" + item);
        //StringBuilder sb = new StringBuilder(item.getDetails());
        //sb.append("\n\n\n");
        //sb.append("DUE: ");
        //sb.append(item.getDeadline());
        itemDetailsTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().toString());
    }
}
