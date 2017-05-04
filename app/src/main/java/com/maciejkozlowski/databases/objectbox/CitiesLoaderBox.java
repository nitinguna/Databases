package com.maciejkozlowski.databases.objectbox;


import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderBox {

    private static final String TAG = "###box";

    public static void insertCities(Context context, Box<CityBox> boxStore) {
        List<CityBox> cities = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("cities.csv")));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                Long id = Long.valueOf(strings[0]);
                Double latitude = Double.valueOf(strings[2]);
                Double longitude = Double.valueOf(strings[3]);
                String name = strings[1];
                CityBox city = new CityBox(id, name, latitude, longitude);
                cities.add(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        boxStore.removeAll();

        long start = System.currentTimeMillis();
        boxStore.put(cities);

        Log.d(TAG, "time: " + (System.currentTimeMillis() - start));
        Log.d(TAG, "insertCities: " + boxStore.count());
    }
}
