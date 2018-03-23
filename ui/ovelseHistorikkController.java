import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ovelseHistorikkController implements Initializable, ControlledScreen{

    private ScreensController myController;
    private Treningsøkt valgtØkt; //økten som er trykket på i tabellen. brukes for å vite hvilken økt man skriver notat til.

    @FXML
    private TableView<Treningsøkt> treningsokter;
    @FXML
    private TableColumn<Treningsøkt, Date> Dato;
    @FXML
    private TableColumn<?, ?> Tidspunkt;
    @FXML
    private TableColumn<?, ?> Varighet;
    @FXML
    private TextField n;
    @FXML
    private Button nullstill;
    @FXML
    private Button visNotat_btn;
    @FXML
    private Text visNotat_txt;
    @FXML
    private AnchorPane notat_pane;
    @FXML
    private TextArea notat_txt;
    @FXML
    private Text valgtØktInfo;
    @FXML
    private Button displayNotat_btn;
    @FXML
    private Button loggNotat_btn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        visNotat_txt.setVisible(false);
        visNotat_btn.setVisible(false);
        displayNotat_btn.setVisible(false);
        Dato.setCellValueFactory(new PropertyValueFactory<>("Dato"));
        Tidspunkt.setCellValueFactory(new PropertyValueFactory<>("Tidspunkt"));
        Varighet.setCellValueFactory(new PropertyValueFactory<>("Varighet"));
        loadTableView();
        treningsokter.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                valgtØkt = treningsokter.getSelectionModel().getSelectedItem();
                displayNotat_btn.setVisible(true);
                visNotat_txt.setVisible(true);
                visNotat_btn.setVisible(true);
                System.out.println((treningsokter.getSelectionModel().getSelectedItem())); //printer ut treningsøkt-objektet/instansen

            }
        });

    }

    public void loadTableView(){
        myController.dbController.loadTableView(treningsokter);
    }

    public void nSisteOkter(){
        ObservableList<Treningsøkt> nOkter = FXCollections.observableArrayList();
        if (n.getText().length() == 0){
            resetTable();
        }else{
            nullstill.setVisible(true);
            int i = 0;
            for (Treningsøkt t: treningsokter.getItems().sorted()){
                if (i < Integer.parseInt(n.getText())){
                    i++;
                    nOkter.add(t);
                }
            }
        }
        treningsokter.setItems(nOkter);
    }
    @FXML
    public void resetTable(){
        n.setText("");
        nullstill.setVisible(false);
        ObservableList<Treningsøkt> nOkter = FXCollections.observableArrayList();
        for(Treningsøkt t: Treningsøkt.økter){
            nOkter.add(t);
        }
        treningsokter.setItems(nOkter);
    }

    @FXML
    public void displayNotat(){
        valgtØktInfo.setText(valgtØkt.toString());
        String notat = myController.dbController.displayNotat(valgtØkt.getID());
        notat_txt.setText(notat);
        loggNotat_btn.setVisible(false);
        notat_pane.setVisible(true);

    }

    @FXML
    public void visNotat(){
        valgtØktInfo.setText(valgtØkt.toString());
        loggNotat_btn.setVisible(true);
        notat_pane.setVisible(true);
    }
    @FXML
    public void lukkNotat(){
        notat_txt.clear();
        notat_pane.setVisible(false);
    }
    @FXML
    public void loggNotat(){
        String notat = notat_txt.getText();
        System.out.println(notat);
        myController.dbController.addNotat(valgtØkt.getID(), notat);
    }

    @FXML
    public void goToHome(){
        myController.setScreen(ApplicationDemo.HomescreenID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }


}
