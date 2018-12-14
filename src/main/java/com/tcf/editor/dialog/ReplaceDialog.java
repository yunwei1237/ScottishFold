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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * @author Archer Tan
 */
public class ReplaceDialog {
    /**
     * ��������̨
     */
    private static Stage stage;
    //���ڲ������ı���
    private TextArea content;

    @FXML
    private TextField findText,replaceText;
    @FXML
    private Button btnFind,btnReplaceNext,btnReplaceAll,btnCancel;
    @FXML
    private CheckBox checkCase;
    public ReplaceDialog(Stage parent,TextArea content) {
        this.content = content;//���Ҫ������ı������
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("replaceDialog.fxml"));
        //loader.setRoot(FindDialog.this);
        loader.setController(ReplaceDialog.this);
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
        stage.hide();
        //Ĭ�Ͻ��ð�ť
        btnFind.setDisable(true);
        btnReplaceNext.setDisable(true);
        btnReplaceAll.setDisable(true);
        //������������
        findText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.isEmpty()){
                    btnFind.setDisable(true);
                    btnReplaceNext.setDisable(true);
                    btnReplaceAll.setDisable(true);
                }else{
                    btnFind.setDisable(false);
                    btnReplaceNext.setDisable(false);
                    btnReplaceAll.setDisable(false);
                }
            }
        });
        //���Ұ�ť
        btnFind.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                findText();
            }
        });
        //�滻
        btnReplaceNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                replaceNext();
            }
        });
        //�滻ȫ��
        btnReplaceAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                replaceAll();
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
     * �����ַ����ķ�Χ
     * @param find ����
     * @param text �����ַ���
     * @param checkCase �Ƿ����ִ�Сд
     * @return
     */
    private IndexRange findTextRange(String find,String text,boolean checkCase){
        //�Ƿ���Դ�Сд
        if(!checkCase){
            find = find.toUpperCase();
            text = text.toUpperCase();
        }
        int anchor = text.indexOf(find, start);
        if(anchor != -1) {
            int caretPosition = anchor + find.length();
            start = caretPosition;
            return new IndexRange(anchor,caretPosition);
        }
        return null;
    }
    /**
     * ������Ϣ,������ѡ��ķ�Χ
     */
    private void findText(){
        String find = findText.getText();
        String text = content.getText();
        boolean chkCase = checkCase.isSelected();
        range = findTextRange(find, text, chkCase);
        if(range != null) {
            content.selectRange(range.getStart(), range.getEnd());
        }else {
            DialogUtils.alert("�Ҳ���" + find);
        }
    }
    //������ҵĽ��
    IndexRange range = null;
    //�滻һ��
    private void replaceNext(){
        String find = findText.getText();
        String text = content.getText();
        boolean chkCase = checkCase.isSelected();
        if(range == null){
            range = findTextRange(find, text, chkCase);
        }else{
            String replaceStr = replaceText.getText();
            content.replaceText(range, replaceStr);
            range = null;
        }
    }
    //�滻ȫ��
    private void replaceAll(){
        String find = findText.getText();
        String text = content.getText();
        boolean chkCase = checkCase.isSelected();
        String replaceStr = replaceText.getText();
        do{
            if(range != null){
                content.replaceText(range, replaceStr);
            }
            range = findTextRange(find, text, chkCase);
        }while(range != null);
    }
}