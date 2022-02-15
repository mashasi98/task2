package com.example.task2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class main {

    public static void main(String[] args) {

        TreeMap<Object, Object> phoneByCountryMap = new TreeMap<>();
        String uriGetCountry = "https://onlinesim.ru/price-list-data?type=receive";
        HttpClient client = HttpClient.newHttpClient();
        phoneByCountryMap = client.sendAsync(getClientRequest(uriGetCountry), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(main::getPriceListFromRequest)
                .join();

        phoneByCountryMap.forEach((key, value) -> System.out.println(key + "  : " + value));
    }

    public static HttpRequest getClientRequest(String uri) {

        return HttpRequest
                .newBuilder()
                .uri(URI.create(uri))
                .build();
    }

    public static TreeMap<Object, Object> getPriceListFromRequest(String responseBody) {
        ObjectMapper mapper = new ObjectMapper();
        TreeMap<Object, Object> allInformationMap = new TreeMap<>();
        try {
            PriceListData responseToClass = mapper.readValue(responseBody, PriceListData.class);

            TreeMap<String, String> countryName = responseToClass.getCountryNameMap();
            TreeMap<String, HashMap<String, String>> priseListMap = responseToClass.getPriseListMap();

            String key, country = "";

            for (Map.Entry entry : priseListMap.entrySet()) {
                key = "country_" + entry.getKey();
                for (Map.Entry entryName : countryName.entrySet()) {
                    if (key.equals(entryName.getKey())) {
                        country = (String) entryName.getValue();
                    }
                }
                if (!country.isEmpty()) {
                    allInformationMap.put(country, entry.getValue());
                }
                country ="";
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return allInformationMap;
    }
}