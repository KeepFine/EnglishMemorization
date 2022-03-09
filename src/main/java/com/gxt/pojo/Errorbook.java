package com.gxt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Errorbook {

    private Integer id;
    private String userCode;
    private Integer wordId;
    private String wordName;
    private String meaning;
    private Integer usersNo;
}
