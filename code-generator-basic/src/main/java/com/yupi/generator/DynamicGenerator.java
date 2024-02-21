package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir") + File.separator + "code-generator-basic";
        String inputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate.java";

        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("chy");
        mainTemplateConfig.setOutputText("输出结果：");
        mainTemplateConfig.setLoop(true);

        doGenerator(inputPath, outputPath, mainTemplateConfig);
    }

    public  static void doGenerator(String inputPath, String outputPath, Object model) throws IOException, TemplateException{
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        configuration.setDefaultEncoding("utf-8");

        configuration.setNumberFormat("0.#######");

        Template template = configuration.getTemplate("MainTemplate.java.ftl");

        Writer out = new FileWriter(outputPath);

        template.process(model, out);

        out.close();
    }
}
