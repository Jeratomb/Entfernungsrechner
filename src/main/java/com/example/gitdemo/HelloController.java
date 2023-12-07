package com.example.gitdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Arrays;
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
    @FXML
    private Button btnRef;
    @FXML
    private Label lblTime;
    private DistanceCalc dist = new DistanceCalc();

    @Deprecated
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblOutput.setText("Warte auf Benutzereingabe...");
        refresh();
    }

    @FXML
    public void refresh(){
        lblOutput.setText("");
        lblTime.setText("");
        chbx1.getItems().clear();
        chbx2.getItems().clear();

        dist.refreshData();

        chbx1.getItems().addAll(Arrays.stream(dist.getStaedte()).sorted().toList());
        chbx2.getItems().addAll(Arrays.stream(dist.getStaedte()).sorted().toList());
    }

    @FXML
    public void onCalc(ActionEvent actionEvent) {
        dist.Algorithm(Data.getEntfernung());
        int state = dist.getDist(chbx1.getValue(), chbx2.getValue());
        int time = dist.getWarteZeit(chbx1.getValue(), chbx2.getValue());

        lblOutput.setText("");
        setOutput(state, time);

        if(state >=0) lstHist.getItems().add(dist.getOutput(chbx1, chbx2));

    }

    @FXML
    public void onRef(ActionEvent actionEvent) {
        refresh();
    }
    @Deprecated
    public void setOutput(int value, int time) {
        lblOutput.setStyle("-fx-text-fill: red;");
        switch (value) {
            case -2 -> lblOutput.setText("Fehlerhafte Eingabe! Stadt nicht im geplanten Verkehr enthalten!");
            case -1 -> lblOutput.setText("Keine Direktverbindung möglich");
            default -> dist.getOutput(chbx1, chbx2);
        }
        int h = time / 60;
        int m = time % 60;
        lblTime.setText(h + "h "+ m + "m");
        lblTime.setStyle("-fx-text-fill: #0f420f");
    }
    @FXML
    public void onClearList(ActionEvent actionEvent) {
        lstHist.getItems().clear();
    }
}