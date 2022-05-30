package com.example.demo.dictonary.entity;

import lombok.Data;

import java.util.List;

@Data
public class DictionaryCollectionEntity {

    /**
     * 库名
     */
    private String database;

    /**
     * 表名
     */
    private String table;

    /**
     * 字段名
     */
    private String field;


    /**
     * 系统名
     */
    private String system;


    /**
     * 字典
     */
    private List<DictionaryDataEntity> children;
}
