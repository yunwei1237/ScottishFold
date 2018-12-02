package com.tcf.editor.util;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.jar.JarFile;


public class TestUtil {

    @Test
    public void loadResources() throws Exception {
        URL url =  this.getClass().getClassLoader().getResource("plugins/delete.jar");
        JarFile jarFile = new JarFile(url.getFile());
        //JarFile jarFile = new JarFile("C:\\Users\\Administrator\\IdeaProjects\\ScottishFold\\src\\main\\resources\\plugins\\delete.jar");
        DynamicJarClassLoader loader = new DynamicJarClassLoader(jarFile);
        InputStream inputStream = loader.getResourceAsStream("pluginConfig.xml");
        SAXReader reader = new SAXReader();
        Document doc = reader.read(inputStream);
        System.out.println(doc.getRootElement().getName());
        inputStream.close();
    }
    @Test
    public void loadPlugins(){

    }
}
