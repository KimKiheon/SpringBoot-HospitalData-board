package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleResponse;
import com.mustache.bbs.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ArticleControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("JSON 형태로 RESPONSE 잘 전달 되는가")
    void jsonResponse() throws Exception {
        ArticleResponse articleResponse = ArticleResponse.builder().id(1L)
                .id(1L)
                .title("제목1")
                .content("내용1")
                .build();
        given(articleService.getArticle(1L))
                .willReturn(articleResponse);

        Long articleId = 1L;
        String url = String.format("/api/v1/articles/%d", articleId);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.title").exists()) // $는 루트 안에 hospitalName이 있어야함
                .andExpect((ResultMatcher) jsonPath("$.title").value("제목1"))
                .andExpect((ResultMatcher) jsonPath("$.content").exists())
                .andExpect((ResultMatcher) jsonPath("$.content").value("내용1"))
                .andDo(print()); // http request, response내역을 출력해라.



    }

}
