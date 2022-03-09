package com.gxt.service;

import com.gxt.dao.QueMapper;
import com.gxt.pojo.Que;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueServiceImpl implements QueService{

    @Autowired
    private QueMapper queMapper;

    @Override
    public int addUserQue(Que que) {
        return queMapper.addQue(que);
    }

    @Override
    public int updataAsk(String userCode, String ask) {
        return queMapper.updataAsk(userCode,ask);
    }

    @Override
    public String getUserAsk(String userCode) {
        return queMapper.getUserAsk(userCode);
    }
}
