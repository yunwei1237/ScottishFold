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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Archer Tan
 */
public class FindDialog {
    /**
     * ��������̨
     */
    private static Stage stage;
    //���ڲ������ı���
    private TextArea content;

    @FXML
    private TextField findText;
    @FXML
    private Button btnFind,btnCancel;
    @FXML
    private RadioButton top,bottom;
    @FXML
    private CheckBox checkCase;
    public FindDialog(Stage parent,TextArea content) {
        this.content = content;//���Ҫ������ı������
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("findDialog.fxml"));
        //loader.setRoot(FindDialog.this);
        loader.setController(FindDialog.this);
        try {
            VBox pane = loader.load();
            stage.setScene(new Scene(pane));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("����");
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.initOwner(parent);
        //������������
        findText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.isEmpty()){
                    btnFind.setDisable(true);
                }else{
                    btnFind.setDisable(false);
                }
            }
        });
        //��������
        ToggleGroup toggleGroup = new ToggleGroup();
        top.setToggleGroup(toggleGroup);
        bottom.setToggleGroup(toggleGroup);
        //���Ұ�ť
        btnFind.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                findText();
            }
        });
        //ȡ����ť
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.hide();
            }
        });

        content.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                start = content.getAnchor();
            }
        });
    }

    private int start = 0;

    public void show(){
        stage.show();
    }
    public void findNext(){
        findText();
    }
    public void hide(){
        stage.hide();
    }
    public void close(){stage.close();}
    /**
     * ������Ϣ
     */
    private void findText(){
        String find = findText.getText();
        String text = content.getText();
        int direction = top.isSelected()?0:1;//0���� 1����
        int chkCase = checkCase.isSelected()?0:1;//0:�ǣ�1������

        if(chkCase == 1){
            find = find.toUpperCase();
            text = text.toUpperCase();
        }

        if(direction == 0){//����
            String data = text.substring(0,start);
            int anchor = data.lastIndexOf(find);
            if(anchor<=text.length() && anchor >= 0) {
                int caretPosition = anchor + find.length();
                start = anchor;
                content.selectRange(anchor, caretPosition);
            }else{
                DialogUtils.alert("�Ҳ���"+find);
            }
        }else{//����
            int anchor = text.indexOf(find, start);
            if(anchor<=text.length() && anchor >= 0) {
                int caretPosition = anchor + find.length();
                start = caretPosition;
                content.selectRange(anchor, caretPosition);
            }else{
                DialogUtils.alert("�Ҳ���"+find);
            }
        }
    }

    public boolean needInput(){
        return findText.getText().isEmpty();
    }
}