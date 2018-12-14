package com.tcf.editor.ms;

public class InfoName {
    /**
     * 要显示的名称
     */
    private String showName;
    /**
     * 在剧本对象中的位置
     */
    private int index;

    public InfoName(String showName, int index) {
        this.showName = showName;
        this.index = index;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
