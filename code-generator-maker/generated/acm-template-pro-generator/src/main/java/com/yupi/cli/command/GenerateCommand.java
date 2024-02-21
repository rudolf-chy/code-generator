package com.yupi.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.yupi.generator.MainGenerator;
import com.yupi.model.DataModel;
import lombok.Data;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(name = "generate", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"--needGit"}, arity = "0..1", description = "是否生成.gitignore", interactive = true, echo = true)
    private boolean needGit = true;

    @CommandLine.Option(names = {"-l", "--loop"}, arity = "0..1", description = "是否生成循环", interactive = true, echo = true)
    private boolean loop = false;

        /**
          * 核心模板
          */
        static DataModel.MainTemplate mainTemplate = new DataModel.MainTemplate();

        @CommandLine.Command(name = "核心模板", description = "用于生成核心模板文件")
        @Data
        public static class MainTemplateCommand implements Runnable{
         @CommandLine.Option(names = {"-a", "--author"}, arity = "0..1", description = "作者注释", interactive = true, echo = true)
         private String author = "yupi";
         @CommandLine.Option(names = {"-o", "--outputText"}, arity = "0..1", description = "输出信息", interactive = true, echo = true)
         private String outputText = "sum = ";

            @Override
            public void run(){
                    mainTemplate.author = author;
                    mainTemplate.outputText = outputText;
            }
        }


    @Override
    public Integer call() throws Exception{
        if(loop){
           System.out.println("输入核心模板配置: ");
           CommandLine commandLine = new CommandLine(MainTemplateCommand.class);
           commandLine.execute("--author", "--outputText");
        }

        DataModel dataModel = new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        dataModel.mainTemplate = mainTemplate;
        MainGenerator.doGenerate(dataModel);
        return 0;
    }
}
