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

public class ActualizarProdController {
	private Stage stage;
	private DAOtipo daoTipo;
	private DAOproducto daoProducto;
	private Producto prod;
	
	@FXML private TextField nombre;
    @FXML private TextField condicion;
    @FXML private TextField fecha2;
    @FXML private DatePicker fecha;
    @FXML private ComboBox<String> tipo;
    
    @FXML
    void agregarClick() {
    	if(nombre.getText().isEmpty() || condicion.getText().isEmpty() || fecha2.getText().isEmpty() || tipo.getValue() == null) {
    		Alert error = new Alert(AlertType.ERROR);
    		error.setTitle("Error");
    		error.setHeaderText("No se han llenado los campos");
    		error.setContentText("Favor de completarlos");
    		error.show();
    	} else { 
    		
    		Producto one = new Producto();
    		one.setId(prod.getId());
    		one.setNombre(nombre.getText());
    		one.setCondicion(condicion.getText());
    		one.setFecha_ingreso(fecha2.getText());
    		one.setTipo(daoTipo.regresaTipo(tipo.getValue()));
    		
    		daoProducto.actualizaProd(one);
    		
    		
    		Alert alerta = new Alert(AlertType.INFORMATION);
    		
    		alerta.setTitle("Informacion");
    		alerta.setHeaderText("Se ha actualizado el producto");
    		alerta.setContentText("Se ha actualizado el producto sin ningun problema");
    		alerta.showAndWait();
    		stage.close();
    		
    	}
    }
    
    @FXML
    void cancelarClick() {
    	stage.close();
    }
    
    @FXML
    void ponerFecha() {
    	fecha2.setText(String.valueOf(fecha.getValue()));
    }
    
    public void setStage(Stage stage) {
		this.stage = stage;
	}
    
    public void setDaoTipo(SessionFactory factory) {
		daoTipo = new DAOtipo(factory);
		daoProducto = new DAOproducto(factory);
		llenarTipo();
	}
    
    public void setProd(Producto prod) {
		this.prod = prod;
		llenarTodo();
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
    
    private void llenarTodo() {
    	nombre.setText(prod.getNombre());
    	condicion.setText(prod.getCondicion());
    	fecha.setPromptText(prod.getFecha_ingreso());
    	fecha2.setText(prod.getFecha_ingreso());
    	tipo.setValue(prod.getTipo().getNombre());
    }
}
