

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OvelseGruppeController implements Initializable, ControlledScreen{

	ScreensController myController;
	
    @FXML
    private TextField navn;
    @FXML
    private TextArea beskrivelse;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

    @FXML
    void goToHome(ActionEvent event) {
    		myController.setScreen(ApplicationDemo.HomescreenID);
    }

    @FXML
    void leggTil(ActionEvent event) {
    		String gruppeNavn = navn.getText();
    		String gruppeBeskrivelse = beskrivelse.getText();
    		System.out.println(gruppeNavn+"\n"+gruppeBeskrivelse);
    		myController.dbController.addOvelsegruppe(gruppeNavn, gruppeBeskrivelse);
    }

	@Override
	public void setScreenParent(ScreensController screenPage) {
		myController = screenPage;
		
	}


}
