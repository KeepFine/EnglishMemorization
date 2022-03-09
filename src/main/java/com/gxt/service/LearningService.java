package com.gxt.service;

import com.gxt.pojo.Learning;
import com.gxt.pojo.Practice;
import org.apache.ibatis.annotations.Param;

public interface LearningService {

    //注册
    int addUserLearn(Learning learning);

    //获得已学习单词数
    Integer getCompletion(String userCode);

    //获得用户的单词本
    String getLearningBookId(String userCode);

    //获得背记单词的id
    Integer getLearningNo(String userCode);

    Integer getErrorNo(String userCode);

    int updataLearningBookId(@Param("userCode")String userCode, @Param("learningBookId")String learningBookId);

    int updataCompletion(@Param("userCode")String userCode, @Param("completion")int completion);

    int updatalearningNo(@Param("userCode")String userCode, @Param("learningNo")int learningNo);

    int updataErrorNo(@Param("userCode")String userCode, @Param("errorNo")int errorNo);

    int updataUserLearn(Learning learning);

    Learning getUserLearn(String userCode);
}
