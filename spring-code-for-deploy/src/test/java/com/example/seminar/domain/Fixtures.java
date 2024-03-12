package com.example.seminar.domain;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Member> createMembers(int age, String name, String nickname, Part part) {
        List<Member> members = new ArrayList<>();
        SOPT sopt = SOPT.builder()
                .part(part)
                .build();

        Member member = Member.builder()
                .age(age)
                .name(name)
                .sopt(sopt)
                .nickname(nickname)
                .build();

        Member member2 = Member.builder()
                .age(15)
                .name("김성은")
                .sopt(sopt)
                .nickname("성은짱짱걸")
                .build();

        Member member3 = Member.builder()
                .age(35)
                .name("윤정원")
                .sopt(sopt)
                .nickname("가든이")
                .build();

        Member member4 = Member.builder()
                .age(20)
                .name("최윤한")
                .sopt(sopt)
                .nickname("유나니")
                .build();

        members.add(member);
        members.add(member2);
        members.add(member3);
        members.add(member4);

        return members;
    }

    public static Post createPost(String title, String content, Member member){
        return Post.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }
}
