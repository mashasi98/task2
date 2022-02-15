package com.example.task2;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.TreeMap;

public class PriceListData {

    @JsonProperty("list")
    TreeMap<String, HashMap<String,String>> priseListMap;
    @JsonProperty("text")
    TreeMap<String,String> countryNameMap;
    @JsonProperty("countries")
     TreeMap<String,String> countryIdMap;
    @JsonProperty("type")
     String type;

    public TreeMap<String, HashMap<String, String>> getPriseListMap() {
        return priseListMap;
    }

    public TreeMap<String, String> getCountryNameMap() {
        return countryNameMap;
    }

    public TreeMap<String, String> getCountryIdMap() {
        return countryIdMap;
    }

    public String getType() {
        return type;
    }
}
