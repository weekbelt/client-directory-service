package me.weekbelt.clientdirectory.apiserver.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.weekbelt.clientdirectory.apiserver.dto.department.DepartmentCreateRequest;
import me.weekbelt.clientdirectory.apiserver.dto.department.DepartmentResponse;
import me.weekbelt.clientdirectory.apiserver.service.ClientDepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class ClientDepartmentController {

    private final ClientDepartmentService departmentService;

    @PostMapping("/v1/departments")
    @ResponseStatus(code = HttpStatus.CREATED)
    public DepartmentResponse save(@RequestBody @Valid DepartmentCreateRequest departmentCreateRequest) {
        return departmentService.save(departmentCreateRequest);
    }
}
