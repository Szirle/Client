package com.szymonlukiewicz.polskieeplatnosci;

import org.json.JSONException;
import org.json.JSONObject;

class Station {
    int id;
    String name;
    String address;
    String city;
    String commune;
    String district;
    String province;

    double latitude;
    double longitude;

    IndexLevel overallIndexLevel;
    IndexLevel so2IndexLevel;
    IndexLevel no2IndexLevel;
    IndexLevel pm10IndexLevel;
    IndexLevel pm25IndexLevel;
    IndexLevel o3IndexLevel;

    boolean isExpanded;

    public Station(JSONObject stationJSONObject) throws JSONException {
        this.id = stationJSONObject.getInt("id");
        this.name = stationJSONObject.getString("stationName");
        this.address = stationJSONObject.getString("addressStreet");
        JSONObject cityJSONObject = stationJSONObject.getJSONObject("city");
        JSONObject communeJSONObject = cityJSONObject.getJSONObject("commune");
        this.city = cityJSONObject.getString("name");
        this.commune = communeJSONObject.getString("communeName");
        this.district = communeJSONObject.getString("districtName");
        this.province = communeJSONObject.getString("provinceName");
        this.latitude = Double.parseDouble(stationJSONObject.getString("gegrLat"));
        this.longitude = Double.parseDouble(stationJSONObject.getString("gegrLon"));
        this.isExpanded = false;
    }

    public void setIndexLevels(JSONObject airPollutionJSONObject) throws JSONException {
        if(!airPollutionJSONObject.isNull("stIndexLevel")) {
            overallIndexLevel = new IndexLevel(airPollutionJSONObject.getJSONObject("stIndexLevel"));
        }
        if(!airPollutionJSONObject.isNull("so2IndexLevel")) {
            so2IndexLevel = new IndexLevel(airPollutionJSONObject.getJSONObject("so2IndexLevel"));
        }
        if(!airPollutionJSONObject.isNull("no2IndexLevel")) {
            no2IndexLevel = new IndexLevel(airPollutionJSONObject.getJSONObject("no2IndexLevel"));
        }
        if(!airPollutionJSONObject.isNull("pm10IndexLevel")) {
            pm10IndexLevel = new IndexLevel(airPollutionJSONObject.getJSONObject("pm10IndexLevel"));
        }
        if(!airPollutionJSONObject.isNull("pm25IndexLevel")) {
            pm25IndexLevel = new IndexLevel(airPollutionJSONObject.getJSONObject("pm25IndexLevel"));
        }
        if(!airPollutionJSONObject.isNull("o3IndexLevel")){
            o3IndexLevel = new IndexLevel(airPollutionJSONObject.getJSONObject("o3IndexLevel"));
        }
    }
}

class IndexLevel{
    int id;
    String indexLevelName;

    IndexLevel(JSONObject indexLevelJSONObject) throws JSONException {
        this.id = indexLevelJSONObject.getInt("id");
        this.indexLevelName = indexLevelJSONObject.getString("indexLevelName");
    }
}
