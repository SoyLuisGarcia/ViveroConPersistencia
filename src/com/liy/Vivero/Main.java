package com.liy.Vivero;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.liy.Vivero.Model.Historial;
import com.liy.Vivero.Model.Producto;
import com.liy.Vivero.View.*;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Principal.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,400,200);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Viveronix");
			PrincipalController controller = loader.getController();
			controller.inicio(this, primaryStage);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void conexion() {
		try {
			Configuration configuration = new Configuration();
			System.err.println("Leyendo configuracion." );
            configuration.configure("hibernate.cfg.xml");
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
            openLogin();
		} catch (Throwable ex) {
			Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error a surgido");
            alert.setContentText("No se ha podidom conectar a la base de datos");
            alert.showAndWait();
		}
	}
	
	public void openLogin() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Login.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageLogin = new Stage();
			Scene scene = new Scene(root, 400, 400);
			stageLogin.setScene(scene);
			stageLogin.setTitle("Login");
			stageLogin.setResizable(false);
			LoginController controller = loader.getController();
			controller.setMain(this);
			controller.setStage(stageLogin);
			controller.setDaoUsuario(factory);
			stageLogin.initModality(Modality.WINDOW_MODAL);
			stageLogin.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openMenu() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Menu.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageMenu = new Stage();
			Scene scene = new Scene(root, 800, 600);
			stageMenu.setScene(scene);
			stageMenu.setTitle("Menu");
			stageMenu.setResizable(false);
			MenuController controller = loader.getController();
			controller.inicio(this, stageMenu);
			stageMenu.initModality(Modality.WINDOW_MODAL);
			stageMenu.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openTipo(Stage stageANT) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Tipo.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageTipo = new Stage();
			Scene scene = new Scene(root, 300, 400);
			stageTipo.setScene(scene);
			stageTipo.setTitle("Tipos");
			stageTipo.setResizable(false);
			stageTipo.initModality(Modality.WINDOW_MODAL);
			stageTipo.initOwner(stageANT);
			TipoController controller = loader.getController();
			controller.setStage(stageTipo);
			controller.setFactory(factory);
			stageTipo.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openProducto(Stage stageANT) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Producto.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageProducto = new Stage();
			Scene scene = new Scene(root, 600, 400);
			stageProducto.setScene(scene);
			stageProducto.setTitle("Productos");
			stageProducto.setResizable(false);
			stageProducto.initModality(Modality.WINDOW_MODAL);
			stageProducto.initOwner(stageANT);
			ProductoController controller = loader.getController();
			controller.setStage(stageProducto);
			controller.setMain(this);
			controller.setProducto(factory);
			stageProducto.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openNewProd(Stage stageANT) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/NewProd.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageNew = new Stage();
			Scene scene = new Scene(root, 300, 350);
			stageNew.setScene(scene);
			stageNew.setTitle("Nuevo Producto");
			stageNew.setResizable(false);
			stageNew.initModality(Modality.WINDOW_MODAL);
			stageNew.initOwner(stageANT);
			NewProdController controller = loader.getController();
			controller.setStage(stageNew);
			controller.setDaoTipo(factory);
			stageNew.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openActualizarProd(Stage stageANT, Producto prod) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/ActualizarProd.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root, 300, 350);
			Stage stageActProd = new Stage();
			stageActProd.setScene(scene);
			stageActProd.setTitle("Actualizar Producto");
			stageActProd.setResizable(false);
			stageActProd.initModality(Modality.WINDOW_MODAL);
			stageActProd.initOwner(stageANT);
			ActualizarProdController controller = loader.getController();
			controller.setDaoTipo(factory);
			controller.setProd(prod);
			controller.setStage(stageActProd);
			stageActProd.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openHistorial(Stage stageANT, Producto prod) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Historial.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageHistorial = new Stage();
			Scene scene = new Scene(root, 400, 400);
			stageHistorial.setScene(scene);
			stageHistorial.setTitle("Historial");
			stageHistorial.setResizable(false);
			stageHistorial.initModality(Modality.WINDOW_MODAL);
			stageHistorial.initOwner(stageANT);
			HistorialController controller = loader.getController();
			controller.setMain(this, prod, stageHistorial, factory);
			stageHistorial.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openNewHis(Stage stageANT, Producto prod) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/NuevoHistorial.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageNewHis = new Stage();
			Scene scene = new Scene(root, 400, 450);
			stageNewHis.setScene(scene);
			stageNewHis.setTitle("Nuevo historial");
			stageNewHis.setResizable(false);
			stageNewHis.initModality(Modality.WINDOW_MODAL);
			stageNewHis.initOwner(stageANT);
			NuevoHistorialController controller = loader.getController();
			controller.setProd(prod);
			controller.setDaoHistorial(factory);
			controller.setStage(stageNewHis);
			stageNewHis.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openActHis(Stage stageANT, Producto prod, Historial his) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/ActualizarHistorial.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root, 400, 450);
			stage.setScene(scene);
			stage.setTitle("Actualizar historial de foto");
			stage.setResizable(false);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(stageANT);
			ActualizarHistorialController controller = loader.getController();
			controller.setDaoHistorial(factory);
			controller.setHis(his);
			controller.setProduct(prod);
			controller.setStage(stage);
			stage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
