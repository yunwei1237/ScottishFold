package com.tcf.editor.dialog;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * @author Archer Tan
 */
public class GotoDialog {
    /**
     * ��������̨
     */
    private static Stage stage;
    //���ڲ������ı���
    private TextArea content;

    @FXML
    private TextField rowNumber;
    @FXML
    private Button btnGoto,btnCancel;
    public GotoDialog(Stage parent, TextArea content) {
        this.content = content;//���Ҫ������ı������
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gotoDialog.fxml"));
        //loader.setRoot(FindDialog.this);
        loader.setController(GotoDialog.this);
        try {
            VBox pane = loader.load();
            stage.setScene(new Scene(pane));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("��ת");
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.initOwner(parent);
        rowNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                for(char chr : newValue.toCharArray()){
                    if(!Character.isDigit(chr)){
                        DialogUtils.alert("���ڴ˴�ֻ���������֣�");
                        rowNumber.setText("");
                    }
                }
            }
        });
        //ת����ť
        btnGoto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String line = rowNumber.getText();
                gotoLine(Integer.parseInt(line));
            }
        });
        //ȡ����ť
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.hide();
            }
        });
    }


    public void show(){
        stage.show();
    }
    public void hide(){
        stage.hide();
    }
    public void close(){stage.close();}

    private void gotoLine(int line){
        String text = content.getText()+".";//��.��Ϊ�˷����ֳ���Ӧ��Ƭ�Σ��Ա��������
        String[] lines = text.split("\\r?\\n");
        if(line > lines.length){
            DialogUtils.alert("������������������");
            return;
        }
        int anchor = 0;
        for(int i = 0;i<line;i++){
            anchor += lines[i].length();
        }
        content.positionCaret(anchor);
    }

}