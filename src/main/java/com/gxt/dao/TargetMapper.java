package com.gxt.dao;

import com.gxt.pojo.Target;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TargetMapper {

    int addUserTarget(Target target);

    int updataPicture(@Param("userCode") String userCode,@Param("picture") String picture);

    int updataTarget(@Param("userCode") String userCode,@Param("target") String target);

    String getTargetPicture(String userCode);

    String getTargetword(String userCode);

    Target getUserTarget(String userCode);

}
