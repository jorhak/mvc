/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.materia;

import com.mvc.utils.IControlador;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jon
 */
public class CMateria implements IControlador {

    private final MMateria modelo;
    private final VMateria vista;

    public CMateria() {
        this.modelo = new MMateria();
        this.vista = new VMateria();
    }

    @Override
    public void create(HttpServletRequest req, HttpServletResponse res) {
        req.setAttribute("table", modelo.findAll());
        vista.show(req, res, "/template/materia/materia.jsp");
    }

    @Override
    public void save(HttpServletRequest req, HttpServletResponse res) {
        Map<String, String> request = new LinkedHashMap<>();
        request.put("id", req.getParameter("id"));
        request.put("codigo", req.getParameter("codigo"));
        request.put("descripcion", req.getParameter("descripcion"));

        modelo.setData(request);

        req.setAttribute("modelo", modelo.save());
        req.setAttribute("table", modelo.findAll());
        vista.show(req, res, "/template/materia/materia.jsp");
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse res) {
        boolean state = modelo.delete(req.getParameter("id"));

        req.setAttribute("state", state);
        req.setAttribute("table", modelo.findAll());
        vista.show(req, res, "/template/materia/materia.jsp");
    }

    @Override
    public void find(HttpServletRequest req, HttpServletResponse res) {
        req.setAttribute("modelo", modelo.find("id", req.getParameter("id")));
        req.setAttribute("table", modelo.findAll());
        vista.show(req, res, "/template/materia/materia.jsp");
    }

    @Override
    public void findAll(HttpServletRequest req, HttpServletResponse res) {
        req.setAttribute("table", modelo.findAll());
        vista.show(req, res, "/template/materia/materia.jsp");
    }

}
