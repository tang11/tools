package dto;

import lombok.Data;

import java.util.List;

/**
 * @author tanglijuan
 * @date 2021/10/8
 */
@Data
public class MarkdowDTO {
    private String name;
    private String desc;
    private String url;
    private String httpMethod;

    private List<ParamDTO> requestParamList;
}
