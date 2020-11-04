package dev.pinter.rsaapp.core;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {
    private static final Object lock = new Object();
    private static Language instance = null;

    private Locale currentLocale = Locale.ENGLISH;

    private Language() {
    }

    public static Language get() {
        Language c = instance;
        if (c == null) {
            synchronized (lock) {
                c = instance;
                if (c == null) {
                    c = new Language();
                    instance = c;
                }
            }
        }
        return instance;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public String getMsg(String key) {
        return getMsg(key, currentLocale);
    }

    public String getMsg(String key, Locale locale) {
        ResourceBundle bundle = getResourceBundleUI(locale);
        if (key != null && bundle.containsKey(key)) {
            return bundle.getString(key);
        }

        return null;
    }

    public ResourceBundle getResourceBundleUI() {
        return getResourceBundleUI(currentLocale);
    }

    public ResourceBundle getResourceBundleUI(Locale locale) {
        return ResourceBundle.getBundle(Constants.RESOURCE_BUNDLE_UI, locale);
    }
}
