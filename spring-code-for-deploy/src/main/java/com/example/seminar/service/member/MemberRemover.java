package com.example.seminar.service.member;


import com.example.seminar.domain.Member;
import com.example.seminar.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberRemover {
    private final MemberRepository memberRepository;

    public void remove(Member member) {
        memberRepository.remove(member);
    }
}
