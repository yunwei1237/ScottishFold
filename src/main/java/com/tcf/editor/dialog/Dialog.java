package com.tcf.editor.dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Archer Tan
 */
public class Dialog {
    /**
     * 本窗体舞台
     */
    private static Stage stage;
    @FXML
    private Label content;
    @FXML
    private Button save;
    @FXML
    private Button unsave;
    @FXML
    private Button cancel;

    private Dialog() {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dialog.fxml"));
        //loader.setRoot(FindDialog.this);
        loader.setController(Dialog.this);
        try {
            BorderPane pane = loader.load();
            stage.setScene(new Scene(pane));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setResizable(false);
        stage.setMaximized(false);
    }

    public void show(){
        stage.show();
    }
    public void close(){
        stage.close();
    }

    private static Map<String,Dialog> dialogs = new HashMap<String,Dialog>();
    public static void showSaveDialog(String title, String text, EventHandler save, EventHandler unsave) {
        Dialog sqc = new Dialog();
        dialogs.put("save", sqc);
        sqc.stage.setTitle(title);
        sqc.content.setText(text);
        sqc.save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                save.handle(event);
                dialogs.get("save").close();
                System.out.println("save");
            }
        });
        sqc.unsave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                unsave.handle(event);
                dialogs.get("save").close();
                System.out.println("unsave");
            }
        });
        //sqc.cancel.setText("取消");
        sqc.cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialogs.get("save").close();
                System.out.println("cancel");
            }
        });
        sqc.show();
    }

    public static void showAlertDialog(String title,String text){
        Dialog sqc = new Dialog();
        dialogs.put("alert", sqc);
        sqc.stage.setTitle(title);
        sqc.content.setText(text);
        sqc.save.setVisible(false);
        sqc.unsave.setVisible(false);
        sqc.cancel.setText("确定");
        sqc.cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialogs.get("alert").close();
            }
        });
        sqc.show();
    }
}