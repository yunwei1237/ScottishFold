package com.tcf.editor.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PluginsParser {
    /*public List<Plugin> parse(DynamicJarClassLoader loader){
        //加载配置文件
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(loader.getResourceAsStream("pluginConfig.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //解析配置文件
        List<Plugin> list = new ArrayList<Plugin>();
        Element root = document.getRootElement();
        Iterator<Element> plugins = root.element("plugins").elementIterator("plugin");
        while(plugins.hasNext()){
            Element pluginEle = plugins.next();
            String parent = null;
            int index = -1;
            Parent view = null;
            Class controller = null;
            //content
            Element content = pluginEle.element("content");
            FXMLLoader fxmlLoader = new FXMLLoader();
            try {
                fxmlLoader.load(loader.getResourceAsStream(content.attributeValue("view")));
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
    }*/
}
