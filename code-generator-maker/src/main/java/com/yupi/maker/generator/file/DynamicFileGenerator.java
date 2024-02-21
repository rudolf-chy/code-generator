package com.yupi.maker.generator.file;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DynamicFileGenerator {

    public  static void doGenerator(String inputPath, String outputPath, Object model) throws IOException, TemplateException{
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        configuration.setDefaultEncoding("utf-8");

        configuration.setNumberFormat("0.#######");

        int lastSplitIndex = inputPath.lastIndexOf("/");
        Template template = configuration.getTemplate(inputPath.substring(lastSplitIndex + 1));

        if(!FileUtil.exist(outputPath)){
            FileUtil.touch(outputPath);
        }

        Writer out = new FileWriter(outputPath);

        template.process(model, out);

        out.close();
    }
}
