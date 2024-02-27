package com.example.seminar.service.member;

import com.example.seminar.domain.Member;
import com.example.seminar.domain.SOPT;
import com.example.seminar.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUpdater {
    private final MemberJpaRepository memberJpaRepository;

    public void updateSopt(Member member, SOPT sopt) {
        member.updateSOPT(sopt);
    }

}
