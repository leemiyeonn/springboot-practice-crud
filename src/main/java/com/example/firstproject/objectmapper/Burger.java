package com.example.firstproject.objectmapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor // Burger burger = objectMapper.readValue(json, Burger.class);로 버거 객체를 만들때 default 생성자 필요
@AllArgsConstructor
@ToString
@Getter // String json = objectMapper.writeValueAsString(burger); buger 객체를 Json 으로 만드려면 @Getter 필요
public class Burger {
    private String name;
    private int price;
    private List<String> ingredients;
}

