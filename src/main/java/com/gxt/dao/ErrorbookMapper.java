package com.gxt.dao;


import com.gxt.pojo.Errorbook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ErrorbookMapper {
//ErrorbookMapper
    int addOneWord(Errorbook errorbook);

    int delOneWord(int id);

    List<Errorbook> getErrorBook(String userCode);

    Errorbook getMaxNoWord(String userCode);

    Errorbook getWordByNo(@Param("userCode") String userCode,@Param("wordId")  int wordId);

    Errorbook getWordByUsersNo(@Param("userCode") String userCode,@Param("usersNo")int usersNo);
}
