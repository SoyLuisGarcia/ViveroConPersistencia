package com.liy.Vivero.View;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.liy.Vivero.Model.DAOhistorial;
import com.liy.Vivero.Model.Historial;
import com.liy.Vivero.Model.Producto;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NuevoHistorialController {
	private Stage stage;
	private DAOhistorial daoHistorial;
	private Producto prod;
	private File imgFile;
	
	@FXML private DatePicker fecha;
    @FXML private TextField fecha2;
    @FXML private ImageView imagen;
	
	@FXML
	void agergarClick(){
		if(fecha2.getText().isEmpty()) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Informacion");
			alerta.setHeaderText("No se ha introducido la fecha");
			alerta.setContentText("El historial no fue agregado!");
			alerta.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmacion");
			alert.setHeaderText("Elige una respuesta");
			alert.setContentText("¿Estas seguro de agregar el historial?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Informacion");
				alerta.setHeaderText(null);
				alerta.setContentText("El historial fue agregado!");
				alerta.showAndWait();
				
				Historial his = new Historial();
				his.setProducto(prod);
				his.setFecha(fecha2.getText());
	            his.setFotografia("Media/noHayImagen.jpg");
				
				Integer elID = daoHistorial.creaHistorial(his);
				
				if(imagen.getImage() == null) {
					
				} else {
		            Path origenPath = FileSystems.getDefault().getPath(String.valueOf(imgFile));
		            Path destinoPath = FileSystems.getDefault().getPath("Media/"+elID+".png");
		            try {
		                Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
		            }catch (Exception e) {
		            	e.printStackTrace();
					}
		            Historial his2 = new Historial();
		            his2.setFotografia("Media/"+elID+".png");
		            his2.setId(elID);
		            his2.setProducto(prod);
		            his2.setFecha(fecha2.getText());
		            daoHistorial.actualizaHistorial(his2);
				}
				stage.close();
			} else {
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Informacion");
				alerta.setHeaderText(null);
				alerta.setContentText("El historial no fue agregado!");
				alerta.showAndWait();
			}
		}
	}
	
	@FXML
	void fechaClick() {
		if(fecha.getValue() == null ) {
			
		} else {
			fecha2.setText(String.valueOf(fecha.getValue()));
		}
	}
    
	
	@FXML
	void agregarFotoClick() {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // Obtener la imagen seleccionada
        imgFile = fileChooser.showOpenDialog(null);
        
        if(imgFile != null) {
        	Image image = new Image("file:" + imgFile.getAbsolutePath());
            imagen.setImage(image);
        }
	}
	
	@FXML
	void cancelarClick() {
		stage.close();
	}
    
	public void setProd(Producto prod) {
		this.prod = prod;
	}
	
	public void setDaoHistorial(SessionFactory factory) {
		this.daoHistorial = new DAOhistorial(factory);
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
