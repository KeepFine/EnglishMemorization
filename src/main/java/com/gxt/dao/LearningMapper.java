package com.gxt.dao;

import com.gxt.pojo.Learning;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LearningMapper {

    int addUserLearn(Learning learning);

    String getLearningBookId(String userCode);

    Integer getCompletion(String userCode);

    Integer getLearningNo(String userCode);

    Integer getErrorNo(String userCode);

    int updataLearningBookId(@Param("userCode")String userCode, @Param("learningBookId")String learningBookId);

    int updataCompletion(@Param("userCode")String userCode, @Param("completion")int completion);

    int updatalearningNo(@Param("userCode")String userCode, @Param("learningNo")int learningNo);

    int updataErrorNo(@Param("userCode")String userCode, @Param("errorNo")int errorNo);

    int updataUserLearn(Learning learning);

    Learning getUserLearn(String userCode);

}
