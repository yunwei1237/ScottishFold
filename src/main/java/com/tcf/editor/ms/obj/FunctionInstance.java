package com.tcf.editor.ms.obj;

import com.tcf.editor.ms.Function;
import com.tcf.editor.ms.obj.util.FlagList;

import java.util.Arrays;
import java.util.List;

/**
 * This is instance of Function.class
 */
public class FunctionInstance {
    private Function function;
    private FlagList<String> params;

    public FunctionInstance(Function function, FlagList<String> params) {
        this.function = function;
        this.params = params;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(FlagList<String> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return function.getFunName() + "(" + params.join(",") +")";
    }

    public static void main(String[] args) {
        Function fun = new Function("weapon_length", Arrays.asList("x"));
        FunctionInstance call = new FunctionInstance(fun,FlagList.asList("200"));
        System.out.println(call);
    }
}
