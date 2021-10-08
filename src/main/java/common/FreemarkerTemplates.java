package common;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.Writer;

/**
 * @author tanglijuan
 * @date 2021/9/24
 */
public enum FreemarkerTemplates {

    CURL(getTemplate("/curl.ftl")),

    MARKDOWN(getTemplate("/markdown.ftl"));


    Template template;


    static Template getTemplate(String name) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setAPIBuiltinEnabled(true);
        cfg.setNumberFormat("0.##");
        cfg.setDateFormat("yyyy-MM-dd");
        cfg.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        cfg.setTimeFormat("HH:mm:ss");
        cfg.setClassLoaderForTemplateLoading(FreemarkerTemplates.class.getClassLoader(), "/templates");
        try {
            return cfg.getTemplate(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }


    FreemarkerTemplates(Template template) {
        this.template = template;
    }

    public void process(Object data, Writer out) throws TemplateException, IOException {
        template.process(data, out);

    }
}
