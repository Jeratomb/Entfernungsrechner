package com.example.gitdemo;

import javafx.scene.control.ChoiceBox;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class DistanceCalc {

    private static  String[] staedte;
    private static  int[][] entfernungen;

    public static String[] getStaedte() {

        return staedte;
    }

    public static void refreshData() {
        staedte = Data.getStaedte();
        entfernungen = Data.getEntfernung();
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
        if(!checkInput(str) || !checkInput(end)) return -1;
        if(!checkDirect(str, end)) return -2;
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
