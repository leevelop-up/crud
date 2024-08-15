package com.example.crud.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "member")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberNo;
    @Column(name = "member_name")
    private String memberName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "gender")
    private String gender;
    @Column(name = "birth")
    @CreatedDate
    private LocalDate birth;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    private String role;
    public void toEntity(Member member) {
        this.memberName = member.getMemberName();
        this.phoneNumber = member.getPhoneNumber();
        this.birth = member.getBirth();
        this.email = member.getEmail();
        this.role = member.getRole();
    }

}
