package com.example.gitdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    @FXML
    private Button btnCalc;
    @FXML
    private TextField txtErg;
    @FXML
    private Label lblOutput;
    @FXML
    private Button btnClear;
    @FXML
    private ListView lstHist;
    @FXML
    private Label headerLabel;
    @FXML
    private ChoiceBox<String> chbx1;
    @FXML
    private ChoiceBox<String> chbx2;
    @FXML
    private Button btnRef;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblOutput.setText("Warte auf Benutzereingabe...");
        refresh();
    }

    public void refresh(){
        lblOutput.setText("");
        DistanceCalc.refreshData();

        for (String s : DistanceCalc.getStaedte()) {
            chbx1.getItems().add(s);
            chbx2.getItems().add(s);
        }
    }

    @FXML
    public void onCalc(ActionEvent actionEvent) {
        int state = DistanceCalc.getDist(chbx1.getValue(), chbx2.getValue());

        lblOutput.setText("");
        setLblOutput(state);

        if(state >=0) lstHist.getItems().add(DistanceCalc.getOutput(chbx1, chbx2));

    }

    public void onRef(ActionEvent actionEvent) {
        refresh();
    }
    @FXML
    public void setLblOutput(int value) {
        lblOutput.setStyle("-fx-text-fill: red;");
        switch (value){
            case -3:
                lblOutput.setText("Keine Direktverbindung möglich");
                break;
            case -2:
                lblOutput.setText("Fehlerhafte Eingabe! Stadt nicht im geplanten Verkehr enthalten!");
                break;
            case -1:
                lblOutput.setText("Keine Direktverbindung möglich");
                break;
            default:
                DistanceCalc.getOutput(chbx1,chbx2);
                break;
        }
    }
    @FXML
    public void onClearList(ActionEvent actionEvent) {
        lstHist.getItems().clear();
    }
}