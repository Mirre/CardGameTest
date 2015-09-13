package com.mirre.cardgame;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class CardGame extends Application {
	
	private BorderPane rootLayout;
	private Stage stage;
	
	@Override
	public void start(Stage stage) {
		this.stage = stage;
		
		rootLayout = new BorderPane();
		loadController();
		
		
		
		Scene scene = new Scene(rootLayout);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}
	
	private void loadController(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CardGame.class.getResource("view/GameView.fxml"));
			rootLayout.setCenter(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public Stage getStage() {
		return stage;
	}
	
	public BorderPane getRootLayout() {
		return rootLayout;
	}
	
}
