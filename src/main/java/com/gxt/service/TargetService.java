package com.gxt.service;

import com.gxt.pojo.Target;
import org.apache.ibatis.annotations.Param;

public interface TargetService {

    int addUserTarget(Target target);

    int updataTarget(@Param("userCode") String userCode, @Param("target") String target);

    Target getUserTarget(String userCode);
}
