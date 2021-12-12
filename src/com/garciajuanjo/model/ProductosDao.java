package com.garciajuanjo.model;

import com.garciajuanjo.domain.Productos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductosDao {

    private final Conexion conexion;

    public ProductosDao(){
        conexion = new Conexion();
    }

    public List<Productos> getSecciones(){
        Connection connection = conexion.getConnection();
        List<Productos> secctions = new ArrayList<>();

        try {
            String sql = "SELECT DISTINCT seccion FROM productos";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Productos producto = new Productos();
                producto.setSection(resultSet.getString(1));

                secctions.add(producto);
            }

            resultSet.close();
            connection.close();

        }catch (SQLException sql) {
            sql.printStackTrace();
        }

        return secctions;
    }

    public List<Productos> getPaises(){
        Connection connection = conexion.getConnection();
        List<Productos> productos = new ArrayList<>();

        try {
            String sql = "SELECT DISTINCT pais FROM productos";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()){
                Productos producto = new Productos();
                producto.setCountry(result.getString(1));

                productos.add(producto);
            }

            result.close();
            connection.close();

        }catch (SQLException sql){
            sql.printStackTrace();
        }

        return productos;
    }

    public List<Productos> getResultSearch(String country, String section){
        Connection connection = conexion.getConnection();
        List<Productos> productos = new ArrayList<>();
        PreparedStatement statement;

        try {
            String sql;

            if(!country.equals("Todos") && !section.equals("Todas")) {
                sql = "SELECT nombre, precio, pais FROM productos WHERE seccion = ? AND pais = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, section);
                statement.setString(2, country);

            } else if(!country.equals("Todos")) {
                sql = "SELECT nombre, precio, pais FROM productos WHERE pais = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, country);

            } else if(!section.equals("Todas")) {
                sql = "SELECT nombre, precio, pais FROM productos WHERE seccion = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, section);
            } else {
                sql = "SELECT nombre, precio, pais FROM productos";
                statement = connection.prepareStatement(sql);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Productos producto = new Productos();

                producto.setName(resultSet.getString(1));
                producto.setPrice(resultSet.getString(2));
                producto.setCountry(resultSet.getString(3));

                productos.add(producto);
            }

            resultSet.close();
            connection.close();

        }catch (SQLException sql){
            sql.printStackTrace();
        }

        return productos;
    }
}
