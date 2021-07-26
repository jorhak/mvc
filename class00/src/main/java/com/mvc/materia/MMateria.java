/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.materia;

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
public class MMateria implements IModelo {

    private int id;
    private String codigo;
    private String descripcion;

    public MMateria() {
        this(0, "", "");
    }

    public MMateria(int id, String codigo, String descripcion) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    @Override
    public void setData(Map<String, String> request) {
        id = Integer.parseInt(request.getOrDefault("id", "0"));
        codigo = request.getOrDefault("codigo", "");
        descripcion = request.getOrDefault("descripcion", "");
    }

    @Override
    public Map<String, String> save() {
        boolean proccessed = false;
        String sqlInsert = "insert into Materia (codigo, descripcion) "
                + "values (?,?);";

        String sqlUpdate = "update Materia "
                + "set codigo=?, descripcion=? "
                + "where id=?;";

        String sql = id != 0 ? sqlUpdate : sqlInsert;

        try {
            PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, codigo);
            statement.setString(2, descripcion);

            if (id != 0) {
                statement.setInt(3, id);
            }

            proccessed = Database.getInstance().executeSQL(statement);
        } catch (SQLException e) {
        }

        return proccessed ? find("codigo", codigo) : null;
    }

    @Override
    public boolean delete(String id) {
        String sql = "delete from Materia where id=?;";
        return Database.getInstance().delete(sql, id);
    }

    @Override
    public Map<String, String> find(String columnName, Object columnValue) {
        String sql = "select * from Materia where %s='%s' limit 1;";
        sql = String.format(sql, columnName, columnValue);

        List<Map<String, String>> resultado = Database.getInstance().executeSQLResultList(sql);
        return !resultado.isEmpty() ? resultado.get(0) : null;
    }

    @Override
    public List<Map<String, String>> findAll() {
        String sql = "select * from Materia order by 1;";
        return Database.getInstance().executeSQLResultList(sql);
    }

    @Override
    public Map<String, String> comboBox() {
        Map<String, String> materia = new LinkedHashMap<>();

        String sql = "select * from Materia order by 1;";
        List<Map<String, String>> rows = Database.getInstance().executeSQLResultList(sql);

        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);

            String fullname = row.get("codigo") + " " + row.get("descripcion");
            materia.put(row.get("id"), fullname);
        }
        return materia;
    }
}
