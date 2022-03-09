package com.gxt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Word {

    private Integer wordId;
    private String name;
    private String meaning;
}
