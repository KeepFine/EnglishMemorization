package com.gxt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msg {
    private Integer id;
    private String userCode;
    private String userPassword;
    private String userName;
    private String mail;
    private String picture;
    private Date creationDate;
}
