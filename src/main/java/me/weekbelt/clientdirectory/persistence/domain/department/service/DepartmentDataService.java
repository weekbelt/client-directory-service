package me.weekbelt.clientdirectory.persistence.domain.department.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.clientdirectory.persistence.domain.department.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DepartmentDataService {

    private final DepartmentRepository departmentRepository;
}
