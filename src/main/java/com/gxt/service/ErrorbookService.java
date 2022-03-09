package com.gxt.service;

import com.gxt.pojo.Errorbook;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ErrorbookService {
    int addOneWord(Errorbook errorbook);

    int delOneWord(int id);

    List<Errorbook> getErrorBook(String userCode);

    Errorbook getMaxNoWord(String userCode);

    Errorbook getWordByNo(@Param("userCode") String userCode, @Param("wordId")  int wordId);

    Errorbook getWordByUsersNo(@Param("userCode") String userCode,@Param("usersNo")int usersNo);


}
