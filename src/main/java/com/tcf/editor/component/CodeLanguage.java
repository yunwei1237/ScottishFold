package com.tcf.editor.component;

import com.tcf.editor.util.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 代表某一个代码语言的样式
 */
public class CodeLanguage {
    private String languageName;
    private Map<String,CodeMark> marks = new HashMap<>();
    private String styleFile;
    public CodeLanguage(String languageName) {
        this.languageName = languageName;
    }
    public CodeLanguage(String languageName,String configPath) {
        File cfgFile = new File(configPath);
        if(!cfgFile.exists()){
            throw new IllegalArgumentException("File is not exists:"+configPath);
        }
        try {
            this.parseLanguageConfig(new FileInputStream(cfgFile));
            generatFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public CodeLanguage(InputStream inputStream) {
        this.parseLanguageConfig(inputStream);
        generatFile();

    }

    private void generatFile(){
        String fileName = languageName+".css";
        //避免多次写入文件
        if(this.getClass().getResource(fileName) == null){
            String style = getStyle();
            String tempPath = this.getClass().getResource("").getPath();
            File file = new File(tempPath+fileName);
            FileUtils.write(file,style);
        }
        styleFile = this.getClass().getResource(fileName).toExternalForm();
    }

    public void setMark(String name, String style, String[] signs){
        marks.put(name,new CodeMark(name,style,signs));
    }
    public void removeMark(String name){
        marks.remove(name);
    }

    public void setStyle(String name,String style){
        marks.get(name).setStyle(style);
    }

    public String getStyle(){
        StringBuffer sb = new StringBuffer();
        for(CodeMark mark:marks.values()){
            sb.append(String.format(".%s{\n%s\n} \n",mark.getName(),mark.getStyle()));
        }
        return sb.toString()+"\n.paragraph-box:has-caret{\n-fx-background-color: #f2f9fc;\n}";
    }
    public String getStyleFilePath(){
        return styleFile;
    }

    public Pattern getPattern(){
        List<String> patterns = new ArrayList<>();
        for(CodeMark mark : marks.values()){
            patterns.add(String.format("(?<%s>%s)",mark.getName(),mark.getSigns()[0]));
        }
        return Pattern.compile(String.join("|",patterns.toArray(new String[]{})));
    }

    public String[] getNames(){
       return marks.keySet().toArray(new String[]{});
    }

    private void parseLanguageConfig(InputStream inputStream){
        if(inputStream == null){
            throw new IllegalArgumentException("inputStream is null");
        }
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(inputStream);
            Element root = doc.getRootElement();
            languageName = root.element("languageName").getText();
            List<Element> marks =  root.elements("mark");
            for(Element mark:marks){
                String name = mark.attributeValue("name");
                String style = "-fx-fill:black;";
                Element colorEle = mark.element("color");
                if(colorEle != null) {
                    style = String.format("-fx-fill:%s;",colorEle.attributeValue("colorName"));
                    if (style == null){
                        style = String.format("-fx-fill:rgb(%s,%s,%s);",colorEle.attributeValue("r"),colorEle.attributeValue("g"),colorEle.attributeValue("b"));
                    }
                }
                String[] signs = {};
                Element signsEle = mark.element("signs");
                if(signsEle != null){
                    String signStr = signsEle.attributeValue("value");
                    if(signStr == null){
                        Element value = signsEle.element("value");
                        signStr = value.getText();
                    }
                    if(signStr != null && signStr.trim().length() != 0){
                        signs = signStr.split(",");
                    }
                }
                this.setMark(name,style,signs);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}

/**             );
 * 保存代码标记的信息
 */
class CodeMark {
    private String name;
    private String[] signs;
    private String style;

    public CodeMark(String name, String style,String[] signs) {
        this.name = name;
        this.style = style;
        this.signs = signs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSigns() {
        return signs;
    }

    public void setSigns(String[] signs) {
        this.signs = signs;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}