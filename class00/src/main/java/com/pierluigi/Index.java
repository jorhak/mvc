package com.pierluigi;

import com.mvc.contenido.CContenido;
import com.mvc.materia.CMateria;
import com.mvc.profesor.CProfesor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jon
 */
public class Index extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/materia": {
                CMateria materia = new CMateria();
                materia.save(req, resp);
                return;
            }
            case "/contenido": {
                CContenido contenido = new CContenido();
                contenido.save(req, resp);
                return;
            }
            case "/profesor": {
                CProfesor profesor = new CProfesor();
                profesor.save(req, resp);
                return;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        
        if (action.startsWith("/materia")) {
            CMateria materia = new CMateria();

            switch (action) {
                case "/materia":
                    materia.create(req, resp);
                    return;
                case "/materia/delete":
                    materia.delete(req, resp);
                    return;
                case "/materia/find":
                    materia.find(req, resp);
                    return;
                case "/materia/findAll":
                    materia.findAll(req, resp);
                    return;
            }
        }

        if (action.startsWith("/contenido")) {
            CContenido contenido = new CContenido();

            switch (action) {
                case "/contenido":
                    contenido.create(req, resp);
                    return;
                case "/contenido/delete":
                    contenido.delete(req, resp);
                    return;
                case "/contenido/find":
                    contenido.find(req, resp);
                    return;
                case "/contenido/findAll":
                    contenido.findAll(req, resp);
                    return;
            }
        }
        
        if (action.startsWith("/profesor")) {
            CProfesor profesor = new CProfesor();

            switch (action) {
                case "/profesor":
                    profesor.create(req, resp);
                    return;
                case "/profesor/delete":
                    profesor.delete(req, resp);
                    return;
                case "/profesor/find":
                    profesor.find(req, resp);
                    return;
                case "/profesor/findAll":
                    profesor.findAll(req, resp);
                    return;
            }
        }
//
//        if (action.startsWith("/carga-horaria")) {
//            CCargaHoraria cargaHoraria = new CCargaHoraria();
//
//            switch (action) {
//                case "/carga-horaria":
//                    cargaHoraria.create(req, resp);
//                    return;
//            }
//        }
    }

}
