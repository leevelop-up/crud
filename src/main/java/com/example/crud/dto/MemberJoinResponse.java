package com.example.crud.dto;

import com.example.crud.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinResponse {
    private String returnStatus;
    private String returnMessage;

    public MemberJoinResponse(Member member) {
        this.returnMessage = member.getName();

    }
    @Builder
    public MemberJoinResponse(String returnStatus, String returnMessage) {
        this.returnStatus = returnStatus;
        this.returnMessage = returnMessage;
    }

}
