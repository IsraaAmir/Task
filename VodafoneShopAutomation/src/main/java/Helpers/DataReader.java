package Helpers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {
    private static DataReader dataReader;
    private BufferedReader reader;

    private Gson gson;
    public DataReader(){
        gson = new Gson();
    }

    public static DataReader getInstance(){

        if (dataReader == null )
            dataReader = new DataReader();
        return dataReader;

    }

    private BufferedReader readFile (String path) {

        try {
            reader=new BufferedReader(new FileReader(path));
        }
        catch (FileNotFoundException e){

            throw new RuntimeException(e);
        }

        return reader;
    }



    private void closeBuffer(){


        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public JsonObject readData(String path){

        JsonObject data;
        data =gson.fromJson(readFile(path),JsonObject.class);
        closeBuffer();
        return data;
    }
}