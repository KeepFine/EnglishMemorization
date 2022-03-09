package com.gxt.dao;

import com.gxt.pojo.Msg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
@Mapper
@Repository
public interface MsgMapper {

    //注册一个用户信息，即添加
    int addMsg(Msg msg);

    int updatePassword(@Param("id") int id, @Param("userPassword") String userPassword);

    int updateName(@Param("id") int id,@Param("userName") String userName);

    int updatePicture(@Param("id") int id,@Param("picture") String picture);

    int updateMail(@Param("id") int id,@Param("mail") String mail);

    Msg getMsgById(int id);

    Msg getMsgByUserCode(String userCode);

    Date getCreatTime(int id);

    String getPassword(int id);

    String getUserNameById(int id);

    String getUserCodeByName(String userCode);

    String getUserPic(String userCode);

}
