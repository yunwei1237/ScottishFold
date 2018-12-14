package com.tcf.editor.dialog;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Archer Tan
 */
public class FontDialog {
    /**
     * ��������̨
     */
    private static Stage stage;
    //���ڲ������ı���
    private TextArea content;

    @FXML
    private TextField fontFamily,fontStyle,fontSize;
    @FXML
    private ListView fontFamilySelect,fontStyleSelect,fontSizeSelect;
    @FXML
    private Label lblSample;
    @FXML
    private ChoiceBox scriptBox;
    @FXML
    private Hyperlink linkMoreFont;
    @FXML
    private Button btnOk,btnCancel;

    private String valFontFamily;
    private FontWeight valFontStyle;
    private Double valFontSize;
    private FontPosture valFontPosture;
    public FontDialog(Stage parent, TextArea content) {
        this.content = content;//���Ҫ������ı������
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fontDialog.fxml"));
        //loader.setRoot(FindDialog.this);
        loader.setController(FontDialog.this);
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

        //���Ĭ������
        Font defaultFont = content.getFont();
        valFontFamily = defaultFont.getFamily();
        valFontSize = defaultFont.getSize();
        String style = defaultFont.getName();
        valFontStyle = getValFontStyle(defaultFont);
        valFontPosture = getValFontPosture(defaultFont);
        //valFontPosture = defaultFont.get
        //��������

        fontFamilySelect.getItems().addAll(Font.getFamilies());
        fontFamilySelect.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                fontFamily.setText(newValue.toString());
                valFontFamily = newValue.toString();
                lblSample.setFont(Font.font(valFontFamily, valFontStyle, valFontPosture, valFontSize));
            }
        });
        fontFamilySelect.getSelectionModel().select(defaultFont.getFamily());//���ñ༭��������
        //������ʽ
        fontStyleSelect.getItems().addAll(new String[]{"����","����","��б","���� ��б"});
        fontStyleSelect.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                fontStyle.setText(newValue.toString());
                switch(newValue.toString()){
                    case "����":
                        valFontStyle = FontWeight.NORMAL;
                        valFontPosture = FontPosture.REGULAR;
                        break;
                    case "����":
                        valFontStyle = FontWeight.BOLD;
                        valFontPosture = FontPosture.REGULAR;
                        break;
                    case "��б":
                        valFontStyle = FontWeight.NORMAL;
                        valFontPosture = FontPosture.ITALIC;
                        break;
                    case "���� ��б":
                        valFontStyle = FontWeight.BOLD;
                        valFontPosture = FontPosture.ITALIC;
                        break;
                }
                lblSample.setFont(Font.font(valFontFamily, valFontStyle, valFontPosture, valFontSize));
            }
        });
        fontStyleSelect.getSelectionModel().select(defaultFont.getStyle());
        //�����С
        fontSizeSelect.getItems().addAll(new String[]
                {"8","9","10","11","12","14","16","18",
                        "20","22","24","26","28","36","48","72",
                        "����","С��","һ��","Сһ","����","С��","����","С��","�ĺ�","С��","���","С��","����","С��","�ߺ�","�˺�"});
        fontSizeSelect.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                fontSize.setText(newValue.toString());
                switch (newValue.toString()){
                    case "����":
                        valFontSize = 42.0;
                        break;
                    case "С��":
                        valFontSize = 36.0;
                        break;
                    case "һ��":
                        valFontSize = 26.0;
                        break;
                    case "Сһ":
                        valFontSize = 24.0;
                        break;
                    case "����":
                        valFontSize = 22.0;
                        break;
                    case "С��":
                        valFontSize = 18.0;
                        break;
                    case "����":
                        valFontSize = 16.0;
                        break;
                    case "�ĺ�":
                        valFontSize = 14.0;
                        break;
                    case "С��":
                        valFontSize = 12.0;
                        break;
                    case "���":
                        valFontSize = 10.5;
                        break;
                    case "С��":
                        valFontSize = 9.0;
                        break;
                    case "����":
                        valFontSize = 7.5;
                        break;
                    case "С��":
                        valFontSize = 6.5;
                        break;
                    case "�ߺ�":
                        valFontSize = 5.5;
                        break;
                    case "�˺�":
                        valFontSize = 5.0;
                        break;
                    default:
                        valFontSize = Double.parseDouble(newValue. toString());
                        break;
                }
                lblSample.setFont(Font.font(valFontFamily, valFontStyle, valFontPosture, valFontSize));
            }
        });
        fontSize.setText(String.valueOf(defaultFont.getSize()));
        fontSizeSelect.getSelectionModel().select(defaultFont.getSize());
        //ȷ����ť
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                content.setFont(lblSample.getFont());
                hide();
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


    private FontPosture getValFontPosture(Font font){
        String name = font.getName();
        List<String> items = new ArrayList<>();
        if(name.indexOf(" ") != -1) {
            for(String item:name.split("\\s")){
                items.add(item);
            }
        }else{
            items.add(name);
        }
        for(String item:items){
            FontPosture fontPosture = FontPosture.findByName(item);
            if(fontPosture != null){
                return fontPosture;
            }
        }
        return null;
    }
    private FontWeight getValFontStyle(Font font){
        String name = font.getName();
        List<String> items = new ArrayList<>();
        if(name.indexOf(" " ) != -1) {
            for(String item:name.split("\\s")){
                items.add(item);
            }
        }else{
            items.add(name);
        }
        for(String item:items){
            FontWeight fontWeight = FontWeight.findByName(item);
            if(fontWeight != null){
                return fontWeight;
            }
        }
        return null;
    }
}