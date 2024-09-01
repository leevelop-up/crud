package com.example.crud.dto.param;

import com.example.crud.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberJoinParam {
    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String gender;
    @NotNull
    private LocalDate birth;
    @NotBlank
    private String email;

    public Member toDomain() {
        return Member.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .birth(birth)
                .gender(gender)
                .build();
    }
}
