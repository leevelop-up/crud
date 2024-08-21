package com.example.crud.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MemberJoinRequestDto {
    private String name;
    private String phoneNumber;
    private String gender;
    private LocalDate birth;
    private String email;
    private String role;

    public String toString() {
        return "Member{" +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", birth=" + birth +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
