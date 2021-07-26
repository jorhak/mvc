/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.utils;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jon
 */
public interface IVista {

    public String getTitle();

    public String linkToFindAndDelete(String id);

    public String getTable(List<Map<String, String>> table);

    public void show(HttpServletRequest req, HttpServletResponse res, String template);
}
