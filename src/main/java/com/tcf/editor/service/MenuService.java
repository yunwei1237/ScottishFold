package com.tcf.editor.service;

import com.tcf.editor.component.CodeEditorWithTip;
import com.tcf.editor.util.UIContext;
import javafx.scene.control.Tab;

import java.util.ArrayList;
import java.util.List;

public class MenuService {

    private UIContext context;

    public MenuService(UIContext context) {
        this.context = context;
    }

    /**
     * �½��˵�
     */
    public void newProject(){
        Tab tab = new Tab();
        tab.setText("Toll");
        tab.setContent(new CodeEditorWithTip());
        context.getTabPane().getTabs().add(tab);
        context.getTabPane().getSelectionModel().select(tab);
    }

    /**
     * �ر�
     */
    public void close(){
        context.getTabPane().getTabs().remove(context.getSelectedTab());
    }
    /**
     * �ر�ȫ��
     */
    public void closeAll(){
        context.getTabPane().getTabs().clear();
    }

    public void closeAllButThis(){
        List<Tab> list = new ArrayList<>();
        for(Tab tab :context.getTabPane().getTabs()){
            if(tab != context.getSelectedTab()){
                list.add(tab);
            }
        }
        context.getTabPane().getTabs().removeAll(list);

    }

    public void closeAllToLeft(){
        context.getTabPane().getTabs().remove(0,context.getSelectedTabIndex());
    }

    public void closeAllToRight(){
        context.getTabPane().getTabs().remove(context.getSelectedTabIndex()+1,context.getTabPane().getTabs().size());
    }

}
