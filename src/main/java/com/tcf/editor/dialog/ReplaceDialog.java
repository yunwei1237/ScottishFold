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
     * 本窗体舞台
     */
    private static Stage stage;
    //用于操作的文本框
    private TextArea content;

    @FXML
    private TextField findText,replaceText;
    @FXML
    private Button btnFind,btnReplaceNext,btnReplaceAll,btnCancel;
    @FXML
    private CheckBox checkCase;
    public ReplaceDialog(Stage parent,TextArea content) {
        this.content = content;//获得要处理的文本框对象
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
        stage.setTitle("查找");
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.initOwner(parent);
        stage.hide();
        //默认禁用按钮
        btnFind.setDisable(true);
        btnReplaceNext.setDisable(true);
        btnReplaceAll.setDisable(true);
        //查找内容配置
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
        //查找按钮
        btnFind.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                findText();
            }
        });
        //替换
        btnReplaceNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                replaceNext();
            }
        });
        //替换全部
        btnReplaceAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                replaceAll();
            }
        });
        //取消按钮
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
     * 查找字符串的范围
     * @param find 数据
     * @param text 查找字符串
     * @param checkCase 是否区分大小写
     * @return
     */
    private IndexRange findTextRange(String find,String text,boolean checkCase){
        //是否忽略大小写
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
     * 查找信息,并返回选择的范围
     */
    private void findText(){
        String find = findText.getText();
        String text = content.getText();
        boolean chkCase = checkCase.isSelected();
        range = findTextRange(find, text, chkCase);
        if(range != null) {
            content.selectRange(range.getStart(), range.getEnd());
        }else {
            DialogUtils.alert("找不到" + find);
        }
    }
    //保存查找的结果
    IndexRange range = null;
    //替换一次
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
    //替换全部
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