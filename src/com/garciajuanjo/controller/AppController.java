package com.garciajuanjo.controller;


import com.garciajuanjo.domain.Productos;
import com.garciajuanjo.model.ProductosDao;
import com.garciajuanjo.view.App;

import java.util.List;
import java.util.Objects;

public class AppController {

    private final App app = new App();
    private final ProductosDao productosDao;

    public AppController(){
        productosDao = new ProductosDao();
        addListenerButton();
    }

    public void addListenerButton(){
        app.getButton().addActionListener(e -> {
            String countries = Objects.requireNonNull(app.getPaises().getSelectedItem()).toString();
            String secctions = Objects.requireNonNull(app.getSecciones().getSelectedItem()).toString();

            List<Productos> productos = productosDao.getResultSearch(countries, secctions);

            app.getResultados().setText("");
            productos.forEach(prducto -> {
                app.getResultados().append(prducto.toString() + "\n");
            });
        });
    }

}
