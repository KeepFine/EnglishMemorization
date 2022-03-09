package com.gxt.service;

import com.gxt.pojo.Word;
import org.apache.ibatis.annotations.Param;

public interface WordService {
    Word getWord(String bookName, int wordId);

    int getWordCount(String bookName);

}
