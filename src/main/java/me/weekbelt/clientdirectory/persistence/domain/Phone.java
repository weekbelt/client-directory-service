package me.weekbelt.clientdirectory.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Embeddable
@Getter
@RequiredArgsConstructor
@Builder
public class Phone {

    @Column(nullable = false)
    private String number;

    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;
}