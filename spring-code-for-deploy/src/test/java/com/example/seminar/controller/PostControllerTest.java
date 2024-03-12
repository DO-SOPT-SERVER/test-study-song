package com.example.seminar.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.example.seminar.common.exception.PostException;
import com.example.seminar.config.MockAllServiceBeanFactoryPostProcessor;
import com.example.seminar.dto.request.post.PostCreateRequest;
import com.example.seminar.dto.request.post.PostUpdateRequest;
import com.example.seminar.service.post.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Import(MockAllServiceBeanFactoryPostProcessor.class)
@WebMvcTest(controllers = PostController.class)
public class PostControllerTest extends ControllerTestManager {

    @Autowired
    private PostService postService;

    private static final String CUSTOM_USER_ID = "X-Auth-Id";
    private static final String POST_API_ENDPOINT = "/api/posts";

    @Test
    void given_정상입력_when_게시글등록_then_게시글등록성공() throws Exception {
        // given
        doReturn("1").when(postService).create(any(PostCreateRequest.class), any(Long.class));

        // when
        PostCreateRequest request = new PostCreateRequest("게시물제목입니다.", "게시글 내용으로는 제가 생각한...더보기");

        // then
        mockMvc.perform(
                        MockMvcRequestBuilders.post(POST_API_ENDPOINT)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(json)
                                .header(CUSTOM_USER_ID, 1L))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/api/posts/1"));
    }

    @Test
    void given_게시글제목3자미만경우_when_게시글등록_then_예외발생() throws Exception {
        // given
        doThrow(new PostException("게시글 제목은 3자 미만이 될 수 없습니다.")).when(postService).create(any(PostCreateRequest.class), any(Long.class));

        // when
        PostCreateRequest request = new PostCreateRequest("하이", "게시글 내용으로는 제가 생각한...더보기");

        // then
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(POST_API_ENDPOINT)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(json)
                        .header(CUSTOM_USER_ID, 1L));

        resultActions.andExpect(status().isBadRequest());

    }

    @Test
    void given_정상경우_when_게시글삭제_then_게시글삭제성공() throws Exception {
        // given
        doNothing().when(postService).deleteById(any(Long.class));

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.delete(POST_API_ENDPOINT+"/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void given_정상경우_when_게시글수정_then_게시글수정성공() throws Exception {
        // given
        PostUpdateRequest postUpdateRequest = new PostUpdateRequest("이렇게 수정해볼게여!");
        doNothing().when(postService).editContent(any(Long.class), any(PostUpdateRequest.class));

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.patch(POST_API_ENDPOINT+"/1")
                        .contentType(json)
                        .content(objectMapper.writeValueAsString(postUpdateRequest)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
