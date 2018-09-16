
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ApparatController implements Initializable, ControlledScreen{

	private ScreensController myController;
	
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
	    myController.dbController.addApparat(navn.getText(), beskrivelse.getText());


    }

	@Override
	public void setScreenParent(ScreensController screenPage) {
		myController = screenPage;	
	}


}

