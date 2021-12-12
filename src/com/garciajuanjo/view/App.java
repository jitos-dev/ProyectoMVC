package com.garciajuanjo.view;

import com.garciajuanjo.domain.Products;
import com.garciajuanjo.model.ProductosDao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class App extends JFrame{

    private final JComboBox<String> sections;
    private final JComboBox<String> countries;
    private final JTextArea results;
    private final JButton button;

    public App() {
        ProductosDao productosDao = new ProductosDao();

        setTitle("Consulta de productos");
        setBounds(500,300,400,400);
        setLayout(new BorderLayout());

        JPanel menus = new JPanel();
        menus.setLayout(new FlowLayout());

        sections = new JComboBox<>();
        sections.addItem("Todas");
        sections.setPreferredSize(new Dimension(120,25));
        addItemsSections(productosDao.getSecciones());

        countries = new JComboBox<>();
        countries.addItem("Todos");
        countries.setPreferredSize(new Dimension(120,25));
        addItemsCountries(productosDao.getPaises());

        menus.add(sections);
        menus.add(countries);

        //Metemos el JTextArea de los resultados dentro de un JScrollPane para poder verlos todos
        results = new JTextArea(4,50);
        JScrollPane scrollPane = new JScrollPane(results,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.button = new JButton("Buscar");

        add(menus, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    /**
     * Método que sirve pera añadir las distintas secciones en el JComboBox de sections cuando iniciamos la app
     * @param list de products
     */
    private void addItemsSections(List<Products> list){
        list.forEach(item -> {
            sections.addItem(item.getSection());
        });
    }

    /**
     * Método que sirve pera añadir las distintas ciudades en el JComboBox de countries cuando iniciamos la app
     * @param list de products
     */
    private void addItemsCountries(List<Products> list){
        list.forEach(item -> {
            countries.addItem(item.getCountry());
        });
    }

    public JComboBox<String> getSections() {
        return sections;
    }

    public JComboBox<String> getCountries() {
        return countries;
    }

    public JTextArea getResults() {
        return results;
    }

    public JButton getButton() {
        return button;
    }
}
