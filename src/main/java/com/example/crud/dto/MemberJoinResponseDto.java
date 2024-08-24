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
    private String name;
    private String phoneNumber;
    private String email;
    private LocalDate birth;
    private String gender;
    private String role;

    public MemberJoinResponseDto(Member member) {
        this.returnMessage = member.getName();
        this.name = member.getName();
        this.phoneNumber = member.getPhoneNumber();
        this.email = member.getEmail();
        this.birth = member.getBirth();
        this.gender = member.getGender();
        this.role = member.getRole();
    }
    @Builder
    public MemberJoinResponseDto(String returnStatus, String returnMessage, String name, String phoneNumber, String email, LocalDate birth, String gender, String role) {
        this.returnStatus = returnStatus;
        this.returnMessage = returnMessage;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
        this.role = role;
    }

}
