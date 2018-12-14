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
    //���ؼ��ֱ����������ʽ
    private Pattern PATTERN = null;
    public CodeEditor(CodeLanguage language) {
        this.language = language;
        PATTERN = language.getPattern();
        this.showNumberLines();
        //������֮������
        Subscription cleanupWhenNoLongerNeedIt = this
                .multiPlainChanges()
                .successionEnds(Duration.ofMillis(100))
                .subscribe(ignore -> this.setStyleSpans(0, computeHighlighting(this.getText())));
        //ָ���ؼ��ֵ���ʽ
        this.getStylesheets().add(language.getStyleFilePath());
    }
    /**
     * ��Ӵ�������
     * @param code
     */
    public void setText(String code){
        this.replaceText(0, 0, code);
    }

    /**
     * ��ʾ�к�
     */
    public void showNumberLines(){
        //���ö���ͼ�ι�������ʾ�кţ�
        this.setParagraphGraphicFactory(LineNumberFactory.get(this));
    }

    /**
     * �����к�
     */
    public void hideNumberLines(){
        //���ö���ͼ�ι�������ʾ�кţ�
        this.setParagraphGraphicFactory(null);
    }
    //���Ҵ������

    /**
     *
     * StyleSpans:����ʽ���ı�Ƭ���б�
     *
     * @param text
     * @return
     */
    private StyleSpans<Collection<String>> computeHighlighting(String text) {
        //ʹ��������ʽƥ��
        Matcher matcher = PATTERN.matcher(text);
        //��һ���ؼ��ֵĽ���Ϊ0
        int lastKwEnd = 0;
        //�½�һ������ʽ�ı�Ƭ�εĽ�����
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        //ѭ���������йؼ���
        while(matcher.find()) {
            //���ݹؼ������Ͳ��Ҷ�Ӧ�ؼ�����ʽ
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

            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);//û�и�ʽ��Ƭ��
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());//�и�ʽ��Ƭ��
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);//û�и�ʽ��Ƭ��
        return spansBuilder.create();
    }
}
