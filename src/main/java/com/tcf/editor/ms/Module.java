package com.tcf.editor.ms;

import java.util.List;
import java.util.Map;

public class Module {
    /**
     * 模块的名称，也是文件的名字
     */
    private String moduleName;
    /**
     * 该模块所要导入的其它模块（也就是依赖的模块）
     */
    private List<Module> impors;
    /**
     * 该模块所声明的变量
     */
    private Map<String,String> vars;
    /**
     * 该模块所声明的函数
     */
    private Map<String,String> funs;
    /**
     * 模块信息所属的变量名
     */
    private String subModuleVarName;
    /**
     * 模块信息(可编辑)
     */
    private Map<String, Map<InfoName,Object>> subModulesEditable;

    private List<Map<String,Map<InfoName,Object>>> modules;
}
