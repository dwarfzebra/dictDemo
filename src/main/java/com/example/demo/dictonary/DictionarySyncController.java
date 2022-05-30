package com.example.demo.dictonary;


import com.example.demo.dictonary.entity.DictionaryCollectionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RequestMapping("/dictionary")
@RestController
@Slf4j
public class DictionarySyncController {


    /**
     * 枚举同步到字典库
     * @param enumClassNames  枚举类名  多个用,分隔  例如  AAAEnum   or    AAAEnum,BBBEnum
     * @return
     */
    @PostMapping("/sync")
    public boolean sync(@RequestParam String enumClassNames) throws NoSuchFieldException, IllegalAccessException {

        DictionaryDataUtils.getDictionaryByEnumClassNames(enumClassNames);

        //todo 调用字典库接口

        return true;
    }


    /**
     * 枚举同步到字典库
     * @return
     */
    @PostMapping("/sync-all")
    public boolean syncAll() throws NoSuchFieldException, IllegalAccessException {

        DictionaryDataUtils.getAllDictionary();

        //todo 调用字典库接口

        return true;
    }



    /**
     * 展示枚举转换后的字典
     * @param enumClassNames 枚举类名  多个用,分隔
     * @return
     */
    @GetMapping("/show")
    public List<DictionaryCollectionEntity> show(@RequestParam String enumClassNames) throws NoSuchFieldException, IllegalAccessException {

        List<DictionaryCollectionEntity> result =   DictionaryDataUtils.getDictionaryByEnumClassNames(enumClassNames);
        return result;
    }

    /**
     * 展示枚举转换后的字典-全部
     * @return
     */
    @GetMapping("/show-all")
    public List<DictionaryCollectionEntity> showAll() throws NoSuchFieldException, IllegalAccessException {
        List<DictionaryCollectionEntity> result =   DictionaryDataUtils.getAllDictionary();
        return result;
    }




    /**
     * 获取字典
     *
     * @return 字典列表
     */
//    @PostConstruct
    private void AutoPush() {
        new Thread(() -> {
            try {
                List<DictionaryCollectionEntity> data = DictionaryDataUtils.getAllDictionary();
                //获取到需要入字典库的枚举
                log.info("枚举转化字典数据:{}", data);
                //todo 项目启动时调用字典库接口  同步枚举字典到字典库
                log.info("同步字典结果{}");
            } catch (Exception e) {
                log.error("!!!!!!字典推送失败!!!!!!!", e);
            }
        }).start();

    }
}
