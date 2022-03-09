package com.gxt.dao;

import com.gxt.pojo.Levelexbook;
import com.gxt.pojo.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WordMapper {

    Word getWord(@Param("bookName") String bookName,@Param("wordId") int wordId);

    int getWordCount(String bookName);

}
