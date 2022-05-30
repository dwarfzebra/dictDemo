package com.example.demo.dictonary;

import com.example.demo.dictonary.entity.DictionaryCollectionEntity;
import com.example.demo.dictonary.entity.DictionaryDataEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class DictionaryDataUtils {


    /**
     * 自定义配置
     *
     * @param enumClass  枚举类
     * @param codeField  code字段
     * @param valueField value字段
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    static List<DictionaryDataEntity> getDictionaryDataEntityList(Class<? extends Enum> enumClass, String codeField, String valueField) throws NoSuchFieldException, IllegalAccessException {
        Assert.notNull(codeField, "code字段不能为空");
        Assert.notNull(valueField, "value字段不能为空");

        Field code = enumClass.getDeclaredField(codeField);
        Field value = enumClass.getDeclaredField(valueField);

        return transferToDictionaryDataEntity(enumClass.getEnumConstants(), code, value);
    }


    /**
     * 将枚举类转换成字典实体
     *
     * @param enumConstants
     * @param codeField
     * @param valueField
     * @return
     */
    static List<DictionaryDataEntity> transferToDictionaryDataEntity(Enum[] enumConstants, Field codeField, Field valueField) throws IllegalAccessException {
        codeField.setAccessible(true);
        valueField.setAccessible(true);

        Integer valueType = null;

        if (Number.class.isAssignableFrom(codeField.getType())) {
            valueType = 1;
        } else {
            valueType = 2;
        }

        List<DictionaryDataEntity> data = new ArrayList<>();
        for (int i = 0; i < enumConstants.length; i++) {
            Enum e = enumConstants[i];
            DictionaryDataEntity entity = new DictionaryDataEntity();
            entity.setDicKey(e.name());
            entity.setDicVal(String.valueOf(codeField.get(e)));
            entity.setDicDesc(String.valueOf(valueField.get(e)));
            entity.setDicSort(i);
            entity.setDicValType(valueType);
            entity.setDicTagKey(DictionaryEnum.DefaultConfig.SYSTEM);
            data.add(entity);
        }

        return data;
    }

    /**
     * 根据枚举获取枚举字典
     *
     * @param dictionaryEnum
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    static DictionaryCollectionEntity getDictionaryDataEntity(DictionaryEnum dictionaryEnum) throws NoSuchFieldException, IllegalAccessException {

        DictionaryCollectionEntity collectionEntity = new DictionaryCollectionEntity();
        collectionEntity.setDatabase(DictionaryEnum.DefaultConfig.DATABASE_NAME);
        collectionEntity.setTable(dictionaryEnum.getTable());
        collectionEntity.setField(dictionaryEnum.getField());
        collectionEntity.setSystem(DictionaryEnum.DefaultConfig.SYSTEM);
        collectionEntity.setChildren(getDictionaryDataEntityList(dictionaryEnum.getEnumClass(), dictionaryEnum.getCodeField(), dictionaryEnum.getValueField()));

        return collectionEntity;
    }

    /**
     * 获取所有枚举字典
     *
     * @return
     */
    public static List<DictionaryCollectionEntity> getAllDictionary() throws NoSuchFieldException, IllegalAccessException {
        List<DictionaryCollectionEntity> result = new ArrayList<>();
        for (DictionaryEnum dictionaryEnum : DictionaryEnum.values()) {
            result.add(getDictionaryDataEntity(dictionaryEnum));
        }
        return result;
    }

    /**
     * 获取指定的枚举字典
     *
     * @return
     * @throws NoSuchFieldException   枚举包含字段错误
     * @throws IllegalAccessException 非法访问
     */
    public static List<DictionaryCollectionEntity> getDictionaryByEnumClassNames(String enumClassNames) throws NoSuchFieldException, IllegalAccessException {
        List<DictionaryCollectionEntity> result = new ArrayList<>();
        Assert.notNull(enumClassNames, "枚举类名不能为空");
        String[] enumClassNameArr = enumClassNames.split(",");

        List<DictionaryEnum> dictionaryEnums = new ArrayList<>();
        for (String enumClassName : enumClassNameArr) {
            DictionaryEnum dictionaryEnum = DictionaryEnum.ofEnumClassName(enumClassName);
            if (dictionaryEnum == null) {
                log.error("枚举名不存在字典配置DictionaryEnum中,{}", enumClassName);
            } else {
                dictionaryEnums.add(dictionaryEnum);
            }

        }
        for (DictionaryEnum dictionaryEnum : dictionaryEnums) {
            result.add(getDictionaryDataEntity(dictionaryEnum));
        }
        return result;
    }
}
