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
 * �Ի��򹤾���
 * @author Archer Tan
 */
public class DialogUtils {
    private static final String TITLE = "ϵͳ��ʾ";
    public static Stage stage;

    /**
     * ͨ�õĵ���
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
     * �����
     * ��ť��ȷ����
     * @param content
     */
    public static void alert(String content){
        DialogUtils.dialog(stage, Alert.AlertType.WARNING, DialogUtils.TITLE, null, content,null);
    }
    /**
     * ��Ϣ��
     * ��ť��ȷ����
     * @param content
     */
    public static void info(String content){
        DialogUtils.dialog(stage, Alert.AlertType.INFORMATION, DialogUtils.TITLE, null, content,null);
    }

    /**
     * ��ֹ��
     * ��ť�����棬�����棬ȡ����
     * @param content
     * @return
     */
    public static Optional<ButtonType> abort(String content){
        return DialogUtils.dialog(stage, Alert.AlertType.NONE, DialogUtils.TITLE, null, content,new ButtonType("����", ButtonBar.ButtonData.YES),new ButtonType("������", ButtonBar.ButtonData.NO),new ButtonType("ȡ��", ButtonBar.ButtonData.CANCEL_CLOSE));
    }

    /**
     * ����Ի���
     * @param filters
     * @return
     */
    public static File saveDialog(FileChooser.ExtensionFilter...filters){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(filters);
        return fileChooser.showSaveDialog(stage);
    }

    /**
     * �򿪶Ի��򣨵����ļ���
     * @param filters
     * @return
     */
    public static File openDialog(FileChooser.ExtensionFilter...filters){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(filters);
        return fileChooser.showOpenDialog(stage);
    }

    /**
     * �򿪶Ի��򣨶���ļ���
     * @param filters
     * @return
     */
    public static List<File> openMultipleDialog(FileChooser.ExtensionFilter...filters){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(filters);
        return fileChooser.showOpenMultipleDialog(stage);
    }

    /**
     * ���ҶԻ���
     * @param content
     * @return
     */
    public static FindDialog openFindDialog(TextArea content){
        return new FindDialog(stage,content);
    }

    /**
     * �滻�Ի���
     * @param content
     * @return
     */
    public static ReplaceDialog openReplaceDialog(TextArea content){
        return new ReplaceDialog(stage,content);
    }

    /**
     * ��ת�Ի���
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
