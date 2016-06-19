package gurstudio.com.taleitapp.model.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by gur on 6/19/2016.
 */
public class SharedPreferencesManager {

    private final SharedPreferences preferences;

    public SharedPreferencesManager(String name, Context context){
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public <T> void write(String key, T value){
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(value);

        editor.putString(key, json);
        editor.commit();
    }

    public <T> T read(String key, Class<T> type){
        Gson gson = new Gson();
        String json = preferences.getString(key, null);
        T value = gson.fromJson(json, type);
        return value;
    }
}