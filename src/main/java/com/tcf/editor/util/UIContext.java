package com.tcf.editor.util;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class UIContext {
    private BorderPane borderPane;
    public UIContext(BorderPane borderPane){
        this.borderPane = borderPane;
    }
    public TabPane getTabPane(){
        return (TabPane)borderPane.lookup("#tabs");
    }
    /**
     * 获得当前选中的tab面板
     * @return
     */
    public Tab getSelectedTab(){
        for(Tab tab : getTabPane().getTabs()){
            if(tab.isSelected()){
                return tab;
            }
        }
        return null;
    }
    public int getSelectedTabIndex(){
        List<Tab> list = getTabPane().getTabs();
        for(int i = 0;i<list.size();i++){
            if(list.get(i).isSelected()){
                return i;
            }
        }
        return -1;
    }
    public TreeView getTreeView(){
        return (TreeView) borderPane.lookup("#projectInfo");
    }

    public ListView getListView(){
        return (ListView) borderPane.lookup("#listView");
    }

    public Node getTop(){
        return borderPane.getTop();
    }
    public Node getBottom(){
        return borderPane.getBottom();
    }
    public Node getLeft(){
        return borderPane.getLeft();
    }
    public Node getRight(){
        return borderPane.getRight();
    }
    public Node getCenter(){
        return borderPane.getCenter();
    }

}
