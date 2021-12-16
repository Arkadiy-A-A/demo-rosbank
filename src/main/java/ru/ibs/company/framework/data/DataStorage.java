package ru.ibs.company.framework.data;

import java.util.*;

public class DataStorage {

    private static HashMap<String, String> dataStorage = new HashMap<>();
    private static DataStorage INSTANCE = null;

    private DataStorage(){
    }

    public static DataStorage getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new DataStorage();
        }
        return INSTANCE;
    }

    public void addVariable(String key, String value) {
        dataStorage.put(key, value);
    }

    public String getVariableValue(String key) {
        return dataStorage.get(key);
    }



}
