package com.gxt.dao;

import com.gxt.pojo.Mail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface MailMapper {

    int addMail(Mail mail);

    int delMail(int id);

    List<Mail> getMailByUser(String userCode);

    Mail getMailById(int id);
}
