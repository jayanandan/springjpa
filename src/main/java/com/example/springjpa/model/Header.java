package com.example.springjpa.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Header {

    private String name;
    private List<String> values;

}
