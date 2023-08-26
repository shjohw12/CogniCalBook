package com.aihackathon.service;

import com.aihackathon.dto.ClovaImageEntity;
import com.aihackathon.dto.ClovaRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TextImageConvertService {

    // Clova OCR API 사용
    public String clova(String filename, String data) throws Exception {
        String[] parts = filename.split("\\.");
        String name = parts[0];
        String format = parts[1];
        ClovaRequest requestEntity = toRequestEntity(name, data, format);

        RestTemplate rt = new RestTemplate();
//        rt.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
//;
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OCR-SECRET","c0JFa1FDV0pNb2xnTFZNdnVIenZOS3ZLTGRzWk1MV1U=");

        HttpEntity<String> entity = new HttpEntity<>(requestEntity.toString(), headers);

        ResponseEntity<String> response = rt.exchange(
            "https://4wvcldst17.apigw.ntruss.com/custom/v1/24610/a260835b8e9c0c660cfba053eb7e60113216e9dad54963db51d2e7cbf53355c6/general",
            HttpMethod.POST, //{요청할 방식}
            entity, // {요청할 때 보낼 데이터}
            String.class
        );

        var jparse = jsonparse(response.getBody());

        String result = "";
        for (String str : jparse) {
            result += str + " ";
        }

        return result;
    }

    private static List<String> jsonparse(String response) throws Exception {

        var jp = new org.json.simple.parser.JSONParser();
        JSONObject jobj = (JSONObject) jp.parse(response);
        //images 배열 obj 화
        JSONArray JSONArrayPerson = (JSONArray)jobj.get("images");
        JSONObject JSONObjImage = (JSONObject)JSONArrayPerson.get(0);
        JSONArray s = (JSONArray) JSONObjImage.get("fields");
        //
        List<Map<String, Object>> m = JsonUtill.getListMapFromJsonArray(s);
        List<String> result = new ArrayList<>();
        for (Map<String, Object> as : m) {
            result.add((String) as.get("inferText"));
        }
        return result;
    }
    public class JsonUtill {
        /**
         * JSONObject => Map<String, String>
         * @param {JSONObject} jsonObject
         * @returns {Map} map
         */
        @SuppressWarnings("unchecked")
        public static Map<String, Object> getMapFromJsonObject(JSONObject jsonObject) {
            Map<String, Object> map = null;
            try {
                map = new ObjectMapper().readValue(jsonObject.toJSONString(), Map.class);

            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return map;
        }
        /**
         * JSONArray => List<Map<String, String>>
         * @param {jsonArray} jsonArray
         * @returns {List} list
         */
        public static List<Map<String, Object>> getListMapFromJsonArray(
            JSONArray jsonArray) {

            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

            if (jsonArray != null) {

                int jsonSize = jsonArray.size();

                for (int i = 0; i < jsonSize; i++) {

                    Map<String, Object> map = getMapFromJsonObject((JSONObject)jsonArray.get(i));
                    list.add(map);

                }
            }
            return list;
        }
    }

    public ClovaRequest toRequestEntity(String imgName, String data, String format) {
        return new ClovaRequest(
            "V2",
            getUUID(imgName).toString(),
            System.currentTimeMillis(),
            getImgList(imgName, format, data)
        );
    }

    public UUID getUUID(String imgName) {
        return UUID.nameUUIDFromBytes(imgName.getBytes());
    }

    public ArrayList<ClovaImageEntity> getImgList(String imgName, String format, String data) {
        ArrayList<ClovaImageEntity> images = new ArrayList<ClovaImageEntity>();
        images.add(new ClovaImageEntity(format, data, imgName));
        return images;
    }
}
