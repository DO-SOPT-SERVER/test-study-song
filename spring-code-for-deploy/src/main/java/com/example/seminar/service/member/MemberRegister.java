package com.example.seminar.service.member;


import com.example.seminar.domain.Member;
import com.example.seminar.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberRegister {
    private final MemberJpaRepository memberJpaRepository;

    public Member register(Member member) {
        return memberJpaRepository.save(member);
    }

}
