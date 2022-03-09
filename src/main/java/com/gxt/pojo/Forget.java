package com.gxt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forget {

    private Integer id;
    private String userCode;
    private Date creatTime;
    private String content;
}
