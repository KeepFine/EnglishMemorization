package com.gxt.dao;

import com.gxt.pojo.Practice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PracticeMapper {

    int addUserPractice(Practice practice);

    int updataLearningBookId(@Param("userCode") String userCode,@Param("learningBookId") String learningBookId);

    int updataCompletion(@Param("userCode") String userCode,@Param("completion") Integer completion);

    int updataLearningNo(@Param("userCode") String userCode,@Param("learningNo") Integer learningNo);

    int updataErrorNo(@Param("userCode") String userCode,@Param("errorNo") Integer errorNo);

    int updataCorrect(@Param("userCode") String userCode,@Param("correct") Integer correct);

    String getLearningBookId(String userCode);

    Integer getCompletion(String userCode);

    Integer getLearningNo(String userCode);

    Integer getErrorNo(String userCode);

    Integer getCorrect(String userCode);

    Practice getUserPractice(String userCode);

    Integer getAllTotal(String userCode);

    int updataAllTotal(@Param("userCode")String userCode,@Param("allTotal") Integer allTotal);

}
