package com.liy.Vivero.View;

import com.liy.Vivero.Main;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class PrincipalController {
	private Main main;
	private Stage stage;
	
	public void inicio(Main main, Stage stage) {
		this.main = main;
		this.stage = stage;
	}
	
	@FXML
    void clickMenu() {
		stage.close();
		main.conexion();
    }
}
