package com.tcf.editor;

import com.tcf.editor.controller.MainController;
import com.tcf.editor.dialog.DialogUtils;
import com.tcf.editor.language.ResourceBundleUtils;
import com.tcf.editor.util.PluginsLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        //设置图标
        primaryStage.getIcons().add(new Image("com/tcf/editor/main/imgs/app.jpg"));

        ResourceBundle locale = ResourceBundleUtils.getResourceBundle();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("appUI.fxml"),locale);
        BorderPane borderPane = loader.load();
        ((MainController)loader.getController()).init();
        primaryStage.setScene(new Scene(borderPane,500,400));
        primaryStage.setTitle(locale.getString("appName"));
        primaryStage.show();

        //设置对话框工具
        DialogUtils.stage = primaryStage;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
