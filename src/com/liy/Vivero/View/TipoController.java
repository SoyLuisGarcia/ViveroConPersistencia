package com.liy.Vivero.View;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;

import com.liy.Vivero.Model.DAOtipo;
import com.liy.Vivero.Model.Tipo;

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

public class TipoController {
	private Stage stage;
	private DAOtipo daoTipo;
	
	@FXML private TableView<Tipo> tabla;
    @FXML private TableColumn<Tipo, Integer> id;
    @FXML private TableColumn<Tipo, String> nombre;
    @FXML private TextField idInsert;
    @FXML private TextField tipoInsert;
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setFactory(SessionFactory factory) {
		daoTipo = new DAOtipo(factory);
		creeaTabla();
	}
	
	
	private void creeaTabla() {
		ObservableList<Tipo> lista = FXCollections.observableArrayList();
		List<?> employees = daoTipo.mostrarTipo();
		for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();){
       	 Tipo dao = (Tipo) iterator.next(); 
           lista.add(dao);
        }
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		tabla.setItems(lista);
	}
	
	@FXML
	void borrarClick() {
		if (idInsert.getText().isEmpty()) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("Campo no llenado");
			error.setContentText("El id no deve de ir vasio");
			error.show();
		} else if (!daoTipo.validaTipoId(Integer.valueOf(idInsert.getText()))){
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("El tipo no se ha encontrado");
			error.setContentText("Ingrese un ID valido");
			error.show();
		}else if (daoTipo.validaProdsEnTipos(Integer.valueOf(idInsert.getText()))){
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("Hay productos que tienen este tipo");
			error.setContentText("Cambie los productos que tienen este tipo o borrelos");
			error.show();
		} else { 
			Tipo one = new Tipo();
			one.setId(Integer.valueOf(idInsert.getText()));
			daoTipo.borrarTipo(one);
			creeaTabla();
		}
	}
	
	@FXML
    void agregarCilc() {
		if (tipoInsert.getText().isEmpty()) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("No se ha insertado el tipo");
			error.setContentText("Inserte un tipo");
			error.showAndWait();
		}else if(daoTipo.validaTipo(tipoInsert.getText())) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("Ese tipo ya existe");
			error.setContentText("Ingrese otro tipo");
			error.showAndWait();
		}else {
			Tipo tipo = new Tipo();
			tipo.setNombre(tipoInsert.getText());
			daoTipo.creaTipo(tipo);
			creeaTabla();
		}
    }
	
	@FXML
	void actualizarClick() {
		if (tipoInsert.getText().isEmpty() || idInsert.getText().isEmpty()) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("Campos vacios");
			error.setContentText("Favor de llenar todos los campos");
			error.show();
		} else if (!daoTipo.validaTipoId(Integer.valueOf(idInsert.getText()))) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("ID invalido");
			error.setContentText("Es ID no existe");
			error.show();
		} else if (daoTipo.validaTipo(tipoInsert.getText())) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("Tipo existente");
			error.setContentText("Ese tipo de producto ya existe");
			error.show();
		} else {
			Tipo one = new Tipo();
			one.setId(Integer.valueOf(idInsert.getText()));
			one.setNombre(tipoInsert.getText());
			daoTipo.actualizaTipo(one);
			creeaTabla();
		}
			
	}
	
	@FXML
	void salirClick() {
		stage.close();
	}
}
