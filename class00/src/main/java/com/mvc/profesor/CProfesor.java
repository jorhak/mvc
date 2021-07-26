package com.mvc.profesor;


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
public class CProfesor implements IControlador{
    private final MProfesor modelo;
    private final VProfesor vista;
    private final MMateria mMateria;

    public CProfesor() {
        this.modelo = new MProfesor();
        this.vista = new VProfesor();
        this.mMateria = new MMateria();
    }

   @Override
    public void create(HttpServletRequest req, HttpServletResponse res) {
        req.setAttribute("carga_materias", mMateria.comboBox());
        req.setAttribute("table", modelo.findAll());
        vista.show(req, res, "/template/profesor/profesor.jsp");
    }

    @Override
    public void save(HttpServletRequest req, HttpServletResponse res) {
        Map<String, String> request = new LinkedHashMap<>();
        request.put("id", req.getParameter("id"));
        request.put("ci", req.getParameter("ci"));
        request.put("nombres", req.getParameter("nombres"));
        request.put("apellidos", req.getParameter("apellidos"));
        request.put("id_materia", req.getParameter("id_materia"));

        modelo.setData(request);

        req.setAttribute("modelo", modelo.save());
        req.setAttribute("table", modelo.findAll());
        req.setAttribute("carga_materias", mMateria.comboBox());
        vista.show(req, res, "/template/profesor/profesor.jsp");
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse res) {
        boolean state = modelo.delete(req.getParameter("id"));

        req.setAttribute("state", state);
        req.setAttribute("table", modelo.findAll());
        req.setAttribute("carga_materias", mMateria.comboBox());
        vista.show(req, res, "/template/profesor/profesor.jsp");
    }

    @Override
    public void find(HttpServletRequest req, HttpServletResponse res) {

        req.setAttribute("modelo", modelo.find("id", req.getParameter("id")));
        req.setAttribute("table", modelo.findAll());
        req.setAttribute("carga_materias", mMateria.comboBox());
        vista.show(req, res, "/template/profesor/profesor.jsp");
    }

    @Override
    public void findAll(HttpServletRequest req, HttpServletResponse res) {
        req.setAttribute("table", modelo.findAll());
        req.setAttribute("carga_materias", mMateria.comboBox());
        vista.show(req, res, "/template/profesor/profesor.jsp");
    }
    
}
