package com.example.demo.dictonary;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum DictionaryEnum {



    AAA(TestEnum.class,"crm_clue","create_method"),
    BBB(TestEnum.class,"c2_biz_info", "biz_type"),
    CCC(TestEnum.class,"crm_clue","clue_method","code","value"),
    DDD(TestEnum.class, "c2_biz_info","source","code","value")
    ;





    /**
     * 枚举类
     */
    private Class<? extends Enum> enumClass;

    /**
     * 表名
     */
    private String table;

    /**
     * 字段名
     */
    private String field;

    /**
     * 枚举编码字段
     */
    private String codeField;

    /**
     * 枚举值字段
     */
    private String valueField;

    /**
     * 使用全局配置
     * @param enumClass
     * @param table
     * @param field
     */
    DictionaryEnum(Class<? extends Enum> enumClass,String table,String field){
        this.enumClass = enumClass;
        this.table = table;
        this.field = field;
        this.codeField = DefaultConfig.DEFAULT_CODE_FIELD;
        this.valueField = DefaultConfig.DEFAULT_VALUE_FIELD;
    }

    /**
     * 使用自定义配置
     * @param enumClass
     * @param table
     * @param field
     * @param codeField
     * @param valueField
     */
    DictionaryEnum(Class<? extends Enum> enumClass,String table,String field,String codeField,String valueField){
        this.enumClass = enumClass;
        this.table = table;
        this.field = field;
        this.codeField = codeField;
        this.valueField = valueField;
    }


    /**
     * 枚举类名
     * @param enumClassName
     * @return
     */
    public static DictionaryEnum ofEnumClassName(String enumClassName){
           for(DictionaryEnum dictionaryEnum:values()){
               if(dictionaryEnum.enumClass.getName().equals(enumClassName));
               return dictionaryEnum;
           }
           return null;
    }


    public  interface DefaultConfig{
        /**
         * 数据库名
         */
        String DATABASE_NAME = "ucarcrm";


        /**
         * 枚举编码字段
         */
        String DEFAULT_CODE_FIELD = "code";

        /**
         * 枚举值字段
         */
        String DEFAULT_VALUE_FIELD = "value";

        /**
         * 业务系统
         */
        String SYSTEM = "CRM";
    }
}
