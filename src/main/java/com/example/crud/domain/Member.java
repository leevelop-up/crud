package com.example.crud.domain;

import com.example.crud.dto.MemberJoinRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "member")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Integer id;
    @Column(name = "member_name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String gender;
    private LocalDate birth;
    private String email;
    private String role;

    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", birth=" + birth +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public void updateMember(String name, String phoneNumber, String email, LocalDate birth) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birth = birth;
        this.email = email;
    }
}
