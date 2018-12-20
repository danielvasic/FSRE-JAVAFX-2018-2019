package restoran.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import restoran.model.Korisnik;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    Button registracijaBtn;

    @FXML
    TextField registerImeTxt;

    @FXML
    TextField registerPrezimeTxt;

    @FXML
    TextField registerJMBGTxt;

    @FXML
    TextField registerEmailTxt;

    public void registerAction (ActionEvent e) {
        String ime = this.registerImeTxt.getText();
        String prezime = this.registerPrezimeTxt.getText();
        String JMBG = this.registerJMBGTxt.getText();
        String email = this.registerEmailTxt.getText();

        Korisnik k = new Korisnik(0, ime, prezime, JMBG, email);
        k.create();

        this.registerImeTxt.setText("");
        this.registerPrezimeTxt.setText("");
        this.registerJMBGTxt.setText("");
        this.registerEmailTxt.setText("");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
