package com.gxt.service;

import com.gxt.dao.ForgetMapper;
import com.gxt.pojo.Forget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForgetServiceImpl implements ForgetService{

    @Autowired
    private ForgetMapper forgetMapper;


    @Override
    public int addUserForget(Forget forget) {
        return forgetMapper.addUserForget(forget);
    }

    @Override
    public List<Forget> getForgetList(String userCode) {
        return forgetMapper.getForgetList(userCode);
    }

    @Override
    public Forget getForgetById(int id) {
        return forgetMapper.getForgetById(id);
    }

    @Override
    public int updateForgetById(String content, int id) {
        return forgetMapper.updateForgetById(content, id);
    }

    @Override
    public int delForgetById(int id) {
        return forgetMapper.delForgetById(id);
    }
}
