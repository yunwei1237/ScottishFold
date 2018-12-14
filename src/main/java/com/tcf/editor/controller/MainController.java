package com.tcf.editor.controller;

import com.tcf.editor.service.MenuService;
import com.tcf.editor.util.UIContext;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class MainController {

    @FXML
    private BorderPane borderPane;

    public MainController() {

    }

    @FXML
    private MenuItem newProject,open,openFolder,saveAs,saveAll;
    @FXML
    private MenuItem close,closeAll,closeAllButThis,closeAllToLeft,closeAllToRight;
    @FXML
    private MenuItem print,printNow;
    @FXML
    private MenuItem recentFile,exit;
    @FXML
    private MenuItem undo,redo,cut,copy,paste,delete,selectAll,find,findNext,findPrev;
    @FXML
    private MenuItem copyToClipboard,copyFilePathToClipboard,copyFileNameToClipboard,copyFileTextToClipboard,copyFileDirToClipboard;
    @FXML
    private MenuItem indent,increaseLineIndent,decreaseLineIndent;
    @FXML
    private MenuItem convertCaseTo,uppercase,lowercase,togglecase;
    @FXML
    private MenuItem duplicateCurrentLine,splitLines,joinLines,moveUpCurrentLines,moveDownCurrentLines,removeEmptyLines,insertBlankLineAboveCurrentLine,insertBlankLineBelowCurrentLine;
    @FXML
    private MenuItem toggleSingleLineComment,toggleBlockComment;
    @FXML
    private MenuItem trimTrailingSpace,trimLeadingSpace,trimLeadingAndTrailingSpace,tabToSpace,spaceToTab;
    @FXML
    private MenuItem encodeInANSI,encodeInGBK,encodeInUTF8,encodeInUTF8BOM,charsets,convertToANSI,convertToGBK,convertToUTF8,convertToUTF8BOM;
    @FXML
    private MenuItem setReadonly;

    @FXML
    private MenuItem preferences,language,editMainMenu,editContextMenu,quickText;

    @FXML
    private MenuItem pluginManager;
    @FXML
    private MenuItem about;
    public void init(){
        UIContext uiContext = new UIContext(borderPane);
        MenuService ms = new MenuService(uiContext);
        newProject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ms.newProject();
            }
        });
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ms.close();
            }
        });
        closeAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ms.closeAll();
            }
        });

        closeAllButThis.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ms.closeAllButThis();
            }
        });
        closeAllToLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ms.closeAllToLeft();
            }
        });
        closeAllToRight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ms.closeAllToRight();
            }
        });
    }

}
