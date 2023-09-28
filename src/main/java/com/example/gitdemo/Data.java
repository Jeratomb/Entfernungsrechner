package com.example.gitdemo;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Data {

    public static String CsvFilePath = "src/data.csv";

    public static String[] getStaedte() {
        ArrayList<String> staedte = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(CsvFilePath));
            String line = bufferedReader.readLine();
            if (line != null) {
                String[] cities = line.split(",");
                staedte.addAll(Arrays.asList(cities).subList(1, cities.length));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return staedte.toArray(new String[staedte.size()]);
    }


    public static int[][] getEntfernung() {
        ArrayList<int[]> entfernung = new ArrayList<>();
        int x = 0;
        int[][] data = new int[getStaedte().length][getStaedte().length];
        try{
            FileReader fr = new FileReader(CsvFilePath);
            Scanner sc = new Scanner(fr);
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] lines = line.split(",");
                int[] rows = new int[lines.length - 1];
                for (int i = 1; i < lines.length;i++) {
                    rows[i - 1] = Integer.parseInt(lines[i]);
                }
                entfernung.add(rows);
                for(int i = 0; i < rows.length; i++) {
                    data[x][i] = entfernung.get(x)[i];
                }
                x++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

}
