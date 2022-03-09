package com.gxt.dao;

import com.gxt.pojo.Friend;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface FriendMapper {

    int addUserFriend(Friend friend);

    int delUserFriend(int id);

    List<Friend> getFriendsList(String userCode);

    Friend getFriend(int id);
}
