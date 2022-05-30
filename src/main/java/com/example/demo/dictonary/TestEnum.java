package com.example.demo.dictonary;

public enum TestEnum {

    B(0,"11235"),
    C(1,"213423")
    ;

    private Integer code;


    private String value;


    TestEnum(Integer code, String name){
        this.code = code;
        this.value = name;
    }
}
