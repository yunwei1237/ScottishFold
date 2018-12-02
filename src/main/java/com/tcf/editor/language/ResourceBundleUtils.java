package com.tcf.editor.language;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleUtils {
    public static String baseName = "message";
    public static ResourceBundle getResourceBundle(){
        return ResourceBundle.getBundle(baseName, Locale.getDefault());
    }
}
