package com.garciajuanjo.model;

import com.garciajuanjo.domain.Products;

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

    /**
     * Método que devuelve las distintas secciones que tenemos en la base de datos
     * @return List<Products> solo con las secciones
     */
    public List<Products> getSecciones(){
        Connection connection = conexion.getConnection();
        List<Products> secctions = new ArrayList<>();

        try {
            String sql = "SELECT DISTINCT seccion FROM productos";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Products producto = new Products();
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

    /**
     * Método que devuelve los distintos paises que tenemos en la base de datos
     * @return List<Products> solo con los paises
     */
    public List<Products> getPaises(){
        Connection connection = conexion.getConnection();
        List<Products> productos = new ArrayList<>();

        try {
            String sql = "SELECT DISTINCT pais FROM productos";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()){
                Products producto = new Products();
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

    /**
     * Método que devuelve los productos en función de los parámetros de busqueda que seleccionemos
     * @param country valor del JComboBox de countries
     * @param section valor del JComboBox de sections
     * @return List<Products> con los productos
     */
    public List<Products> getResultSearch(String country, String section){
        Connection connection = conexion.getConnection();
        List<Products> productos = new ArrayList<>();
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
                Products producto = new Products();

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
