package com.tcf.editor.util;

import com.sun.javafx.tk.Toolkit;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.junit.Test;



public class TestUtil {

   /* @Test
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
    }*/
   /* @Test
    public void loadPlugins(){

    }*/

    @Test
    public void testFont(){
        Font font = Toolkit.getToolkit().getFontLoader().font("ËÎÌו",FontWeight.BOLD, FontPosture.ITALIC,22);
        float width = Toolkit.getToolkit().getFontLoader().computeStringWidth("hello",font);
        System.out.println(width);
    }
}
