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

public class ActualizarHistorialController {
	private DAOhistorial daoHistorial;
	private Producto product;
	private Historial his;
	private Stage stage;
	private File imgFile;
	
	@FXML private DatePicker fecha;
    @FXML private TextField fecha2;
    @FXML private ImageView imagen;

    @FXML
    void agergarClick() {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmacion");
    	alert.setHeaderText("Elige una respuesta");
    	alert.setContentText("¿Esta seguro de actualizar el historial?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		
    		Historial his2 = new Historial();
    		his2.setId(his.getId());
    		his2.setFecha(fecha2.getText());
    		his2.setProducto(product);
    		
    		daoHistorial.actualizaHistorial(his2);
    		if(imagen.getImage() == null) {
    			
    		} else {
    			Path origenPath = FileSystems.getDefault().getPath(String.valueOf(imgFile));
	            Path destinoPath = FileSystems.getDefault().getPath("Media/"+his.getId()+".png");
	            try {
	                Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
	            }catch (Exception e) {
	            	e.printStackTrace();
				}
	            his2.setId(his.getId());
	    		his2.setFecha(fecha2.getText());
	    		his2.setProducto(product);
	    		his2.setFotografia("Media/"+his.getId()+".png");
	    		
	    		daoHistorial.actualizaHistorial(his2);
    		}
			stage.close();
    	} else {
    		Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Informacion");
			alerta.setHeaderText(null);
			alerta.setContentText("El historial no fue actualizado!");
			alerta.showAndWait();
    	}
    }
    
    @FXML
    void borrarClick() {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmacion");
    	alert.setHeaderText("Elige una respuesta");
    	alert.setContentText("¿Esta seguro de borrar el historial?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		Historial borrar = new Historial();
    		borrar.setId(his.getId());
    		daoHistorial.borrarHistorial(borrar);
    		stage.close();
    	} else {
    		Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Informacion");
			alerta.setHeaderText(null);
			alerta.setContentText("Se ha cancelado la acción");
			alerta.showAndWait();
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
    void fechaClick() {
    	if(fecha.getValue() == null ) {
			
		} else {
			fecha2.setText(String.valueOf(fecha.getValue()));
		}
    }
    
    @FXML
    void cancelarClick() {
    	stage.close();
    }
	
	
	public void setStage(Stage stage) {
		this.stage = stage;
		llenarDatos();
	}
	
	public void setDaoHistorial(SessionFactory factory) {
		this.daoHistorial = new DAOhistorial(factory);
	}
	
	public void setProduct(Producto product) {
		this.product = product;
	}
	
	public void setHis(Historial his) {
		this.his = his;
	}
	
	private void llenarDatos() {
		try {
			fecha2.setText(his.getFecha());
			imgFile = new File(his.getFotografia());
			Image image = new Image("file:" + imgFile.getAbsolutePath());
            imagen.setImage(image);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
