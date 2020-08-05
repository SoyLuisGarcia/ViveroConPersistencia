package com.liy.Vivero.View;

import org.hibernate.SessionFactory;

import com.liy.Vivero.Main;
import com.liy.Vivero.Model.DAOusuario;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class LoginController {
	Main main;
	Stage stage;
	DAOusuario daoUsuario;
	
	@FXML private TextField usuario;
    @FXML private PasswordField  contrasenia;
    
    public void setStage(Stage stage) {
		this.stage = stage;
	}
    
    public void setMain(Main main) {
		this.main = main;
	}
    
    public void setDaoUsuario(SessionFactory factory) {
		daoUsuario = new DAOusuario(factory);
	}
    
    

    @FXML
    void accederClick() {
    	if (usuario.getText().isEmpty() && contrasenia.getText().isEmpty()) {
    		Alert error = new Alert(AlertType.ERROR);
    		error.setTitle("Error");
    		error.setHeaderText("Los campos estan vacios");
    		error.setContentText("Rellene todos los campos");
    		error.showAndWait();
    	} else if (usuario.getText().isEmpty()) {
    		Alert error = new Alert(AlertType.ERROR);
    		error.setTitle("Error");
    		error.setHeaderText("El usuario esta vacio");
    		error.setContentText("Rellene todos los campos");
    		error.showAndWait();
    	} else if (contrasenia.getText().isEmpty()) {
    		Alert error = new Alert(AlertType.ERROR);
    		error.setTitle("Error");
    		error.setHeaderText("La contraseña esta vacia");
    		error.setContentText("Rellene todos los campos");
    		error.showAndWait();
    	} else if (!daoUsuario.validarUsuario(usuario.getText())) {
    		Alert error = new Alert(AlertType.ERROR);
    		error.setTitle("Error");
    		error.setHeaderText("Usuario no existente");
    		error.setContentText("No existe ese usuario");
    		error.showAndWait();
    	} else if (!daoUsuario.validarContrasenia(usuario.getText(), contrasenia.getText())){
    		Alert error = new Alert(AlertType.ERROR);
    		error.setTitle("Error");
    		error.setHeaderText("Contraseña incorrecta");
    		error.setContentText("La contraseña esta incorrecta");
    		error.showAndWait();
    	} else {
    		Alert entro = new Alert(AlertType.INFORMATION);
    		entro.setTitle("Hello world");
    		entro.setHeaderText("Vienvenido "+usuario.getText());
    		entro.setContentText("De en aceptar para continuar");
    		entro.showAndWait();
    		stage.close();
    		main.openMenu();
    	}
    }

}
