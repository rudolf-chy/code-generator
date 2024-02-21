package com.yupi.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

@CommandLine.Command(name = "list", description = "查看文件列表", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable{

    @Override
    public void run(){
        String projectPath = System.getProperty("user.dir");
        String inputPath = new File(projectPath + File.separator + "code-generator-demo-projects" + File.separator + "acm-template").getAbsolutePath();
        List<File> files = FileUtil.loopFiles(inputPath);
        for(File file : files){
            System.out.println(file);
        }
    }
}
