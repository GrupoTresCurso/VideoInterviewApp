package com.example.tictum.appcandidatos.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Usuario on 15/03/2017.
 */

public class HelperBeans {

    public static String getStringFromArray(String[] array){
        String result = array.toString();

        result.replace("[","");
        result.replace("]", "");

        return result;
    }

    public static String[] getArrayFromString(String string){
        String[] array = string.split(",");
        return array;
    }
}
