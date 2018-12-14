package com.tcf.editor.util;

import javafx.event.EventTarget;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarFile;

public class PluginsLoader {
    /*public List<DynamicJarClassLoader> loaderJar(){
        List<DynamicJarClassLoader> list = new ArrayList<DynamicJarClassLoader>();
        String path =  this.getClass().getClassLoader().getResource("plugins").getPath();
        File dir = new File(path);
        for(File file : dir.listFiles()){
            JarFile jarFile = null;
            try {
                jarFile = new JarFile(file);
                list.add(new DynamicJarClassLoader(jarFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public List<Plugin> parseJar(DynamicJarClassLoader loader){
        //���������ļ�
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(loader.getResourceAsStream("pluginConfig.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //���������ļ�
        List<Plugin> list = new ArrayList<Plugin>();
        Element root = document.getRootElement();
        Iterator<Element> plugins = root.element("plugins").elementIterator("plugin");
        while(plugins.hasNext()){
            Element pluginEle = plugins.next();
            String parent = null;
            int index = -1;
            EventTarget view = null;
            Class controller = null;
            //content
            Element content = pluginEle.element("content");
            FXMLLoader fxmlLoader = new FXMLLoader();
            try {
                fxmlLoader.setController(controller);
                fxmlLoader.setRoot(controller);
                view = fxmlLoader.load(loader.getResourceAsStream(content.attributeValue("view")));
                controller = loader.loadClass(content.attributeValue("controller"));
            }catch (Exception e){
                e.printStackTrace();
            }
            //location
            Element location = pluginEle.element("location");
            Element toolBar = location.element("toolBar");
            Element mainMenu = location.element("mainMenu");
            Element contextMenu = location.element("contextMenu");

            if(toolBar != null){
                list.add(Plugin.getToolBarPlugin(index,view));
            }else if(mainMenu != null){
                list.add(Plugin.getMainMenuPlugin(parent,index,view));
            }else if(contextMenu != null){
                list.add(Plugin.getContextMenuPlugin(parent,index,view));
            }
        }
        return list;
    }
    public List<Plugin> parseJar(String configPath){
        //���������ļ�
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(configPath);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //���������ļ�
        List<Plugin> list = new ArrayList<Plugin>();
        Element root = document.getRootElement();
        Iterator<Element> plugins = root.element("plugins").elementIterator("plugin");
        while(plugins.hasNext()){
            Element pluginEle = plugins.next();
            String parent = null;
            int index = -1;
            EventTarget view = null;
            Class controller = null;
            //content
            Element content = pluginEle.element("content");
            FXMLLoader fxmlLoader = new FXMLLoader();
            try {
                fxmlLoader.setController(controller);
                fxmlLoader.setRoot(controller);
                view = fxmlLoader.load(this.getClass().getResourceAsStream(content.attributeValue("view")));
                controller = Class.forName(content.attributeValue("controller"));
            }catch (Exception e){
                e.printStackTrace();
            }
            //location
            Element location = pluginEle.element("location");
            Element toolBar = location.element("toolBar");
            Element mainMenu = location.element("mainMenu");
            Element contextMenu = location.element("contextMenu");

            if(toolBar != null){
                list.add(Plugin.getToolBarPlugin(index,view));
            }else if(mainMenu != null){
                list.add(Plugin.getMainMenuPlugin(parent,index,view));
            }else if(contextMenu != null){
                list.add(Plugin.getContextMenuPlugin(parent,index,view));
            }
        }
        return list;
    }
    public void addPlugins(BorderPane borderPane){
        for(DynamicJarClassLoader loader:loaderJar()){
            List<Plugin> plugins = parseJar(loader);
            for(Plugin plugin:plugins){
                if(plugin.location instanceof Plugin.MainMenuLocation){//���������˵�
                    MenuBar menuBar = (MenuBar) borderPane.lookup("#menuBar");
                    menuBar.getMenus().get(3).getItems().add((MenuItem)plugin.content);
                }else if(plugin.location instanceof  Plugin.ContextMenuLocation){//�����������Ĳ˵�
                    TabPane tabPane = (TabPane)borderPane.lookup("#TabPane");
                    tabPane.getContextMenu().getItems().add((MenuItem)plugin.content);
                }else if(plugin.location instanceof  Plugin.ToolBarLocation){//�����Թ�����
                    ToolBar toolBar = (ToolBar)borderPane.lookup("#toolBar");
                    toolBar.getItems().add((Node)plugin.content);
                }
            }
        }
    }*/
}
