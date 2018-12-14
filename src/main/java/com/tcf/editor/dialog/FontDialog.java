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
     * 本窗体舞台
     */
    private static Stage stage;
    //用于操作的文本框
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
        this.content = content;//获得要处理的文本框对象
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
        stage.setTitle("跳转");
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.initOwner(parent);

        //获得默认字体
        Font defaultFont = content.getFont();
        valFontFamily = defaultFont.getFamily();
        valFontSize = defaultFont.getSize();
        String style = defaultFont.getName();
        valFontStyle = getValFontStyle(defaultFont);
        valFontPosture = getValFontPosture(defaultFont);
        //valFontPosture = defaultFont.get
        //字体类型

        fontFamilySelect.getItems().addAll(Font.getFamilies());
        fontFamilySelect.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                fontFamily.setText(newValue.toString());
                valFontFamily = newValue.toString();
                lblSample.setFont(Font.font(valFontFamily, valFontStyle, valFontPosture, valFontSize));
            }
        });
        fontFamilySelect.getSelectionModel().select(defaultFont.getFamily());//设置编辑器的字体
        //字体样式
        fontStyleSelect.getItems().addAll(new String[]{"常规","粗体","倾斜","粗体 倾斜"});
        fontStyleSelect.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                fontStyle.setText(newValue.toString());
                switch(newValue.toString()){
                    case "常规":
                        valFontStyle = FontWeight.NORMAL;
                        valFontPosture = FontPosture.REGULAR;
                        break;
                    case "粗体":
                        valFontStyle = FontWeight.BOLD;
                        valFontPosture = FontPosture.REGULAR;
                        break;
                    case "倾斜":
                        valFontStyle = FontWeight.NORMAL;
                        valFontPosture = FontPosture.ITALIC;
                        break;
                    case "粗体 倾斜":
                        valFontStyle = FontWeight.BOLD;
                        valFontPosture = FontPosture.ITALIC;
                        break;
                }
                lblSample.setFont(Font.font(valFontFamily, valFontStyle, valFontPosture, valFontSize));
            }
        });
        fontStyleSelect.getSelectionModel().select(defaultFont.getStyle());
        //字体大小
        fontSizeSelect.getItems().addAll(new String[]
                {"8","9","10","11","12","14","16","18",
                        "20","22","24","26","28","36","48","72",
                        "初号","小初","一号","小一","二号","小二","三号","小三","四号","小四","五号","小五","六号","小六","七号","八号"});
        fontSizeSelect.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                fontSize.setText(newValue.toString());
                switch (newValue.toString()){
                    case "初号":
                        valFontSize = 42.0;
                        break;
                    case "小初":
                        valFontSize = 36.0;
                        break;
                    case "一号":
                        valFontSize = 26.0;
                        break;
                    case "小一":
                        valFontSize = 24.0;
                        break;
                    case "二号":
                        valFontSize = 22.0;
                        break;
                    case "小二":
                        valFontSize = 18.0;
                        break;
                    case "三号":
                        valFontSize = 16.0;
                        break;
                    case "四号":
                        valFontSize = 14.0;
                        break;
                    case "小四":
                        valFontSize = 12.0;
                        break;
                    case "五号":
                        valFontSize = 10.5;
                        break;
                    case "小五":
                        valFontSize = 9.0;
                        break;
                    case "六号":
                        valFontSize = 7.5;
                        break;
                    case "小六":
                        valFontSize = 6.5;
                        break;
                    case "七号":
                        valFontSize = 5.5;
                        break;
                    case "八号":
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
        //确定按钮
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                content.setFont(lblSample.getFont());
                hide();
            }
        });
        //取消按钮
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