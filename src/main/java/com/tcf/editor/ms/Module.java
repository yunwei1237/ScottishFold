package com.tcf.editor.ms;

import java.util.List;
import java.util.Map;

public class Module {
    /**
     * ģ������ƣ�Ҳ���ļ�������
     */
    private String moduleName;
    /**
     * ��ģ����Ҫ���������ģ�飨Ҳ����������ģ�飩
     */
    private List<Module> impors;
    /**
     * ��ģ���������ı���
     */
    private Map<String,String> vars;
    /**
     * ��ģ���������ĺ���
     */
    private Map<String,String> funs;
    /**
     * ģ����Ϣ�����ı�����
     */
    private String subModuleVarName;
    /**
     * ģ����Ϣ(�ɱ༭)
     */
    private Map<String, Map<InfoName,Object>> subModulesEditable;

    private List<Map<String,Map<InfoName,Object>>> modules;
}
