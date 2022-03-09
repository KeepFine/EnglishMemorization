package com.gxt.dao;

import com.gxt.pojo.Levelexbook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LevelexbookMapper {

    Levelexbook getWord(int wordId);

    int getWordCount();
}
