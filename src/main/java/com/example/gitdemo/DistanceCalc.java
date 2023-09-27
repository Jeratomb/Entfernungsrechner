package com.example.gitdemo;

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

    private static int[] getIndex(String str, String end){
        int[] index = {-1,-1};
        for(int i = 0; i < staedte.length; i++){
            if(staedte[i] == str) index[0] = i;
            if(staedte[i] == end) index[1] = i;
        }
        return index;
    }
    public static boolean checkDirect(String str, String end){
        int[] index = getIndex(str, end);
        if(entfernungen[index[0]][index[1]] > -1) return true;
        return false;
    }
    public static int getDist(String str, String end){
        int[] index = getIndex(str, end);
        int dist = entfernungen[index[0]][index[1]];
        return dist;
    }

}
