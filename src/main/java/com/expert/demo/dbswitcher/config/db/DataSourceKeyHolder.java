package com.expert.demo.dbswitcher.config.db;

/**
 * The datasource key holder within each thread
 */
public class DataSourceKeyHolder {

    private static final ThreadLocal<String> DS_KEY_HOLDER = new ThreadLocal<>();

    public static String getKey() {
        String key = DS_KEY_HOLDER.get();
        return key == null ? DataSourceConstants.DS_KEY_PRIMARY : key;
    }

    public static void setKey(String key) {
        DS_KEY_HOLDER.set(key);
    }

    public static void clear() {
        DS_KEY_HOLDER.remove();
    }
}
