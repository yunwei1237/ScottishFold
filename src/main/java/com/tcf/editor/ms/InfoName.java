package com.tcf.editor.ms;

public class InfoName {
    /**
     * Ҫ��ʾ������
     */
    private String showName;
    /**
     * �ھ籾�����е�λ��
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
