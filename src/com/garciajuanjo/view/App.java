package com.garciajuanjo.view;

import com.garciajuanjo.domain.Productos;
import com.garciajuanjo.model.ProductosDao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class App extends JFrame{

    private final JComboBox<String> secciones;
    private final JComboBox<String> paises;
    private final JTextArea resultados;
    private final JButton button;

    public App() {
        ProductosDao productosDao = new ProductosDao();

        setTitle("Consulta de productos");
        setBounds(500,300,400,400);
        setLayout(new BorderLayout());

        JPanel menus = new JPanel();
        menus.setLayout(new FlowLayout());

        secciones = new JComboBox<>();
        secciones.addItem("Todas");
        secciones.setPreferredSize(new Dimension(120,25));
        addItemsSections(productosDao.getSecciones());

        paises = new JComboBox<>();
        paises.addItem("Todos");
        paises.setPreferredSize(new Dimension(120,25));
        addItemsCountries(productosDao.getPaises());

        menus.add(secciones);
        menus.add(paises);

        resultados = new JTextArea(4,50);
        JScrollPane scrollPane = new JScrollPane(resultados,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.button = new JButton("Buscar");

        add(menus, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    private void addItemsSections(List<Productos> list){
        list.forEach(item -> {
            secciones.addItem(item.getSection());
        });
    }

    private void addItemsCountries(List<Productos> list){
        list.forEach(item -> {
            paises.addItem(item.getCountry());
        });
    }

    public JComboBox<String> getSecciones() {
        return secciones;
    }

    public JComboBox<String> getPaises() {
        return paises;
    }

    public JTextArea getResultados() {
        return resultados;
    }

    public JButton getButton() {
        return button;
    }
}
