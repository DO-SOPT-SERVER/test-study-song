package com.example.seminar.controller;


import com.example.seminar.dto.request.post.PostCreateRequest;
import com.example.seminar.dto.request.post.PostUpdateRequest;
import com.example.seminar.dto.response.post.PostGetResponse;
import com.example.seminar.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

    @RestController
    @RequestMapping("/api/posts")
    @RequiredArgsConstructor
    public class PostController {
        private static final String CUSTOM_AUTH_ID = "X-Auth-Id";

        private final PostService postService;

        @PostMapping
        public ResponseEntity<Void> createPost(
                @RequestHeader(CUSTOM_AUTH_ID) Long memberId,
                @RequestBody PostCreateRequest request) {
            URI location = URI.create("/api/post/" + postService.create(request, memberId));
            return ResponseEntity.created(location).build();
        }

        @PatchMapping("{postId}")
        public ResponseEntity<Void> updatePost(
                @PathVariable Long postId,
                @RequestBody PostUpdateRequest request) {
            postService.editContent(postId, request);
            return ResponseEntity.noContent().build();
        }

        @DeleteMapping("{postId}")
        public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
            postService.deleteById(postId);
            return ResponseEntity.noContent().build();
        }
    }
