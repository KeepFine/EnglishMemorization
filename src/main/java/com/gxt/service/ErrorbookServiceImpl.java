package com.gxt.service;

import com.gxt.dao.ErrorbookMapper;
import com.gxt.pojo.Errorbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ErrorbookServiceImpl implements ErrorbookService{

    @Autowired
    private ErrorbookMapper errorbookMapper;

    @Override
    public int addOneWord(Errorbook errorbook) {
        return errorbookMapper.addOneWord(errorbook);
    }

    @Override
    public int delOneWord(int id) {
        return errorbookMapper.delOneWord(id);
    }

    @Override
    public List<Errorbook> getErrorBook(String userCode) {
        return errorbookMapper.getErrorBook(userCode);
    }

    @Override
    public Errorbook getMaxNoWord(String userCode) {
        return errorbookMapper.getMaxNoWord(userCode);
    }

    @Override
    public Errorbook getWordByNo(String userCode, int wordId) {
        return errorbookMapper.getWordByNo(userCode, wordId);
    }

    @Override
    public Errorbook getWordByUsersNo(String userCode, int usersNo) {
        return errorbookMapper.getWordByUsersNo(userCode,usersNo);
    }
}
