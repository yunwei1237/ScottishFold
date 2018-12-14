package com.tcf.editor.component;

import javafx.scene.control.ListView;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeEditor extends CodeArea {
    private CodeLanguage language;
    //将关键字编译成正则表达式
    private Pattern PATTERN = null;
    public CodeEditor(CodeLanguage language) {
        this.language = language;
        PATTERN = language.getPattern();
        this.showNumberLines();
        //当结束之后清理
        Subscription cleanupWhenNoLongerNeedIt = this
                .multiPlainChanges()
                .successionEnds(Duration.ofMillis(100))
                .subscribe(ignore -> this.setStyleSpans(0, computeHighlighting(this.getText())));
        //指定关键字的样式
        this.getStylesheets().add(language.getStyleFilePath());
    }
    /**
     * 添加代码内容
     * @param code
     */
    public void setText(String code){
        this.replaceText(0, 0, code);
    }

    /**
     * 显示行号
     */
    public void showNumberLines(){
        //设置段落图形工场（显示行号）
        this.setParagraphGraphicFactory(LineNumberFactory.get(this));
    }

    /**
     * 隐藏行号
     */
    public void hideNumberLines(){
        //设置段落图形工场（显示行号）
        this.setParagraphGraphicFactory(null);
    }
    //查找代码高亮

    /**
     *
     * StyleSpans:带样式的文本片段列表
     *
     * @param text
     * @return
     */
    private StyleSpans<Collection<String>> computeHighlighting(String text) {
        //使用正则表达式匹配
        Matcher matcher = PATTERN.matcher(text);
        //上一个关键字的结束为0
        int lastKwEnd = 0;
        //新建一个带样式文本片段的建造器
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        //循环查找所有关键字
        while(matcher.find()) {
            //根据关键字类型查找对应关键字样式
            String styleClass = null;
            for(String name : language.getNames()){
                if(matcher.group(name) != null){
                    styleClass = name;
                }
            }

            /*String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                            matcher.group("PAREN") != null ? "paren" :
                                    matcher.group("BRACE") != null ? "brace" :
                                            matcher.group("BRACKET") != null ? "bracket" :
                                                    matcher.group("SEMICOLON") != null ? "semicolon" :
                                                            matcher.group("STRING") != null ? "string" :
                                                                    matcher.group("COMMENT") != null ? "comment" :
                                                                            null;*/
            /* never happens */
            assert styleClass != null;

            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);//没有格式的片段
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());//有格式的片段
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);//没有格式的片段
        return spansBuilder.create();
    }
}
