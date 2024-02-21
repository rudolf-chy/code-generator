package com.yupi.maker.generator.file;

import com.yupi.maker.generator.file.DynamicFileGenerator;
import com.yupi.maker.generator.file.StaticFileGenerator;
import com.yupi.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class FileGenerator {

    public static void doGenerate(Object model) throws TemplateException, IOException{
        String projectPath = System.getProperty("user.dir");

        String inputPath = projectPath + File.separator + "code-generator-demo-projects" + File.separator + "acm-template";
        String outputPath = projectPath;

        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);

        String dynamicInputPath = projectPath + File.separator + "code-generator-maker" + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicOutputPath = projectPath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";

        DynamicFileGenerator.doGenerator(dynamicInputPath, dynamicOutputPath, model);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        DataModel dataModel = new DataModel();
        doGenerate(dataModel);
    }
}
