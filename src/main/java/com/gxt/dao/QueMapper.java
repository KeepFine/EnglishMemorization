package com.gxt.dao;

import com.gxt.pojo.Que;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QueMapper {

    int addQue(Que que);

    int updataAsk(@Param("userCode") String userCode,@Param("ask") String ask);

    String getUserAsk(String userCode);
}
