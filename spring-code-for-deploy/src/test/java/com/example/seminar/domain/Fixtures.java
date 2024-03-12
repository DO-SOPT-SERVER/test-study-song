package com.example.seminar.domain;

public class Fixtures {

    public static Member createMember(int age, String name, String nickname, Part part) {
        SOPT sopt = SOPT.builder()
                .part(part)
                .build();

        return Member.builder()
                .age(age)
                .name(name)
                .sopt(sopt)
                .nickname(nickname)
                .build();
    }

    public static Post createPost(String title, String content, Member member){
        return Post.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }
}
