/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.profesor;

import com.mvc.utils.Database;
import com.mvc.utils.IModelo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jon
 */
public class MProfesor implements IModelo {

    private int id;
    private String ci;
    private String nombres;
    private String apellidos;
    private int id_materia;

    public MProfesor() {
        this(0, "", "", "", 0);
    }

    public MProfesor(int id, String ci, String nombres, String apellidos, int id_materia) {
        this.id = id;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.id_materia = id_materia;
    }

   @Override
    public void setData(Map<String, String> request) {
        id = Integer.parseInt(request.getOrDefault("id", "0"));
        ci = request.getOrDefault("ci", "");
        nombres = request.getOrDefault("nombres", "");
        apellidos = request.getOrDefault("apellidos", "");
        id_materia = Integer.parseInt(request.getOrDefault("id_materia", "0"));
    }

    @Override
    public Map<String, String> save() {
        boolean proccessed = false;
        String sqlInsert = "insert into profesor1 (ci, nombres, apellidos, id_materia) "
                + "values (?,?,?,?);";

        String sqlUpdate = "update profesor1 "
                + "set ci=?, nombres=?, apellidos=?, id_materia=? "
                + "where id=?;";

        String sql = id != 0 ? sqlUpdate : sqlInsert;

        try {
            PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, ci);
            statement.setString(2, nombres);
            statement.setString(3, apellidos);
            statement.setInt(4, id_materia);

            if (id != 0) {
                statement.setInt(5, id);
            }

            proccessed = Database.getInstance().executeSQL(statement);
        } catch (SQLException e) {
        }

        return proccessed ? find("ci", ci) : null;
    }

    @Override
    public boolean delete(String id) {
        String sql = "delete from profesor1 where id=?;";
        return Database.getInstance().delete(sql, id);
    }

    @Override
    public Map<String, String> find(String columnName, Object columnValue) {
        String sql = "select * from profesor1 where %s='%s' limit 1;";
        sql = String.format(sql, columnName, columnValue);

        List<Map<String, String>> resultado = Database.getInstance().executeSQLResultList(sql);
        return !resultado.isEmpty() ? resultado.get(0) : null;
    }

    @Override
    public List<Map<String, String>> findAll() {
        String sql = "select * from profesor1 order by 1;";
        return Database.getInstance().executeSQLResultList(sql);
    }

    @Override
    public Map<String, String> comboBox() {
        Map<String, String> profesor = new LinkedHashMap<>();

        String sql = "select * from profesor1 order by 1;";
        List<Map<String, String>> rows = Database.getInstance().executeSQLResultList(sql);

        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);

            String fullname = row.get("nombres") + " " + row.get("apellidos");
            profesor.put(row.get("ci"), fullname);
        }
        return profesor;
    }

    
}
