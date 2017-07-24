package edu.cnm.deepdive.security.ui;

import java.util.ResourceBundle;

import edu.cnm.deepdive.security.core.SecurePasswordGenerator;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuardUi extends Application {

private static final String STYLE_SHEET = "/styles/application.css";
private static final String LAYOUT_RESOURCE = "/layouts/Generate.fxml";
public static final String UI_BUNDLE = "resources/ui"; 
public static final String WINDOW_TITLE_KEY = "window.title";
  
  @Override
  public void start(Stage primaryStage) throws Exception {
      ResourceBundle uiBundle = ResourceBundle.getBundle(UI_BUNDLE);
      Pane pane = FXMLLoader.load(getClass().getResource(LAYOUT_RESOURCE), uiBundle);
      Scene scene = new Scene(pane,450, 450);
      scene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setTitle(uiBundle.getString(WINDOW_TITLE_KEY));
      primaryStage.show();   
    }

  public static void main(String[] args) {
      launch(args);
      
      }
  }


