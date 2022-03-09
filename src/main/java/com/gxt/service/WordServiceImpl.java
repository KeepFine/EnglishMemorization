package com.gxt.service;

import com.gxt.dao.WordMapper;
import com.gxt.pojo.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService{
    @Autowired
    private WordMapper wordMapper;
    @Override
    public Word getWord(String bookName, int wordId) {
        return wordMapper.getWord(bookName, wordId);
    }

    @Override
    public int getWordCount(String bookName) {
        return wordMapper.getWordCount(bookName);
    }
}
