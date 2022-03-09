package com.gxt.service;

import com.gxt.pojo.Forget;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForgetService {
    int addUserForget(Forget forget);

    List<Forget> getForgetList(String userCode);

    Forget getForgetById(int id);

    int updateForgetById(@Param("content") String content, @Param("id") int id);

    int delForgetById(int id);

}
