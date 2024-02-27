package com.example.seminar.service.member;


import com.example.seminar.domain.Member;
import com.example.seminar.repository.MemberJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberRetriever {

    private final MemberJpaRepository memberJpaRepository;

    public MemberRetriever(MemberJpaRepository memberJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
    }

    public Member findById(final long id) {
        return memberJpaRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 회원이 없습니다."));
    }

    public List<Member> findAll() {
        return memberJpaRepository.findAll();
    }

}
