package com.yupi.maker.generator;

import java.io.*;

public class JarGenerator {

    public static void doGenerate(String projectDir) throws IOException, InterruptedException {
        String macMavenCommand = "mvn clean package -DskipTests=true";
        String otherMavenCommand = "mvn.cmd clean package -DskipTests=true";
        String mavenCommand = macMavenCommand;

        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectDir));

        Process process = processBuilder.start();

        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("命令执行结束，退出码： " + exitCode);
    }

    public static void main(String[] args) throws IOException, InterruptedException{
        doGenerate("/Users/chenhaoyu/IdeaProjects/code-generator/code-generator/code-generator-basic");
    }
}