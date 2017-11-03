package com.zhang.chat.utils;

import java.util.List;
import java.util.Map;

public class ListUtil {
	/**
     * 判断list是否为空
     *
     * @param list
     * @return
     */
    public static boolean isNotEmpty(List list) {
        if (list != null && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(List list) {
        return !isNotEmpty(list);
    }

    /**
     * 判断map是否为空
     *
     * @param map
     * @return
     */
    public static boolean mapNotEmpty(Map map) {
        if (map != null && !map.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean mapEmpty(Map map) {
        return !mapNotEmpty(map);
    }

    /**
     * 判断map是否为空
     *
     * @param map
     * @return
     */
    public static boolean arrayMapNotEmpty(Map map) {
        if (map != null && !map.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean arrayMapEmpty(Map map) {
        return !arrayMapNotEmpty(map);
    }

}

