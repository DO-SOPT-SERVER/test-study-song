package com.example.seminar.service.post;


import com.example.seminar.domain.Category;
import com.example.seminar.domain.Member;
import com.example.seminar.domain.Post;
import com.example.seminar.dto.request.post.PostCreateRequest;
import com.example.seminar.dto.request.post.PostUpdateRequest;
import com.example.seminar.dto.response.post.PostGetResponse;
import com.example.seminar.repository.MemberJpaRepository;
import com.example.seminar.repository.PostJpaRepository;
import com.example.seminar.service.category.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostJpaRepository postJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final CategoryService categoryService;

    private final PostSaver postSaver;
    private final PostRetriever postRetriever;

    @Transactional
    public String create(PostCreateRequest request, final long memberId) {
        Member member = memberJpaRepository.findByIdOrThrow(memberId);
        Post post = postJpaRepository.save(
                Post.builder()
                        .member(member)
                        .title(request.title())
                        .content(request.content()).build());
        return post.getId().toString();
    }

    @Transactional
    public void editContent(final Long postId, final PostUpdateRequest request) {
        Post post = postJpaRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 게시글이 없습니다."));
        post.updateContent(request.content());
    }

    public List<PostGetResponse> getPosts(final long memberId) {
        return postJpaRepository.findAllByMemberId(memberId)
                .stream()
                .map(post -> PostGetResponse.of(post, getCategoryByPost(post)))
                .toList();
    }

    public PostGetResponse getById(Long postId) {
        Post post = postJpaRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("해당하는 게시글이 없습니다."));
        return PostGetResponse.of(post, getCategoryByPost(post));
    }

    @Transactional
    public void deleteById(Long postId) {
        Post post = postJpaRepository
                .findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 게시글이 없습니다."));
        postJpaRepository.delete(post);
    }

    private Category getCategoryByPost(Post post) {
        return categoryService.getByCategoryId(post.getCategoryId());
    }
}
