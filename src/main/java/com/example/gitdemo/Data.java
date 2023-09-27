package com.example.gitdemo;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {

    public static String CsvFilePath = "src/data.csv";

    public static String[] getStaedte() {
        ArrayList<String> staedte = new ArrayList<String>();
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
        ArrayList<int[]> entfernung = new ArrayList();
        int[][] data = new int[0][0];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CsvFilePath))) {
            bufferedReader.readLine();
            while (bufferedReader.readLine() != null) {
                String line = bufferedReader.readLine();
                String[] lines = line.split(",");
                int[] rows = new int[lines.length - 1];
                for (int i = 1; i < lines.length;i++) {
                    rows[i - 1] = Integer.parseInt(lines[i]);
                }
                entfernung.add(rows);
                data = new int[entfernung.size()][entfernung.size()];
                for(int i = 0; i < entfernung.size(); i++) {
                    data[i][0] = entfernung.get(i)[0];
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
