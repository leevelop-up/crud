package com.example.crud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MemberJoinRequestDto {
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호는 '010-2222-1111' 형식이어야 합니다.")
    private String phoneNumber;
    @Pattern(regexp = "^[MF]$", message = "성병 혁식이 아닙니다.")
    private String gender;
    private LocalDate birth;
    @Email(message = "이메일 형식이 아닙니다.")
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
