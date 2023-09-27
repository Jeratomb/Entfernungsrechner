package com.example.gitdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    @FXML
    private TextField txtN1;
    @FXML
    private TextField txtN2;
    @FXML
    private Button btnCalc;
    @FXML
    private TextField txtErg;
    @FXML
    private Label lblError;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    lblError.setText("Warte auf Benutzereingabe...");
    }
    @FXML
    public void onCalc(ActionEvent actionEvent) {
        double erg = 0;
        String s1 = txtN1.getText();
        String s2 = txtN2.getText();
        if(DistanceCalc.checkInput(s1) && DistanceCalc.checkInput(s2)) {
            if (DistanceCalc.checkDirect(s1, s2)) {
                erg = DistanceCalc.getDist(s1, s2);
                txtErg.setText(Double.toString(erg));
            } else txtErg.setText("Keine Direktverbindung möglich");
        } else lblError.setText("Fehlerhafte Eingabe! Stadt nicht im geplanten Verkehr enthalten!");
    }

}