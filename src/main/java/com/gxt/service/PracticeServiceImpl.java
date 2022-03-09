package com.gxt.service;

import com.gxt.dao.PracticeMapper;
import com.gxt.pojo.Practice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PracticeServiceImpl implements PracticeService{

    @Autowired
    private PracticeMapper practiceMapper;


    @Override
    public int addUserPractice(Practice practice) {
        return practiceMapper.addUserPractice(practice);
    }

    @Override
    public Integer getCorrect(String userCode) {
        return practiceMapper.getCorrect(userCode);
    }

    @Override
    public int updataLearningBookId(String userCode, String learningBookId) {
        return practiceMapper.updataLearningBookId(userCode, learningBookId);
    }

    @Override
    public int updataCompletion(String userCode, Integer completion) {
        return practiceMapper.updataCompletion(userCode, completion);
    }

    @Override
    public int updataLearningNo(String userCode, Integer learningNo) {
        return practiceMapper.updataLearningNo(userCode, learningNo);
    }

    @Override
    public int updataErrorNo(String userCode, Integer errorNo) {
        return practiceMapper.updataErrorNo(userCode, errorNo);
    }

    @Override
    public int updataCorrect(String userCode, Integer correct) {
        return practiceMapper.updataCorrect(userCode, correct);
    }

    @Override
    public String getLearningBookId(String userCode) {
        return practiceMapper.getLearningBookId(userCode);
    }

    @Override
    public Integer getCompletion(String userCode) {
        return practiceMapper.getCompletion(userCode);
    }

    @Override
    public Integer getLearningNo(String userCode) {
        return practiceMapper.getLearningNo(userCode);
    }

    @Override
    public Integer getErrorNo(String userCode) {
        return practiceMapper.getErrorNo(userCode);
    }

    @Override
    public Practice getUserPractice(String userCode) {
        return practiceMapper.getUserPractice(userCode);
    }

    @Override
    public Integer getAllTotal(String userCode) {
        return practiceMapper.getAllTotal(userCode);
    }

    @Override
    public int updataAllTotal(String userCode, Integer allTotal) {
        return practiceMapper.updataAllTotal(userCode,allTotal);
    }
}
