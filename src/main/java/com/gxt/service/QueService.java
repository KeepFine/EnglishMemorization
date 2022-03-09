package com.gxt.service;

import com.gxt.pojo.Que;
import org.apache.ibatis.annotations.Param;

public interface QueService {

    //注册
    int addUserQue(Que que);

    int updataAsk(@Param("userCode") String userCode, @Param("ask") String ask);

    String getUserAsk(String userCode);
}
