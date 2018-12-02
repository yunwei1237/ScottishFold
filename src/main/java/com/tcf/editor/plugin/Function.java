package com.tcf.editor.plugin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.io.IOException;

/**
 * 代表一个功能
 */
public interface Function {
    //功能初始化
    /*default void init(BorderPane containter){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("pluginUI.fxml"));
        try {
            Parent parent = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    //执行功能
    void doAction();
}
