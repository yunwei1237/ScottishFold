package com.tcf.editor.ms.obj;

public class ModuleString {
    /**
     * string id
     */
    private String stringId;
    /**
     * string content
     */
    private String content;

    public ModuleString(String stringId, String content) {
        this.stringId = stringId;
        this.content = content;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "(" +
                "\"" + stringId +
                "\", \"" + content + '\"' +
                ')';
    }

    public static void main(String[] args) {
        ModuleString str = new ModuleString("no_string","NO STRING!");
        System.out.println(str);

    }
}
