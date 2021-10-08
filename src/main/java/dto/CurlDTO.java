package dto;

import lombok.Data;

import java.util.LinkedHashMap;

/**
 * @author tanglijuan
 * @date 2021/9/24
 */

@Data
public class CurlDTO {
    private String httpMethod;
    private String url;
    private LinkedHashMap<String, Object> headers;
    private String bodyJson;

}
