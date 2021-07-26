/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.materia;

import com.mvc.utils.Html;
import com.mvc.utils.IVista;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jon
 */
public class VMateria implements IVista {

    @Override
    public String getTitle() {
        return "Registrar Materia";
    }

    @Override
    public String linkToFindAndDelete(String id) {
        return Html.linkToFindAndDelete(
                "/mvc/materia/find",
                "/mvc/materia/delete",
                id
        );
    }

    @Override
    public String getTable(List<Map<String, String>> table) {
        String tr = "";
        for (int i = 0; i < table.size(); i++) {
            Map<String, String> row = table.get(i);
            tr += "<tr>";
            tr += "<td>" + row.getOrDefault("codigo", "") + "</td>";
            tr += "<td>" + row.getOrDefault("descripcion", "") + "</td>";
            tr += "<td>" + linkToFindAndDelete(row.getOrDefault("id", "0")) + "</td>";
            tr += "</tr>";
        }
        return tr;
    }

    @Override
    public void show(HttpServletRequest req, HttpServletResponse res, String template) {
        List<Map<String, String>> table = (List<Map<String, String>>) req.getAttribute("table");
        Map<String, String> modelo = (Map<String, String>) req.getAttribute("modelo");

        if (modelo != null) {
            req.setAttribute("id", modelo.getOrDefault("id", "0"));
            req.setAttribute("codigo", modelo.getOrDefault("codigo", ""));
            req.setAttribute("descripcion", modelo.getOrDefault("descripcion", ""));
        } else {
            req.setAttribute("id", "0");
        }

        req.setAttribute("table", getTable(table));
        req.setAttribute("title", getTitle());

        try {
            req.getRequestDispatcher(template).forward(req, res);
        } catch (Exception e) {
        }
    }
}
