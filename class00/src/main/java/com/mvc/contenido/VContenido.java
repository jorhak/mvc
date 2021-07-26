/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.contenido;

import com.mvc.utils.Html;
import com.mvc.utils.IVista;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jon
 */
public class VContenido implements IVista {

    private String getMaterias(Map<String, String> data, String selected) {
        return Html.getComboBox(data, selected);
    }

    @Override
    public String getTitle() {
        return "Registrar Contenido";
    }

    @Override
    public String linkToFindAndDelete(String id) {
        return Html.linkToFindAndDelete(
                "/mvc/contenido/find",
                "/mvc/contenido/delete",
                id
        );
    }

    @Override
    public String getTable(List<Map<String, String>> table) {
        String tr = "";
        for (int i = 0; i < table.size(); i++) {
            Map<String, String> row = table.get(i);
            tr += "<tr>";
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
        Map<String, String> cargaMateria = (Map<String, String>) req.getAttribute("carga_materias");

        if (modelo != null) {
            req.setAttribute("id", modelo.getOrDefault("id", "0"));
            req.setAttribute("descripcion", modelo.getOrDefault("descripcion", ""));
            req.setAttribute("id_materia", modelo.getOrDefault("id_materia", ""));
        } else {
            req.setAttribute("id", "0");
            req.setAttribute("id_materia", "");
        }


        String carga_materias = getMaterias(cargaMateria, req.getAttribute("id_materia").toString());
        req.setAttribute("carga_materias", carga_materias);

        req.setAttribute("table", getTable(table));
        req.setAttribute("title", getTitle());

        try {
            req.getRequestDispatcher(template).forward(req, res);
        } catch (Exception e) {
        }
    }
}
