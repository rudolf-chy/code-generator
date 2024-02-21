package com.yupi.maker.model;

import lombok.Data;

@Data
public class DataModel {

    public boolean needGit = true;

    public boolean loop = false;

    public MainTemplate mainTemplate;
}
