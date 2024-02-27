package com.example.seminar.service.member;


import com.example.seminar.domain.Member;
import com.example.seminar.domain.SOPT;
import com.example.seminar.dto.request.member.MemberCreateRequest;
import com.example.seminar.dto.request.member.MemberProfileUpdateRequest;
import com.example.seminar.dto.response.MemberGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRegister memberRegister;
    private final MemberRetriever memberRetriever;
    private final MemberUpdater memberUpdater;
    private final MemberRemover memberRemover;

    public MemberGetResponse getMemberById(final long id) {
        Member member = memberRetriever.findById(id);
        return MemberGetResponse.of(member);
    }

    public List<MemberGetResponse> getMembers() {
        return memberRetriever.findAll()
                .stream()
                .map(MemberGetResponse::of)
                .collect(Collectors.toList());
    }
    @Transactional
    public String create(final MemberCreateRequest request) {
         final Member member =  memberRegister.register(Member.builder()
                 .name(request.name())
                 .nickname(request.nickname())
                 .age(request.age())
                 .sopt(request.sopt())
                 .build());
         return member.getId().toString();
    }

    @Transactional
    public void updateSOPT(final long memberId, final MemberProfileUpdateRequest request) {
        final Member member = memberRetriever.findById(memberId);
        final SOPT sopt = SOPT.builder()
                .part(request.getPart())
                .build();
        memberUpdater.updateSopt(member, sopt);
    }

    @Transactional
    public void deleteMember(final long memberId) {
        Member member = memberRetriever.findById(memberId);
        memberRemover.remove(member);
    }
}
