package com.example.seminar.service.post;


import com.example.seminar.domain.Member;
import com.example.seminar.domain.Post;
import com.example.seminar.dto.request.post.PostCreateRequest;
import com.example.seminar.dto.request.post.PostUpdateRequest;
import com.example.seminar.service.member.MemberRetriever;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final MemberRetriever memberRetriever;
    private final PostSaver postSaver;
    private final PostEditor postEditor;
    private final PostRetriever postRetriever;
    private final PostRemover postRemover;

    @Transactional
    public String create(PostCreateRequest request, final long memberId) {
        Member member = memberRetriever.findById(memberId);
        Post post = Post.builder()
                .member(member)
                .title(request.title())
                .content(request.content()).build();
        Post savedPost = postSaver.save(post);
        return savedPost.getId().toString();
    }

    @Transactional
    public void editContent(final Long postId, final PostUpdateRequest request) {
        Post post = postRetriever.findById(postId);
        postEditor.editContent(post, request.content());
    }

    @Transactional
    public void deleteById(final Long postId) {
        Post post = postRetriever.findById(postId);
        postRemover.remove(post);
    }

}
