/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.utils;

import java.util.Map;

/**
 *
 * @author jon
 */
public class Html {

    public static String linkToFindAndDelete(String urlFind, String urlDelete, String id) {
        String td = "<a href='" + urlDelete + "?id=" + id + "'> &#x274c;&nbsp;</a>";
        td += "<a href='" + urlFind + "?id=" + id + "'>&nbsp;&#x1f50e;</a>";
        return td;
    }

    public static String getComboBox(Map<String, String> data, String valueSelected) {
        String select = "";
        for (Map.Entry<String, String> option : data.entrySet()) {
            String key = option.getKey();
            String selected = (key.equals(valueSelected)) ? "' selected >" : "'>";
            select += "<option value='" + key + selected + option.getValue() + "</option>";
        }
        return select;
    }
}
