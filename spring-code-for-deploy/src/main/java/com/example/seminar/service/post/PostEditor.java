package com.example.seminar.service.post;

import com.example.seminar.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostEditor {

    public void editContent(Post post, String content) {
        post.updateContent(content);
    }

}
