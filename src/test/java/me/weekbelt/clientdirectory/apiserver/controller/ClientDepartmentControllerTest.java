package me.weekbelt.clientdirectory.apiserver.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.weekbelt.clientdirectory.apiserver.dto.department.DepartmentCreateRequest;
import me.weekbelt.clientdirectory.apiserver.service.ClientDepartmentService;
import me.weekbelt.clientdirectory.persistence.domain.PhoneType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest
public class ClientDepartmentControllerTest {

    private static final String BASE_URL = "/admin/v1/departments";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientDepartmentService clientDepartmentService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Department를 생성한다")
    public void create_department_success() throws Exception {
        // given
        DepartmentCreateRequest departmentCreateRequest = DepartmentCreateRequest.builder()
            .name("수지구청")
            .number("1234")
            .phoneType(PhoneType.INWARD_DIALING)
            .build();

        // when
        ResultActions resultActions = mockMvc.perform(post(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(departmentCreateRequest)));

        // then
        RestDocumentationResultHandler document = document("department",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            requestFields(
                fieldWithPath("name").description("부서명"),
                fieldWithPath("number").description("부서 번호"),
                fieldWithPath("phoneType").description("부서 번호 타입(내선, 외선, 그룹)")
            ),
            responseFields(
                fieldWithPath("id").description("부서의 식별 id"),
                fieldWithPath("name").description("부서명"),
                fieldWithPath("number").description("부서 번호"),
                fieldWithPath("phoneType").description("부서 번호 타입(내선, 외선, 그룹)")
            ));
        resultActions
            .andDo(document)
            .andExpect(status().isCreated())
//            .andExpect(jsonPath("id").value(""))
            .andExpect(jsonPath("name").value("수지구청"))
            .andExpect(jsonPath("number").value("1234"))
            .andExpect(jsonPath("phoneType").value("INWARD_DIALING"))
        ;
    }

    @Test
    @DisplayName("Department생성을 실패한다")
    public void create_department_fail() throws Exception {
        // given
        DepartmentCreateRequest departmentCreateRequest = DepartmentCreateRequest.builder()
            .number("1234")
            .phoneType(PhoneType.INWARD_DIALING)
            .build();

        // when
        ResultActions resultActions = mockMvc.perform(post("/admin/v1/departments")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(departmentCreateRequest)));

        // then
        resultActions
            .andExpect(status().is4xxClientError());
    }
}