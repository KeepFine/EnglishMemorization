package com.gxt.service;

import com.gxt.dao.TargetMapper;
import com.gxt.pojo.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TargetServiceImpl implements TargetService{

    @Autowired
    private TargetMapper targetMapper;

    @Override
    public int addUserTarget(Target target) {
        return targetMapper.addUserTarget(target);
    }

    @Override
    public int updataTarget(String userCode, String target) {
        return targetMapper.updataTarget(userCode,target);
    }

    @Override
    public Target getUserTarget(String userCode) {
        return targetMapper.getUserTarget(userCode);
    }
}
