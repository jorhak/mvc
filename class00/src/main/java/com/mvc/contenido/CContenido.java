package com.mvc.contenido;


import com.mvc.materia.MMateria;
import com.mvc.utils.IControlador;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jon
 */
public class CContenido implements IControlador {
    private final MContenido modelo;
    private final VContenido vista;
    private final MMateria mMateria;

    public CContenido() {
        this.modelo = new MContenido();
        this.vista = new VContenido();
        this.mMateria = new MMateria();
    }

    @Override
    public void create(HttpServletRequest req, HttpServletResponse res) {
        req.setAttribute("carga_materias", mMateria.comboBox());
        req.setAttribute("table", modelo.findAll());
        vista.show(req, res, "/template/contenido/contenido.jsp");
    }

    @Override
    public void save(HttpServletRequest req, HttpServletResponse res) {
        Map<String, String> request = new LinkedHashMap<>();
        request.put("id", req.getParameter("id"));
        request.put("descripcion", req.getParameter("descripcion"));
        request.put("id_materia", req.getParameter("id_materia"));

        modelo.setData(request);

        req.setAttribute("modelo", modelo.save());
        req.setAttribute("table", modelo.findAll());
        req.setAttribute("carga_materias", mMateria.comboBox());
        vista.show(req, res, "/template/contenido/contenido.jsp");
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse res) {
        boolean state = modelo.delete(req.getParameter("id"));

        req.setAttribute("state", state);
        req.setAttribute("table", modelo.findAll());
        req.setAttribute("carga_materias", mMateria.comboBox());
        vista.show(req, res, "/template/contenido/contenido.jsp");
    }

    @Override
    public void find(HttpServletRequest req, HttpServletResponse res) {

        req.setAttribute("modelo", modelo.find("id", req.getParameter("id")));
        req.setAttribute("table", modelo.findAll());
        req.setAttribute("carga_materias", mMateria.comboBox());
        vista.show(req, res, "/template/contenido/contenido.jsp");
    }

    @Override
    public void findAll(HttpServletRequest req, HttpServletResponse res) {
        req.setAttribute("table", modelo.findAll());
        req.setAttribute("carga_materias", mMateria.comboBox());
        vista.show(req, res, "/template/contenido/contenido.jsp");
    }
}
