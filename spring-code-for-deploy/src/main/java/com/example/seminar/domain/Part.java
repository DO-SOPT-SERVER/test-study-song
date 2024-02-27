package com.example.seminar.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum Part {

    SERVER("서버"),
    WEB("웹"),
    ANDROID("안드로이드"),
    IOS("iOS"),
    PLAN("기획"),
    DESIGN("디자인");

    private final String name;

    public static List<Part> developParts() {
        return List.of(SERVER, WEB, ANDROID, IOS);
    }
}
