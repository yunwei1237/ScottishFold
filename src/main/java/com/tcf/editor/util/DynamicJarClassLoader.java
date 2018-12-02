package com.tcf.editor.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * 动态类加载器
  * 
  */
public class DynamicJarClassLoader extends ClassLoader {

    private static Logger logger = LoggerFactory.getLogger(DynamicJarClassLoader.class);


    private JarFile jarFile;


    private ClassLoader parent;


    public DynamicJarClassLoader(JarFile jarFile) {
        super(Thread.currentThread().getContextClassLoader());
        this.parent = Thread.currentThread().getContextClassLoader();
        this.jarFile = jarFile;
    }


    public DynamicJarClassLoader(JarFile jarFile, ClassLoader parent) {
        super(parent);
        this.parent = parent;
        this.jarFile = jarFile;
    }


    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        try {
            Class<?> c = null;
            String path = name.replace('.', '/').concat(".class");
            if(null != jarFile){
                JarEntry entry = jarFile.getJarEntry(path);
                if(null != entry){
                    InputStream is = jarFile.getInputStream(entry);
                    int availableLen = is.available();
                    int len = 0;
                    byte[] bt1 = new byte[availableLen];
                    while (len < availableLen) {
                        len += is.read(bt1, len, availableLen - len);
                    }
                    c = defineClass(name, bt1, 0, bt1.length);
                    if (resolve) {
                        resolveClass(c);
                    }
                }else{
                    if (parent != null) {
                        return parent.loadClass(name);
                    }
                }
            }
            return c;
        } catch (IOException e) {
            throw new ClassNotFoundException("Class " + name + " not found.");
        }
    }


    @Override
    public InputStream getResourceAsStream(String name) {
        InputStream is = null;
        try {
            if(null != jarFile){
                JarEntry entry = jarFile.getJarEntry(name);
                if (entry != null) {
                    is = jarFile.getInputStream(entry);
                }
                if (is == null) {
                    is = super.getResourceAsStream(name);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return is;
    }
}