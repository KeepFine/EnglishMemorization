package com.gxt.service;

import com.gxt.dao.LearningMapper;
import com.gxt.pojo.Learning;
import com.gxt.pojo.Practice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LearningServiceImpl implements LearningService{

    @Autowired
    private LearningMapper learningMapper;

    @Override
    public int addUserLearn(Learning learning) {
        return learningMapper.addUserLearn(learning);
    }

    @Override
    public Integer getCompletion(String userCode) {
        return learningMapper.getCompletion(userCode);
    }

    @Override
    public String getLearningBookId(String userCode) {
        return learningMapper.getLearningBookId(userCode);
    }

    @Override
    public Integer getLearningNo(String userCode) {
        return learningMapper.getLearningNo(userCode);
    }

    @Override
    public Integer getErrorNo(String userCode) {
        return learningMapper.getErrorNo(userCode);
    }

    @Override
    public int updataLearningBookId(String userCode, String learningBookId) {
        return learningMapper.updataLearningBookId(userCode,learningBookId);
    }

    @Override
    public int updataCompletion(String userCode, int completion) {
        return learningMapper.updataCompletion(userCode, completion);
    }

    @Override
    public int updatalearningNo(String userCode, int learningNo) {
        return learningMapper.updatalearningNo(userCode, learningNo);
    }

    @Override
    public int updataErrorNo(String userCode, int errorNo) {
        return learningMapper.updataErrorNo(userCode, errorNo);
    }

    @Override
    public int updataUserLearn(Learning learning) {
        return learningMapper.updataUserLearn(learning);
    }

    @Override
    public Learning getUserLearn(String userCode) {
        return learningMapper.getUserLearn(userCode);
    }


}
