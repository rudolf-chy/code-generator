package com.yupi.cli.example;

import cn.hutool.core.text.csv.CsvUtil;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(subcommands = {ASCIIArt.class})
public class Login implements Callable<Integer> {

    @CommandLine.Option(names = {"-u", "--user"}, description = "User name")
    String user;

    @CommandLine.Option(names = {"-p", "--password"}, description = "Passphrase", arity = "0..1", interactive = true, echo = false)
    String password;

    @CommandLine.Option(names = {"-cp", "--checkPassword"}, description = "Check Password", arity = "0..1", interactive = true)
    String checkPassword;

    public Integer call() throws Exception{
        System.out.println("password = " + password);
        System.out.println("check password = " + checkPassword);
        return 0;
    }

    public static void main(String[] args){
        new CommandLine(new Login()).execute("-u", "user123", "-p", "xxxx", "-cp", "xxxx");
    }
}
