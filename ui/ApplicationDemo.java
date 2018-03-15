package ui;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ApplicationDemo extends Application{

	public static String HomescreenID = "homeScreen";
    public static String HomescreenFile = "homeScreen.fxml";
	public static String OktID = "Okt";
    public static String OktFile = "Okt.fxml";
    public static String OvelseID = "Ovelse";
    public static String OvelseFile = "Ovelse.fxml";
    public static String OvelseGruppeID = "OvelseGruppe";
    public static String OvelseGruppeFile = "OvelseGruppe.fxml";
    public static String ApparatID = "Apparat";
    public static String ApparatFile = "Apparat.fxml";
    public static String SokOvelseGruppeID = "SokOvelseGruppe";
    public static String SokOvelseGruppeFile = "SokOvelseGruppe.fxml";
    
	
	@Override
	public void start(Stage stage) throws Exception {
		ScreensController container = new ScreensController();
		
		container.loadScreen(ApplicationDemo.HomescreenID,ApplicationDemo.HomescreenFile);
		container.loadScreen(ApplicationDemo.OktID,ApplicationDemo.OktFile);
		container.loadScreen(ApplicationDemo.OvelseID, ApplicationDemo.OvelseFile);
		container.loadScreen(ApplicationDemo.OvelseGruppeID, ApplicationDemo.OvelseGruppeFile);
		container.loadScreen(ApplicationDemo.ApparatID, ApplicationDemo.ApparatFile);
		container.loadScreen(ApplicationDemo.SokOvelseGruppeID, ApplicationDemo.SokOvelseGruppeFile);
		
		container.setScreen(ApplicationDemo.HomescreenID);
				
		StackPane root = new StackPane();//Back-to-front stack of children
        root.getChildren().addAll(container);//adds all screens from the ScreensContainer to the StackPane
        Scene scene = new Scene(root,500,500);
        stage.setScene(scene);
        stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
