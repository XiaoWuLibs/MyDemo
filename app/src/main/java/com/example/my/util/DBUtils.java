package com.example.my.util;

import com.example.my.db.BigData;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 * 数据库操作工具类
 */

public class DBUtils {
    /**
     * 更新数据库的热搜词的搜索次数值
     *
     * @param content 搜索内容
     */
    public static void updateHotSearchDB(String content) {
        List<BigData> bigDataList = DataSupport.where("content=?", content)
                .find(BigData.class);
        if (bigDataList != null) {
            int bigDataSize = bigDataList.size();
            if (bigDataSize == 0) {//没有此数据，添加入数据库
                BigData bigDataSave = new BigData();
                bigDataSave.setContent(content);
                bigDataSave.setClickNum(1);
                bigDataSave.save();
            } else {//有此数据，更新数据库
                BigData bigData = bigDataList.get(0);
                int primaryKey = bigData.getId();
                int curNum = bigData.getClickNum();
                BigData bigDataUpdate = new BigData();
                bigDataUpdate.setClickNum(curNum + 1);
                bigDataUpdate.update(primaryKey);
            }
        }
    }

    /**
     * 查询数据库中点击次数最多的内容
     *
     * @return List<BigData>
     */
    public static List<BigData> selectBigData() {
        return DataSupport.where("clickNum=?"
                , String.valueOf(DataSupport.max(BigData.class, "clickNum", int.class)))
                .find(BigData.class);
    }
}
