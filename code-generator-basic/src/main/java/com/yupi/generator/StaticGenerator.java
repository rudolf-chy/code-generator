package com.yupi.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class StaticGenerator {

    public  static void main(String[] args){
        String projectPath = System.getProperty("user.dir");
        // 输入路径
        String inputPath = projectPath + File.separator + "code-generator-demo-projects" + File.separator + "acm-template";
        // 输出路径
        String outputPath = projectPath;
        // 复制
        copyFilesByRecursive(inputPath, outputPath);
    }

    /**
     * 拷贝文件（Hutool实现，会将输入目录完整拷贝到输出目录下）
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public  static void copyFilesByHutool(String inputPath, String outputPath){
        FileUtil.copy(inputPath, outputPath, false);
    }

    public static void copyFilesByRecursive(String inputPath, String outputPath){
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try{
            copyFileByRecursive(inputFile, outputFile);
        }catch (Exception e){
            System.out.println("文件复制失败");
            e.printStackTrace();
        }
    }

    private static void copyFileByRecursive(File inputFile, File outputFile) throws IOException{
        if(inputFile.isDirectory()){
            System.out.println(inputFile.getName());
            File destOutputFile = new File(outputFile, inputFile.getName());
            if(!destOutputFile.exists()){
                destOutputFile.mkdirs();
            }
            File[] files = inputFile.listFiles();
            if(ArrayUtil.isEmpty(files)){
                return;
            }
            for(File file : files){
                copyFileByRecursive(file, destOutputFile);
            }
        }else{
            Path destPath = outputFile.toPath().resolve(inputFile.getPath());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
