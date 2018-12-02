package com.tcf.editor;

import com.tcf.editor.language.ResourceBundleUtils;
import com.tcf.editor.util.PluginsLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        ResourceBundle locale = ResourceBundleUtils.getResourceBundle();
        BorderPane borderPane = FXMLLoader.load(this.getClass().getResource("main.fxml"), locale);
        //加载插件
        //new PluginsLoader().addPlugins(borderPane);

        primaryStage.setScene(new Scene(borderPane,500,400));
        primaryStage.setTitle(locale.getString("appName"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
