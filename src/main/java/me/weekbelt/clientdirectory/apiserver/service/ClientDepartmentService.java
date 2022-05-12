package me.weekbelt.clientdirectory.apiserver.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.clientdirectory.apiserver.dto.department.DepartmentCreateRequest;
import me.weekbelt.clientdirectory.apiserver.dto.department.DepartmentResponse;
import me.weekbelt.clientdirectory.persistence.domain.department.Department;
import me.weekbelt.clientdirectory.persistence.domain.department.service.DepartmentDataService;
import me.weekbelt.clientdirectory.persistence.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClientDepartmentService {

    private final DepartmentDataService departmentDataService;

    @Transactional
    public DepartmentResponse save(DepartmentCreateRequest departmentCreateRequest) {
        Department department = departmentDataService.save(DepartmentMapper.toDepartment(departmentCreateRequest));
        return DepartmentMapper.toDepartmentResponse(department);
    }
}
