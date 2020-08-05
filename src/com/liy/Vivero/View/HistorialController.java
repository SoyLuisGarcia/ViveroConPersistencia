package com.liy.Vivero.View;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;

import com.liy.Vivero.Main;
import com.liy.Vivero.Model.DAOproducto;
import com.liy.Vivero.Model.Historial;
import com.liy.Vivero.Model.Producto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class HistorialController {
	private DAOproducto daoProd;
	private Stage stage;
	private Main main;
	private Producto prod;
	
    @FXML private TableView<Historial> tabla;
    @FXML private TableColumn<Historial,Integer> id;
    @FXML private TableColumn<Historial,String> fotografia;
    @FXML private TableColumn<Historial,String> fecha;
    @FXML private TextField idProducto;
    
	public void setMain(Main main, Producto prod, Stage stage, SessionFactory factory) {
		this.main = main;
		this.prod = prod;
		this.stage = stage;
		this.daoProd = new DAOproducto(factory);
		mostrarTabla();
	}
	
	public void mostrarTabla() {
		actualizaProd();
	    ObservableList<Historial> lista = FXCollections.observableArrayList();
		List<Historial> one = prod.getHistorial();
		for(Iterator<Historial> iterator = one.iterator(); iterator.hasNext();) {
			Historial dao = (Historial) iterator.next();
			lista.add(dao);
		}
    	id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	fotografia.setCellValueFactory(new PropertyValueFactory<>("fotografia"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tabla.setItems(lista);	
	}
	
	@FXML
	void cerrarClick() {
		stage.close();
	}
	
	@FXML
	void actualizarClick() {
		if (idProducto.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Información");
			alert.setHeaderText("No se ha introducido el ID");
			alert.setContentText("Introdusca una ID valida");
			alert.show();
		} else if (!validaHis(Integer.valueOf(idProducto.getText()))) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("El ID introducido no es compatible");
			alert.setContentText("Introdusca una ID valida");
			alert.show();
		} else {
			Historial his = regresaHis(Integer.valueOf(idProducto.getText()));
			main.openActHis(stage, prod, his);
			mostrarTabla();
		}
		
	}
	
	@FXML
	void agregarClick() {
		main.openNewHis(stage, prod);
		mostrarTabla();
	}
	
	private void actualizaProd() {
		prod = daoProd.mostararProducto(prod.getId());
	}
	
	private boolean validaHis(Integer ID) {
		List<Historial> lista = prod.getHistorial();
		for(Iterator<Historial> iterator = lista.iterator(); iterator.hasNext();) {
			Historial dao = (Historial) iterator.next();
			if (dao.getId() == ID) return true;
		}
		return false;
	}
	
	private Historial regresaHis(Integer ID) {
		List<Historial> lista = prod.getHistorial();
		for(Iterator<Historial> iterator = lista.iterator(); iterator.hasNext();) {
			Historial his = (Historial) iterator.next();
			if (his.getId() == ID) return his;
		}
		return null;
	}
}
