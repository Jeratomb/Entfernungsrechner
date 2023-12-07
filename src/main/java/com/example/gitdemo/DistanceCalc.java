package com.example.gitdemo;

import javafx.scene.control.ChoiceBox;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class DistanceCalc {

    private String[] staedte;
    private int[][] entfernungen;
    private int[][] matrix;
    private  int[][] commuteTimes;

    public String[] getStaedte() {

        return staedte;
    }

    //
    public void refreshData() {
        staedte = Data.getStaedte();
        entfernungen = Data.getEntfernung();
        Algorithm(entfernungen);

    }

    public  int getWarteZeit(String s1, String s2){
        calcComTime(s1, s2);
        int[] index = getIndex(s1,s2);
        return commuteTimes[index[0]][index[1]];
    }
    // checking if the passed String is in the array
    public  boolean checkInput(String sr){
        for(String str : staedte){
            if(str.equalsIgnoreCase(sr)) return true;
        }
        return false;
    }

    // checking if the 2 points have an existing direct connection
    public  boolean checkDirect(String str, String end){
        int[] index = getIndex(str, end);
        if (index[0] != -1 || index[1] != -1) {
            int dist = entfernungen[index[0]][index[1]];
            return dist >= 0;
        }
        return false;
    }

    //Index in the array of start and end to get Distance
    public  int[] getIndex(String str, String end){
        int[] index = {-1,-1};
        for(int i = 0; i < staedte.length; i++){
            if(staedte[i].equalsIgnoreCase(str)) index[0] = i;
            if(staedte[i].equalsIgnoreCase(end)) index[1] = i;
        }
        return index;
    }

    //get Distance from A to B
    public  int getDist(String str, String end){
        if(!checkInput(str) || !checkInput(end)) return -2;
        if(!checkDirect(str, end)) return -1;
        else {
            int[] index = getIndex(str, end);
            return entfernungen[index[0]][index[1]];
        }
    }


    // formatted output
    public  String getOutput(ChoiceBox<String> str, ChoiceBox<String> end){
        String f1 = str.getValue();
        String f2 = end.getValue();
        return f1 + " bis " + f2 + " Entfernung: " + getDist(f1, f2) + " km";
    }


    // Floyd-Warshall Algorithm to make travel over non-direct stations possible
    public  void Algorithm(int[][] entfernungen){
        int n = entfernungen.length;
        matrix = new int[n][n];

        // Initialize array and swap -1 entrys with int max value
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(entfernungen[i][j] == -1) matrix[i][j] = Integer.MAX_VALUE;
                else matrix[i][j] = entfernungen[i][j];
            }
        }

        // Floyd-Warshall Algorithm to replace max values with the distances
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][k] < Integer.MAX_VALUE && matrix[k][j] < Integer.MAX_VALUE) {
                        int pSp = matrix[i][k] + matrix[k][j];
                        if(pSp < matrix[i][j]) matrix[i][j] = pSp;
                    }
                }
            }
        }

        // matrix array into entfernungen so we don´t have to change code we wrote before
        for(int i = 0; i < n; i++){
            for(int k = 0; k < n; k++){
                if(matrix[i][k] != Integer.MAX_VALUE) entfernungen[i][k] = matrix[i][k];
                else entfernungen[i][k] = -1;
            }
        }
    }


    // Calculate CommuteTime with 5 waitTime and 725 velocity
    public  void calcComTime(String s1, String s2) {
        if (entfernungen == null) return;
        int velocity = 725;
        int waitTime = 5;
        int stations = getStations(s1, s2);
        if(stations < 0) stations = 0;

        int x = entfernungen.length;
        commuteTimes = new int[x][x];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                if (entfernungen[i][j] >= 0) {
                    //TravelTime without waiting time
                    double travelTimeMinutes = ((double) entfernungen[i][j] / velocity) * 60.0 + (stations * waitTime);

                    commuteTimes[i][j] = (int) travelTimeMinutes;
                }
            }
        }
    }

    public  int getStations(String s1, String s2) {
        if (!checkInput(s1) || !checkInput(s2)) return -2;
        if (!checkDirect(s1, s2)) return -1;

        int[] indices = getIndex(s1, s2);
        int start = indices[0];
        int end = indices[1];

        if (start == end) return 0;


        int shortestDistance = matrix[start][end];

        if (shortestDistance == Integer.MAX_VALUE) return -1;

        // Count of stations (start and end not included)
        int stationCount = 0;
        for (int i = 0; i < entfernungen.length; i++) {
            if (i != start && i != end) {
                if (matrix[start][i] + matrix[i][end] == shortestDistance) {
                    stationCount++;
                }
            }
        }
        return stationCount;
    }


}
