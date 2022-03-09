package com.gxt.service;

import com.gxt.dao.LevelexbookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelexbookServiceImpl implements LevelexbookService{

    @Autowired
    private LevelexbookMapper levelexbookMapper;

    @Override
    public int getWordCount() {
        return levelexbookMapper.getWordCount();
    }
}
