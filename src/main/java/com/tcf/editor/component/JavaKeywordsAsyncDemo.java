package com.tcf.editor.component;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;

public class JavaKeywordsAsyncDemo extends Application {

    //所有支持的关键字
    private static final String[] KEYWORDS = new String[] {
            "abstract", "assert", "boolean", "break", "byte",
            "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else",
            "enum", "extends", "final", "finally", "float",
            "for", "goto", "if", "implements", "import",
            "instanceof", "int", "interface", "long", "native",
            "new", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super",
            "switch", "synchronized", "this", "throw", "throws",
            "transient", "try", "void", "volatile", "while"
    };
    //关键字符号
    private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    //小括号符号
    private static final String PAREN_PATTERN = "\\(|\\)";
    //花括号符号
    private static final String BRACE_PATTERN = "\\{|\\}";
    //中括号符号
    private static final String BRACKET_PATTERN = "\\[|\\]";
    //语句结束符号
    private static final String SEMICOLON_PATTERN = "\\;";
    //字符串符号
    private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    //注释符号
    private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";
    //将关键字编译成正则表达式
    private static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                    + "|(?<PAREN>" + PAREN_PATTERN + ")"
                    + "|(?<BRACE>" + BRACE_PATTERN + ")"
                    + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                    + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                    + "|(?<STRING>" + STRING_PATTERN + ")"
                    + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
    );
    //例子
    private static final String sampleCode = String.join("\n", new String[] {
            "package com.example;",
            "",
            "import java.util.*;",
            "",
            "public class Foo extends Bar implements Baz {",
            "",
            "    /*",
            "     * multi-line comment",
            "     */",
            "    public static void main(String[] args) {",
            "        // single-line comment",
            "        for(String arg: args) {",
            "            if(arg.length() != 0)",
            "                System.out.println(arg);",
            "            else",
            "                System.err.println(\"Warning: empty string as argument\");",
            "        }",
            "    }",
            "",
            "}"
    });

    //主程序
    public static void main(String[] args) {
        launch(args);
    }
    //代码编辑区(文本编辑器)
    private CodeArea codeArea;
    //异步执行器（多线程）
    private ExecutorService executor;

    //开始前程序初始化
    @Override
    public void start(Stage primaryStage) {
        //创建一个多线程执行器
        executor = Executors.newSingleThreadExecutor();
        //创建一个代码编辑器
        codeArea = new CodeArea();
        //设置段落图形工场（显示行号）
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        //当结束之后清理
        Subscription cleanupWhenDone = codeArea.multiPlainChanges()
                .successionEnds(Duration.ofMillis(500))
                .supplyTask(this::computeHighlightingAsync)
                .awaitLatest(codeArea.multiPlainChanges())
                .filterMap(t -> {
                    if(t.isSuccess()) {
                        return Optional.of(t.get());
                    } else {
                        t.getFailure().printStackTrace();
                        return Optional.empty();
                    }
                })
                .subscribe(this::applyHighlighting);

        // call when no longer need it: `cleanupWhenFinished.unsubscribe();`
        //将例子替换到代码编辑区
        codeArea.replaceText(0, 0, sampleCode);
        //创建场景
        Scene scene = new Scene(new StackPane(new VirtualizedScrollPane<>(codeArea)), 600, 400);
        //指定关键字的样式
        scene.getStylesheets().add(JavaKeywordsAsyncDemo.class.getResource("java-keywords.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Java Keywords Async Demo");
        primaryStage.show();
    }
    //当应用程序关闭时，线程服务器也关闭
    @Override
    public void stop() {
        executor.shutdown();
    }
    //使用多线程（在后台）查找代码高亮
    private Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
        String text = codeArea.getText();
        Task<StyleSpans<Collection<String>>> task = new Task<StyleSpans<Collection<String>>>() {
            @Override
            protected StyleSpans<Collection<String>> call() throws Exception {
                return computeHighlighting(text);
            }
        };
        executor.execute(task);
        return task;
    }
    //应用代码高亮
    private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
        codeArea.setStyleSpans(0, highlighting);
    }
    //查找代码高亮

    /**
     *
     * StyleSpans:带样式的文本片段列表
     *
     * @param text
     * @return
     */
    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
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
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                            matcher.group("PAREN") != null ? "paren" :
                                    matcher.group("BRACE") != null ? "brace" :
                                            matcher.group("BRACKET") != null ? "bracket" :
                                                    matcher.group("SEMICOLON") != null ? "semicolon" :
                                                            matcher.group("STRING") != null ? "string" :
                                                                    matcher.group("COMMENT") != null ? "comment" :
                                                                            null;
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
