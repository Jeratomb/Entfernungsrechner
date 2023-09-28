package com.example.gitdemo;

import javafx.scene.control.ChoiceBox;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class DistanceCalc {

    private static  String[] staedte;
    private static  int[][] entfernungen;
    private static  int[][] matrix;

    public static String[] getStaedte() {

        return staedte;
    }

    public static void refreshData() {
        staedte = Data.getStaedte();
        entfernungen = Data.getEntfernung();
        Algorithm(entfernungen);
    }

    public static boolean checkInput(String sr){
        for(String str : staedte){
            if(str.equalsIgnoreCase(sr)) return true;
        }
        return false;
    }
    public static boolean checkDirect(String str, String end){
        int[] index = getIndex(str, end);
        if (index[0] != -1 || index[1] != -1) {
            int dist = entfernungen[index[0]][index[1]];
            if (dist >= 0) return true;
        }
        return false;
    }

    public static int[] getIndex(String str, String end){
        int[] index = {-1,-1};
        for(int i = 0; i < staedte.length; i++){
            if(staedte[i].equalsIgnoreCase(str)) index[0] = i;
            if(staedte[i].equalsIgnoreCase(end)) index[1] = i;
        }
        return index;
    }
    public static int getDist(String str, String end){
        if(!checkInput(str) || !checkInput(end)) return -2;
        if(!checkDirect(str, end)) return -1;
        else {
            int[] index = getIndex(str, end);
            int dist = entfernungen[index[0]][index[1]];
            return dist;
        }
    }

    public static String getOutput(ChoiceBox<String> str, ChoiceBox<String> end){
        String f1 = str.getValue();
        String f2 = end.getValue();
        return f1 + " bis " + f2 + " Entfernung: " + getDist(f1, f2) + " km";
    }

    public static void Algorithm(int[][] entfernungen){
        int n = entfernungen.length;
        matrix = new int[n][n];
        int MAX_DISTANCE = Integer.MAX_VALUE - 1;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(entfernungen[i][j] == -1) matrix[i][j] = MAX_DISTANCE;
                else matrix[i][j] = entfernungen[i][j];
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] < MAX_DISTANCE && matrix[k][j] < MAX_DISTANCE) {
                        int pSp = matrix[i][k] + matrix[k][j];
                        if(pSp < matrix[i][j]) matrix[i][j] = pSp;
                    }
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int k = 0; k < n; k++){
                if(matrix[i][k] == MAX_DISTANCE) entfernungen[i][k] = -1;
                else entfernungen[i][k] = matrix[i][k];
            }
        }
    }

}
