package me.weekbelt.clientdirectory.apiserver.dto.department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.clientdirectory.persistence.domain.PhoneType;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {

    private String id;

    private String name;

    private String number;

    private PhoneType phoneType;
}
