package com.tcf.editor.ms.obj.util;

import java.util.*;

public class FlagList<E> extends ArrayList<E>  {

    @Override
    public String toString() {
        return join("|","");
    }

    public String join(String sep){
        return join(sep,"");
    }
    public String join(String sep,String wrap){
        StringBuffer sb = new StringBuffer();
        if(this.size()>0) {
            for (int i = 0; i < this.size() - 1; i++) {
                sb.append(String.format("%s%s%s %s ",wrap,this.get(i),wrap,sep));
            }
            sb.append(String.format("%s%s%s",wrap,this.get(this.size() - 1),wrap));
        }else{
            sb.append("0");//no flag
        }
        return sb.toString();
    }
    public static <E> FlagList<E> asList(E...elements){
        FlagList<E> flagList = new FlagList<>();
        for(E e : elements){
            flagList.add(e);
        }
        return flagList;
    }
    public static FlagList Empty(){
        return new FlagList();
    }
    public static void main(String[] args) {
        FlagList<String> str = new FlagList<>();
        str.add("imodbit_tattered");
        str.add("imodbit_ragged");
        str.add("imodbit_sturdy");
        str.add("imodbit_thick");
        System.out.println(str);
    }
}
