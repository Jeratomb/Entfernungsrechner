package com.example.gitdemo;

import javafx.scene.control.ChoiceBox;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class DistanceCalc {

    private static  String[] staedte = {"Berlin", "Erfurt", "Essen", "Dresden", "Frankfurt", "Hamburg", "Hannover", "München", "Plattling", "Stuttgart"};
    private static  int[][] entfernungen = {
            {0,326,-1,178,-1,320,-1,-1,-1,-1},//Berlin
            {326,0,312,-1,263,382,-1,329,-1,344},//Erfurt
            {-1,312,0,-1,-1,-1,194,-1,-1,-1},//Essen
            {178,-1,-1,0,-1,-1,-1,-1,-1,-1},//Dresden
            {-1,263,-1,-1,0,-1,-1,-1,-1,179},//Frankfurt
            {320,382,-1,-1,-1,0,-1,-1,-1,-1},//Hamburg
            {-1,194,-1,-1,-1,-1,0,-1,-1,-1},//Hannover
            {-1,329,-1,-1,-1,-1,-1,0,130,-1},//München
            {-1,-1,-1,-1,-1,-1,-1,130,0,-1},//Plattling
            {-1,344,-1,-1,179,-1,-1,-1,-1,0}//Stuttgart
    };

    public static String[] getStaedte() {
        return staedte;
    }

    public static boolean staedteContains(String sr){
        boolean contains = false;
        for(String str : staedte){
            if(str.equalsIgnoreCase(sr)) {
                contains = true;
                break;
            }
        }
        return contains;
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
        if(!checkInput(str) || !checkInput(end)) return -3;
        if(!checkDirect(str, end)) return -2;
        if(!staedteContains(str) ||!staedteContains(end)) return -1;
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


}
