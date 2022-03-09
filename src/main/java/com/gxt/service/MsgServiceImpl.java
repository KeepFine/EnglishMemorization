package com.gxt.service;

import com.gxt.dao.MsgMapper;
import com.gxt.pojo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    private MsgMapper msgMapper;
    @Override
    public int register(Msg msg) {
        if(msgMapper.getUserCodeByName(msg.getUserCode())!=null){
            return 0;
        }
        else
            return msgMapper.addMsg(msg);
    }

    @Override
    public String getUserCodeByName(String userCode) {
        return msgMapper.getUserCodeByName(userCode);
    }

    @Override
    public Msg getMsgByUserCode(String userCode) {
        return msgMapper.getMsgByUserCode(userCode);
    }

    @Override
    public int updatePicture(int id, String picture) {
        return msgMapper.updatePicture(id, picture);
    }

    @Override
    public String getUserPic(String userCode) {
        return msgMapper.getUserPic(userCode);
    }

    @Override
    public int updatePassword(int id, String userPassword) {
        return msgMapper.updatePassword(id,userPassword);
    }

    @Override
    public int updateName(int id, String userName) {
        return msgMapper.updateName(id, userName);
    }

    @Override
    public int updateMail(int id, String mail) {
        return msgMapper.updateMail(id, mail);
    }


}
