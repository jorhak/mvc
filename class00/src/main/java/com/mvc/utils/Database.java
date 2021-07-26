package com.mvc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jon
 */
public class Database {

    private static final String host = "127.0.0.1";
    private static final String database = "parcial";
    private static final String user = "root";
    private static final String password = "toor";
    private static final int port = 3307;

    private Connection con;

    private static Database instance;

    private Database() {
        connect();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public void connect() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://" + host + ":" + port + "/" + database;
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(true);
            System.out.println("conexion realizada con exito");
        } catch (Exception e) {
            con = null;
            System.out.println("conexion fallida");
        }
    }

    public Connection getConnection() {
        if (con == null) {
            connect();
        }
        return con;
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
        }
        con = null;
    }

    public boolean executeSQL(String sql) {
        try (Statement consulta = con.createStatement();) {
            consulta.executeUpdate(sql);
        } catch (SQLException ex) {
            con = null;
            connect();
            return false;
        }
        return true;
    }

    public List<Map<String, String>> executeSQLResultList(String sql) {
        List<Map<String, String>> data = new LinkedList<>();
        try (PreparedStatement consulta = con.prepareStatement(sql); ResultSet resultado = consulta.executeQuery();) {
            String columnNames[] = columnName(resultado);
            while (resultado.next()) {
                Map<String, String> map = new HashMap<>();
                for (int index = 0; index < columnNames.length; index++) {
                    map.put(columnNames[index], resultado.getObject(index + 1).toString());
                }
                data.add(map);
            }
        } catch (Exception e) {
            con = null;
            connect();
        }
        return data;
    }

    public boolean executeSQL(PreparedStatement preparedStatement) {
        int state = 0;
        try {
            state = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            con = null;
            connect();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
            }
        }
        return state != 0;
    }

    public boolean delete(String sql, Object... primaryKey) {
        boolean proccessed = false;
        try (PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(sql);) {
            for (int index = 0; index < primaryKey.length; index++) {
                statement.setString(index + 1, primaryKey[index].toString());
            }
            proccessed = Database.getInstance().executeSQL(statement);
        } catch (SQLException e) {
            con = null;
        }
        return proccessed;
    }

    public List<Map<String, Object>> executeSQLResultList(PreparedStatement preparedStatement) {
        List<Map<String, Object>> data = new LinkedList<>();
        try (ResultSet resultado = preparedStatement.executeQuery();) {
            String columnNames[] = columnName(resultado);
            while (resultado.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int index = 0; index < columnNames.length; index++) {
                    map.put(columnNames[index], resultado.getObject(index + 1));
                }
                data.add(map);
            }
        } catch (Exception e) {
            con = null;
            connect();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
            }
        }
        return data;
    }

    private String[] columnName(ResultSet resultSet) {
        String columns[] = null;
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            columns = new String[columnCount];
            for (int index = 0; index < columnCount; index++) {
                columns[index] = metaData.getColumnName(index + 1);
            }
        } catch (SQLException e) {
            con = null;
            connect();
        }
        return columns;
    }
}
