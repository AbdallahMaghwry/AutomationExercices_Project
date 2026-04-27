package com.automationexercices.utils.dataReader;

import com.automationexercices.utils.Logs.LogsManager;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonReader {
    private final String TEST_DATA_PATH ="src/test/resources/test-data/";
    String JsonReader;
    String jsonFileName;
    //الفانكشن دي شبه ال load properties
    public JsonReader (String jsonFileName){
        this.jsonFileName = jsonFileName;
        try {
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(TEST_DATA_PATH + jsonFileName + ".json"));
            JsonReader = data.toJSONString();// الداتا دي رجعها في شكل استرنج و حطو في الريدير
        }catch (Exception e){
            LogsManager.Error("Error loading json file: " , jsonFileName , e.getMessage());
            JsonReader = "{}";//initialize with empty JSON object to avoid null pointer exceptions
        }
    }
    /*
     * عشان اعمل جيسون ريدر لازم اقولك اسم الفايل الي هاتريد منو ايه
     * الجيسون نيم دا الي انت بعتو في الفانكشن دا لازم اعرفو هاعلمو initialization بالاسترينج الي انا عاملو عشان اعرف استخدمو جوا الفانكشن بتاعتي
     * علمت هنا كونستركتور اسم الفايل عشان ممكن ابقي مكريت فايلز داتا كتير و كل مره لما انده الكونستركتور دا هاديلو اسم الداتا الي انا عايزيها
     * الparse دا هانقطع الجيسون فايل و نطلع منو في الاخر بشويه key and value و هابعتلك الباث بتاعو
     *
     * */
    public String GetJsonData(String jsonPath){//جيسون باث دا الحاجه الي انت محتاج تستخدمها
        try {
            return JsonPath.read(JsonReader,jsonPath);//هنا انا بقولو هاتقرا من الريدير الي جالك دا الحاجه (الباث ) الي انا عايزها تجيب القيمه بتاعتها عشان نستخدمها
        }catch (Exception e){
            LogsManager.Error("Error getting json data for key: " , jsonPath , e.getMessage());
            return "";
        }
    }

}
