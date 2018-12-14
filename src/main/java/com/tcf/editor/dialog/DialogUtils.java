package com.tcf.editor.dialog;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 *
 * 对话框工具类
 * @author Archer Tan
 */
public class DialogUtils {
    private static final String TITLE = "系统提示";
    public static Stage stage;

    /**
     * 通用的弹框
     * @param stage
     * @param alertType
     * @param title
     * @param header
     * @param content
     * @param buttonTypes
     * @return
     */
    public static Optional<ButtonType> dialog(Stage stage, Alert.AlertType alertType, String title, String header, String content, ButtonType...buttonTypes){
        Alert alert = new Alert(alertType, content,buttonTypes);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.initOwner(stage);
        return alert.showAndWait();
    }

    /**
     * 警告框
     * 按钮（确定）
     * @param content
     */
    public static void alert(String content){
        DialogUtils.dialog(stage, Alert.AlertType.WARNING, DialogUtils.TITLE, null, content,null);
    }
    /**
     * 信息框
     * 按钮（确定）
     * @param content
     */
    public static void info(String content){
        DialogUtils.dialog(stage, Alert.AlertType.INFORMATION, DialogUtils.TITLE, null, content,null);
    }

    /**
     * 中止框
     * 按钮（保存，不保存，取消）
     * @param content
     * @return
     */
    public static Optional<ButtonType> abort(String content){
        return DialogUtils.dialog(stage, Alert.AlertType.NONE, DialogUtils.TITLE, null, content,new ButtonType("保存", ButtonBar.ButtonData.YES),new ButtonType("不保存", ButtonBar.ButtonData.NO),new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE));
    }

    /**
     * 保存对话框
     * @param filters
     * @return
     */
    public static File saveDialog(FileChooser.ExtensionFilter...filters){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(filters);
        return fileChooser.showSaveDialog(stage);
    }

    /**
     * 打开对话框（单个文件）
     * @param filters
     * @return
     */
    public static File openDialog(FileChooser.ExtensionFilter...filters){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(filters);
        return fileChooser.showOpenDialog(stage);
    }

    /**
     * 打开对话框（多个文件）
     * @param filters
     * @return
     */
    public static List<File> openMultipleDialog(FileChooser.ExtensionFilter...filters){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(filters);
        return fileChooser.showOpenMultipleDialog(stage);
    }

    /**
     * 查找对话框
     * @param content
     * @return
     */
    public static FindDialog openFindDialog(TextArea content){
        return new FindDialog(stage,content);
    }

    /**
     * 替换对话框
     * @param content
     * @return
     */
    public static ReplaceDialog openReplaceDialog(TextArea content){
        return new ReplaceDialog(stage,content);
    }

    /**
     * 跳转对话框
     * @param content
     * @return
     */
    public static GotoDialog openGotoDialog(TextArea content){
        return new GotoDialog(stage,content);
    }

    public static FontDialog openFontDialog(TextArea content){
        return new FontDialog(stage, content);
    }
}
