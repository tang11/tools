package genThings;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import common.FreemarkerTemplates;
import dto.CurlDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.StringWriter;
import java.util.LinkedHashMap;

/**
 * @author tanglijuan
 * @date 2021/9/24
 */
@Slf4j
public class GenCurl {

    public static String getCurlInfo() {
        CurlDTO curlDTO = new CurlDTO();
        curlDTO.setHttpMethod("POST");
        StringBuilder urlBuilder = new StringBuilder("localhost:8080")
                .append("/test")
                .append("?");

        String url = urlBuilder.toString();
        curlDTO.setUrl(url);
        JSONObject jsonObject = new JSONObject(new LinkedHashMap());
        jsonObject.put("name", "test");
        jsonObject.put("address", "street 1088");
        LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
        headers.put("Content-Type", "application/json");
        curlDTO.setBodyJson(JSON.toJSONString(jsonObject, SerializerFeature.PrettyFormat));
        StringWriter writer = new StringWriter();
        try {
            FreemarkerTemplates.CURL.process(curlDTO, writer);
            String curlInfo = writer.toString();
            return curlInfo;
        } catch (Exception e) {
            throw new RuntimeException("genCurlInfo failed", e);
        }

    }

}