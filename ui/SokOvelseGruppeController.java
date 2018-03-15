

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class SokOvelseGruppeController implements Initializable, ControlledScreen{

	ScreensController myController;

    @FXML
    private ListView<?> guppe;
    @FXML
    private ListView<?> ovelse;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub	
	}

    @FXML
    void goToHome(ActionEvent event) {
    		myController.setScreen(ApplicationDemo.HomescreenID);
    }

	@Override
	public void setScreenParent(ScreensController screenPage) {
		myController = screenPage;
		
	}


}

