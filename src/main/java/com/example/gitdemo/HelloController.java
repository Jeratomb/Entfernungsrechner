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
    private Label lblOutput;
    @FXML
    private Button btnClear;
    @FXML
    private ListView lstHist;
    @FXML
    private ChoiceBox<String> chbx1;
    @FXML
    private ChoiceBox<String> chbx2;

    @Deprecated
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblOutput.setText("Warte auf Benutzereingabe...");
        refresh();
    }

    @FXML
    public void refresh(){
        lblOutput.setText("");
        DistanceCalc.refreshData();

        if(chbx1.getItems().size() != DistanceCalc.getStaedte().length) {
            for (String s : DistanceCalc.getStaedte()) {
                chbx1.getItems().add(s);
                chbx2.getItems().add(s);
            }
        }else chbx1.getItems().clear();
    }

    @FXML
    public void onCalc(ActionEvent actionEvent) {
        int state = DistanceCalc.getDist(chbx1.getValue(), chbx2.getValue());

        lblOutput.setText("");
        setLblOutput(state);

        if(state >=0) lstHist.getItems().add(DistanceCalc.getOutput(chbx1, chbx2));

    }

    @FXML
    public void onRef(ActionEvent actionEvent) {
        refresh();
    }
    @Deprecated
    public void setLblOutput(int value) {
        lblOutput.setStyle("-fx-text-fill: red;");
        switch (value) {
            case -3 -> lblOutput.setText("Keine Direktverbindung möglich");
            case -2 -> lblOutput.setText("Fehlerhafte Eingabe! Stadt nicht im geplanten Verkehr enthalten!");
            case -1 -> lblOutput.setText("Keine Direktverbindung möglich");
            default -> DistanceCalc.getOutput(chbx1, chbx2);
        }
    }
    @FXML
    public void onClearList(ActionEvent actionEvent) {
        lstHist.getItems().clear();
    }

}