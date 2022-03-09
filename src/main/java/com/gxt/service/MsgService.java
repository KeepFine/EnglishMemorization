package com.gxt.service;

import com.gxt.pojo.Msg;
import org.apache.ibatis.annotations.Param;

public interface MsgService {


    //注册
    int register(Msg msg);

    String getUserCodeByName(String userCode);

    Msg getMsgByUserCode(String userCode);

    int updatePicture(@Param("id") int id, @Param("picture") String picture);

    String getUserPic(String userCode);

    int updatePassword(@Param("id") int id, @Param("userPassword") String userPassword);

    int updateName(@Param("id") int id,@Param("userName") String userName);

    int updateMail(@Param("id") int id,@Param("mail") String mail);

}
