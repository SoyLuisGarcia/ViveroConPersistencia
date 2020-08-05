package com.liy.Vivero.View;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;

import com.liy.Vivero.Main;
import com.liy.Vivero.Model.DAOproducto;
import com.liy.Vivero.Model.Producto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ProductoController {
	private Main main;
	private Stage stage;
	private DAOproducto daoProducto;
	
	@FXML private TableView<Producto> tabla;
    @FXML private TableColumn<Producto, Integer> id;
    @FXML private TableColumn<Producto, String> nombre;
    @FXML private TableColumn<Producto, String> condicion;
    @FXML private TableColumn<Producto, String> fecha_ingreso;
    @FXML private TableColumn<Producto, String> nameTipo;
    @FXML private TextField search;
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setProducto(SessionFactory factory) {
		daoProducto = new DAOproducto(factory);
		creaTabla();
	}
	
	private void creaTabla() {
		ObservableList<Producto> lista = FXCollections.observableArrayList();
		List<?> employees = daoProducto.mostrarProductos();
		for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
			Producto dao = (Producto) iterator.next();
			lista.add(new Producto(dao.getId(), dao.getNombre(), dao.getCondicion(), dao.getFecha_ingreso(), dao.getTipo().getNombre()));
		}
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		condicion.setCellValueFactory(new PropertyValueFactory<>("condicion"));
		fecha_ingreso.setCellValueFactory(new PropertyValueFactory<>("fecha_ingreso"));
		nameTipo.setCellValueFactory(new PropertyValueFactory<>("nameTipo"));
		tabla.setItems(lista);
	}
	
	@FXML
	void accederClick() {
		if (search.getText().isEmpty()) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("No se ha especificado el ID");
			error.setContentText("Ingrese el ID del producto");
			error.show();
		} else if(!daoProducto.validarProd(Integer.valueOf(search.getText()))) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("No se ha encontrado el ID");
			error.setContentText("El ID no es valido");
			error.show();
		} else {
			Producto prod = daoProducto.mostararProducto(Integer.valueOf(search.getText()));
			main.openHistorial(stage, prod);
		}
	}
	
	@FXML
	void actualizarClick() {
		if (search.getText().isEmpty()) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("No se ha especificado el ID");
			error.setContentText("Ingrese el ID del producto");
			error.show();
		} else if(!daoProducto.validarProd(Integer.valueOf(search.getText()))) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("No se ha encontrado el ID");
			error.setContentText("El ID no es valido");
			error.show();
		} else {
			Producto prod = daoProducto.mostararProducto(Integer.valueOf(search.getText()));
			main.openActualizarProd(stage, prod);
			creaTabla();
		}
	}
	
	@FXML
	void borrarClick() {
		if (search.getText().isEmpty()) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("No se ha especificado el ID");
			error.setContentText("Ingrese el ID del producto");
			error.show();
		} else if(!daoProducto.validarProd(Integer.valueOf(search.getText()))) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("No se ha encontrado el ID");
			error.setContentText("El ID no es valido");
			error.show();
		} else {
			Producto prod = new Producto();
			prod.setId(Integer.valueOf(search.getText()));
			daoProducto.borrarProd(prod);
			creaTabla();
		}
	}
	
	@FXML
    void agregarClick() {
		main.openNewProd(stage);
		creaTabla();
    }
	
	@FXML
    void salirClick() {
		stage.close();
    }
}
