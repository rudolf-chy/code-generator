package com.yupi.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.yupi.generator.MainGenerator;
import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine;

import java.io.IOException;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "generate", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable {

    @CommandLine.Option(names = {"-l", "--loop"}, description = "是否循环", arity = "0..1", interactive = true)
    private boolean loop;

    @CommandLine.Option(names = {"-a", "--author"}, description = "作者名称", arity = "0..1", interactive = true)
    private String author = "yupi";

    @CommandLine.Option(names = {"-o", "--outputText"}, description = "输出文本", arity = "0..1", interactive = true)
    private String outputText = "sum = ";

    @Override
    public Integer call() throws Exception{
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }
}
