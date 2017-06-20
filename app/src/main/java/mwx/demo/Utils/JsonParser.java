package mwx.demo.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mwx.demo.Models.DataModel;

/**
 * Created by Nauman on 6/20/2017.
 */

public class JsonParser {

    public ArrayList<DataModel> parseJsonData(String json){
        ArrayList<DataModel> _data = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DataModel dataModel = new DataModel();
                if (jsonObject.has("id"))
                    dataModel.setId(jsonObject.getString("id"));
                if (jsonObject.has("color"))
                    dataModel.setColor(jsonObject.getString("color"));
                if (jsonObject.has("categories")) {
                    JSONArray jsonArrayForTitle = jsonObject.getJSONArray("categories");
                    if (jsonArrayForTitle.length() > 0) {
                        if (jsonArrayForTitle.getJSONObject(0).has("title"))
                            dataModel.setTitle(jsonArrayForTitle.getJSONObject(0).getString("title"));
                    }
                }
                if (jsonObject.has("urls")){
                    if (jsonObject.getJSONObject("urls").has("thumb")){
                        dataModel.setThumb(jsonObject.getJSONObject("urls").getString("thumb"));
                    }
                }
                _data.add(dataModel);
            }
        }
        catch (JSONException je){

        }
        return _data;
    }
}
