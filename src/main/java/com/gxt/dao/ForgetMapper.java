package com.gxt.dao;

import com.gxt.pojo.Forget;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ForgetMapper {

    int addUserForget(Forget forget);

    List<Forget> getForgetList(String userCode);

    Forget getForgetById(int id);

    int updateForgetById(@Param("content") String content,@Param("id") int id);

    int delForgetById(int id);
}
