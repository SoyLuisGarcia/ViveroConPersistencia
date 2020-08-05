package com.liy.Vivero.View;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;

import com.liy.Vivero.Model.DAOproducto;
import com.liy.Vivero.Model.DAOtipo;
import com.liy.Vivero.Model.Producto;
import com.liy.Vivero.Model.Tipo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NewProdController {
	Stage stage;
	DAOtipo daoTipo;
	DAOproducto daoProducto;
	
	@FXML private TextField nombre;
    @FXML private TextField condicion;
    @FXML private DatePicker fecha;
    @FXML private ComboBox<String> tipo;

    @FXML
    void agregarClick() {
    	if(nombre.getText().isEmpty() || condicion.getText().isEmpty() || fecha.getValue() == null || tipo.getValue() == null) {
    		Alert error = new Alert(AlertType.ERROR);
    		error.setTitle("Error");
    		error.setHeaderText("No se han llenado los campos");
    		error.setContentText("Favor de completarlos");
    		error.show();
    	} else {
    		Producto prod = new Producto(nombre.getText(), condicion.getText(), String.valueOf(fecha.getValue()));
    		prod.setTipo(daoTipo.regresaTipo(tipo.getValue()));
    		daoProducto.creaProducto(prod);
    		Alert alerta = new Alert(AlertType.INFORMATION);
    		alerta.setTitle("Informacion");
    		alerta.setHeaderText("Se ha creado el producto");
    		alerta.setContentText("Se ha creado el producto sin ningun problema");
    		alerta.showAndWait();
    		stage.close();
    	}
    }

    @FXML
    void cancelarClick() {
    	stage.close();
    }
    
    public void setStage(Stage stage) {
		this.stage = stage;
	}
    
    public void setDaoTipo(SessionFactory factory) {
		daoTipo = new DAOtipo(factory);
		daoProducto = new DAOproducto(factory);
		llenarTipo();
	}
    
    
    
    private void llenarTipo() {
    	ObservableList<String> lista = FXCollections.observableArrayList();
    	List<?> employees = daoTipo.mostrarTipo();
		for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();){
       	 Tipo dao = (Tipo) iterator.next(); 
           lista.add(dao.getNombre());
        } 
		tipo.setItems(lista);
    }

}
