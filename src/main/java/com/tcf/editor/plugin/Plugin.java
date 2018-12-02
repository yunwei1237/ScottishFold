package com.tcf.editor.plugin;

import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.Parent;

public class Plugin {
    /**
     * 插件位置
     */
    public Location location ;
    /**
     * 插件内容
     */
    public EventTarget content;

    private Plugin(Location location, EventTarget content) {
        this.location = location;
        this.content = content;
    }

    /**
     * 生成主菜单插件对象
     * @param parent
     * @param index
     * @param content
     * @return
     */
    public static Plugin getMainMenuPlugin(String parent, int index, EventTarget content){
        return new Plugin(new MainMenuLocation(index,parent),content);
    }

    /**
     * 生成上下文菜单插件对象
     * @param parent
     * @param index
     * @param content
     * @return
     */
    public static Plugin getContextMenuPlugin(String parent, int index, EventTarget content){
        return new Plugin(new ContextMenuLocation(index,parent),content);
    }

    /**
     * 生成工具栏插件对象
     * @param index
     * @param content
     * @return
     */
    public static Plugin getToolBarPlugin(int index, EventTarget content){
        return new Plugin(new ToolBarLocation(index),content);
    }
    /**
     * 代表插件所在位置
     */
    static class Location{
        public int index;

        public Location(int index) {
            this.index = index;
        }
    }

    /**
     * 该插件是为主菜单设计
     */
    public static class MainMenuLocation extends Location{
        public String parent;

        public MainMenuLocation(int index, String parent) {
            super(index);
            this.parent = parent;
        }
    }

    /**
     * 该插件是为上下文菜单设计
     */
    public static class ContextMenuLocation extends Location{
        public String parent;

        public ContextMenuLocation(int index, String parent) {
            super(index);
            this.parent = parent;
        }
    }

    /**
     * 该插件是为了工具栏设计
     */
    public static class ToolBarLocation extends Location{
        public ToolBarLocation(int index) {
            super(index);
        }
    }
}


