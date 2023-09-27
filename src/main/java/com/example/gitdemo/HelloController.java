package com.example.gitdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class HelloController {
    @FXML
    private TextField txtN1;
    @FXML
    private TextField txtN2;
    @FXML
    private Button btnCalc;
    @FXML
    private TextField txtErg;

    @FXML
    public void onCalc(ActionEvent actionEvent) {
        double erg = 0;
        if(DistanceCalc.checkDirect(txtN1.toString(), txtN2.toString())){
        erg = DistanceCalc.getDist(txtN1.toString(), txtN2.toString());
        txtErg.setText(Double.toString(erg));
        }else txtErg.setText("Keine Direktverbindung möglich");
    }

}