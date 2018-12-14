package com.tcf.editor.util;

import java.io.*;

/**
 * @author Archer Tan
 */
public class FileUtils {
    public static void write(File file, String text,boolean append){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file,append);
            fos.write(text.getBytes());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void write(File file,String text){
        write(file, text, false);
    }
    public static String read(File file){
        StringBuffer sb = new StringBuffer();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] buf = new byte[4096];
            int len = -1;
            while((len = fis.read(buf)) != -1){
                sb.append(new String(buf,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
