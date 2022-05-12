package me.weekbelt.clientdirectory.persistence.mapper;

import java.util.UUID;
import me.weekbelt.clientdirectory.apiserver.dto.department.DepartmentCreateRequest;
import me.weekbelt.clientdirectory.apiserver.dto.department.DepartmentResponse;
import me.weekbelt.clientdirectory.persistence.domain.Phone;
import me.weekbelt.clientdirectory.persistence.domain.department.Department;

public class DepartmentMapper {

    public static Department toDepartment(DepartmentCreateRequest departmentCreateRequest) {
        Phone phone = Phone.builder()
            .number(departmentCreateRequest.getNumber())
            .phoneType(departmentCreateRequest.getPhoneType())
            .build();

        return Department.builder()
            .id(UUID.randomUUID().toString())
            .name(departmentCreateRequest.getName())
            .phone(phone)
            .build();
    }

    public static DepartmentResponse toDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
            .id(department.getId())
            .name(department.getName())
            .number(department.getPhone().getNumber())
            .phoneType(department.getPhone().getPhoneType())
            .build();
    }
}
