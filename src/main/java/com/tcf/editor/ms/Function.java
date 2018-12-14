package com.tcf.editor.ms;

import java.util.List;

public class Function {
    /**
     * 函数名称
     */
    private String funName;
    /**
     * 函数需要的参数
     */
    private List<String> paramNames;
    /**
     * 函数返回值类型
     */
    private Class returnType;
    /**
     * 函数的描述信息
     */
    private String description;

    public Function(String funName) {
        this(funName,null,null,null);
    }

    public Function(String funName, List<String> paramNames) {
        this(funName,paramNames,null,null);
    }

    public Function(String funName, List<String> paramNames, Class returnType) {
        this(funName,paramNames,returnType,null);
    }

    public Function(String funName, List<String> paramNames, Class returnType, String description) {
        this.funName = funName;
        this.paramNames = paramNames;
        this.returnType = returnType;
        this.description = description;
    }


    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public List<String> getparamNames() {
        return paramNames;
    }

    public void setparamNames(List<String> paramNames) {
        this.paramNames = paramNames;
    }

    public Class getReturnType() {
        return returnType;
    }

    public void setReturnType(Class returnType) {
        this.returnType = returnType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
