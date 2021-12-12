package com.garciajuanjo.controller;


import com.garciajuanjo.domain.Products;
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

    /**
     * Acción del botón de búsqueda. Cuando pulsamos sobre el recoge los valores de los JComboBox, busca los productos
     * en la basede datos y los muestra en el JTextArea
     */
    public void addListenerButton(){
        app.getButton().addActionListener(e -> {
            String countries = Objects.requireNonNull(app.getCountries().getSelectedItem()).toString();
            String secctions = Objects.requireNonNull(app.getSections().getSelectedItem()).toString();

            List<Products> productos = productosDao.getResultSearch(countries, secctions);

            app.getResults().setText("");
            productos.forEach(prducto -> {
                app.getResults().append(prducto.toString() + "\n");
            });
        });
    }

}
