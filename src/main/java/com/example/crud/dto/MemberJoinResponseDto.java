package com.example.crud.dto;

import com.example.crud.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberJoinResponseDto {
    private String returnStatus;
    private String returnMessage;

    public MemberJoinResponseDto(Member member) {
        this.returnMessage = member.getName();

    }
    @Builder
    public MemberJoinResponseDto(String returnStatus, String returnMessage) {
        this.returnStatus = returnStatus;
        this.returnMessage = returnMessage;
    }

}
