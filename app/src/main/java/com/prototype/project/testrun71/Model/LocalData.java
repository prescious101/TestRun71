package com.prototype.project.testrun71.Model;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prototype.project.testrun71.App;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class LocalData {

    public void saveArrayListPkgName(ArrayList<String> list, String key){
        if(list.contains(null)){
            Toast.makeText(App.getAppContext(),"NULL", Toast.LENGTH_SHORT).show();
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getAppContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        Log.i(TAG, "saveArrayList: "+list.toString());
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public ArrayList<String> getArrayListPkgName(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getAppContext());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void saveArrayListAppName(ArrayList<String> list, String key){
        if(list.contains(null)){
            Toast.makeText(App.getAppContext(),"NULL", Toast.LENGTH_SHORT).show();
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getAppContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        Log.i(TAG, "saveArrayList: "+list.toString());
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public ArrayList<String> getArrayListAppName(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getAppContext());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
