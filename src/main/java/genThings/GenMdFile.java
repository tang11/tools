package genThings;

import common.FreemarkerTemplates;
import dto.MarkdowDTO;
import dto.ParamDTO;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanglijuan
 * @date 2021/9/22
 */
public class GenMdFile {
    public static String GenMdFile() {
        try {

            MarkdowDTO markdowDTO = new MarkdowDTO();
            markdowDTO.setName("测试生成markdown文档");
            markdowDTO.setUrl("localhost:8080/test");
            markdowDTO.setDesc("生成md文件");
            markdowDTO.setHttpMethod("GET");
            ParamDTO paramDTO = new ParamDTO();
            paramDTO.setType("Long");
            paramDTO.setName("id");
            ParamDTO paramDTO1 = new ParamDTO();
            paramDTO1.setName("address");
            paramDTO1.setType("String");
            List<ParamDTO> requestParamList = new ArrayList<>();
            requestParamList.add(paramDTO);
            requestParamList.add(paramDTO1);
            StringWriter writer = new StringWriter();
            FreemarkerTemplates.MARKDOWN.process(markdowDTO, writer);
            String previewDoc = writer.toString();
            return previewDoc;
        } catch (
                Exception e) {
            throw new RuntimeException("genPreviewDoc failed", e);
        }
    }
}
