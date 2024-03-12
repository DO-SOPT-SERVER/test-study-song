package com.example.seminar.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.seminar.common.exception.MemberException;
import com.example.seminar.config.MockAllServiceBeanFactoryPostProcessor;
import com.example.seminar.domain.Fixtures;
import com.example.seminar.domain.Member;
import com.example.seminar.domain.Part;
import com.example.seminar.domain.SOPT;
import com.example.seminar.dto.request.member.MemberCreateRequest;
import com.example.seminar.dto.response.MemberGetResponse;
import com.example.seminar.service.member.MemberService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Import(MockAllServiceBeanFactoryPostProcessor.class)
@WebMvcTest(controllers = MemberController.class)
public class MemberControllerTest extends ControllerTestManager {

    //@Autowired
    @Autowired
    private MemberService memberService;

    private static final String MEMBER_API_ENDPOINT = "/api/member";


    @Test
    void given_정상입력_when_멤버등록_then_멤버등록성공() throws Exception {
        // given
        doReturn(MEMBER_API_ENDPOINT + "/1")
                .when(memberService).create(any(MemberCreateRequest.class));

        // when
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest("송민규", "송민규짱짱맨", 20,
                SOPT.builder().part(Part.SERVER).build());

        // then
        mockMvc.perform(MockMvcRequestBuilders.post(MEMBER_API_ENDPOINT)
                        .content(objectMapper.writeValueAsString(memberCreateRequest))
                        .contentType(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/api/member/1"));
    }

    @Test
    void given_정상입력_when_멤버조회_then_멤버리스트반환() throws Exception {
        // given, when
        List<Member> members = Fixtures.createMembers(10, "송민규", "송민규하이", Part.SERVER);
        doReturn(members.stream().map(MemberGetResponse::of).toList())
                .when(memberService).getMembers();

        // then
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(MEMBER_API_ENDPOINT)
                .contentType(json));

        for (int cnt = 0; cnt < members.size(); cnt++) {
            resultActions.andExpect(jsonPath("$[" + cnt + "].name").value(members.get(cnt).getName()));
            resultActions.andExpect(jsonPath("$[" + cnt + "].age").value(members.get(cnt).getAge()));

        }
    }

    @Test
    void given_이름null경우_when_멤버등록_then_예외발생() throws Exception {
        // given, when
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest(null, "송민규짱짱맨", 20,
                SOPT.builder().part(Part.SERVER).build());
        doThrow(new MemberException("null 값이 될 수 없습니다.")).when(memberService).create(any(MemberCreateRequest.class));

        // then
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(MEMBER_API_ENDPOINT)
                .content(objectMapper.writeValueAsString(memberCreateRequest))
                .contentType(json));

        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void given_나이가100초과인경우_when_멤버등록_then_예외발생() throws Exception {
        // given, when
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest("송민규", "송민규짱짱맨", 101,
                SOPT.builder().part(Part.SERVER).build());
        doThrow(new MemberException("회원의 나이는 0세 이상 100세 이하입니다.")).when(memberService)
                .create(any(MemberCreateRequest.class));

        // then
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(MEMBER_API_ENDPOINT)
                .content(objectMapper.writeValueAsString(memberCreateRequest))
                .contentType(json));

        resultActions.andExpect(status().isBadRequest());
    }
}
