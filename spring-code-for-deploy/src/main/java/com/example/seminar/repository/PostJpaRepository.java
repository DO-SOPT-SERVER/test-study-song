package com.example.seminar.repository;

import com.example.seminar.domain.Member;
import com.example.seminar.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByMemberId(Long memberId);
    List<Post> findAllByMember(Member member);

}
