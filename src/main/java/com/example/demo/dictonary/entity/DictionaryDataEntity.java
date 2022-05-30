package com.example.demo.dictonary.entity;

import lombok.Data;

/**
 * dictionary_data
 * 
 * @author zhaokaige
 * 2021-12-02
 */
@Data
public class DictionaryDataEntity {

    /**
     * 字典value类型
     */
    private Integer dicValType;

    /**
     * 字典key（方便枚举）
     */
    private String dicKey;

    /**
     * 字典value
     */
    private String dicVal;

    /**
     * 字典value描述
     */
    private String dicDesc;


    /**
     * 标签：区分不同系统或者业务类型
     */
    private String dicTagKey;

    /**
     * 排序
     */
    private Integer dicSort;

}