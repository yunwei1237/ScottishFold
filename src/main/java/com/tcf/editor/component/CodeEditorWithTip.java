package com.tcf.editor.component;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import org.fxmisc.richtext.model.PlainTextChange;
import org.fxmisc.richtext.model.StyleSpan;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.TwoDimensional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CodeEditorWithTip extends StackPane {
    private ListView listView;
    private CodeEditor codeEditor;
    private List<String> datas;
    public CodeEditorWithTip() {
        this.init();
        ScrollPane pane = new ScrollPane(codeEditor);
        pane.setFitToHeight(true);
        pane.setFitToWidth(true);
        this.getChildren().add(pane);
        this.getChildren().add(listView);
    }

    public void init(){
        this.setAlignment(Pos.TOP_LEFT);//设置左上角对齐
         //列表信息
        listView = new ListView();
        listView.setVisible(false);
        this.setListViewSize(120,200);
        //this.setListViewLocation(0,0);
        //编辑器信息
        codeEditor = new CodeEditor(new CodeLanguage(CodeEditorWithTip.class.getClassLoader().getResourceAsStream("languages/python.xml")));
        codeEditor.multiPlainChanges().successionEnds(Duration.ofMillis(50)).subscribe(new Consumer<List<PlainTextChange>>() {
            @Override
            public void accept(List<PlainTextChange> plainTextChanges) {
                /*int posi = codeEditor.getCaretColumn();
                int para = codeEditor.getCurrentParagraph();
                int index = codeEditor.getAbsolutePosition(para,posi);
                CodeEditorWithTip.this.setListViewLocation(posi,para);
                CodeEditorWithTip.this.setListViewItems(""+index);
                CodeEditorWithTip.this.listView.setVisible(true);*/
                /*Bounds posi = codeEditor.getLayoutBounds();
                CodeEditorWithTip.this.setListViewLocation(posi.getMaxX()+100,posi.getMaxY()+100);
                CodeEditorWithTip.this.setListViewItems(""+posi);
                System.out.println(posi);
                CodeEditorWithTip.this.listView.setVisible(true);*/
                int pa = codeEditor.getCurrentParagraph();
                String str = codeEditor.getText(pa);
                System.out.println(str);
                StyleSpans span = codeEditor.getStyleSpans(pa);
                List style = (List)span.getStyleSpan(0).getStyle();
                if(style == null || style.size() == 0){
                    float width = getWidth(str,Font.getDefault());
                    float height = getHeight(pa,Font.getDefault());
                    CodeEditorWithTip.this.setListViewLocation(width,height);
                    CodeEditorWithTip.this.setListViewItems(width+"");
                }
                CodeEditorWithTip.this.listView.setVisible(true);
            }
        });
        FontLoader toolkit = Toolkit.getToolkit().getFontLoader();
        //数据信息
        datas = new ArrayList<>();
    }
    public float getWidth(String text,Font font){
        FontMetrics fontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(font);
        float perWidth = fontMetrics.computeStringWidth("a");
        float width = 0;
        for(char chr : text.toCharArray()){
            if(chr == '\t'){
                width += perWidth*8;
            }else{
                width += perWidth;
            }
        }
        return width;
    }
    public float getHeight(int rowNumber,Font font){
        FontMetrics fontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(Font.getDefault());
        return (fontMetrics.getLineHeight()+1)*(rowNumber+1);
    }
    public void setListViewLocation(double x,double y){
        StackPane.setMargin(listView,new Insets(y,0,0,x));
    }

    public void setListViewSize(double w,double h){
        listView.setPrefWidth(w);
        listView.setPrefHeight(h);
        listView.setMaxWidth(w);
        listView.setMaxHeight(h);
    }
    public void setListViewItems(String...items){
        listView.getItems().addAll(items);
    }
    public void addData(String data){
        datas.addAll(Arrays.asList(data.split("\\s")));
    }
}
