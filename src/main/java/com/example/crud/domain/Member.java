package com.example.crud.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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

    public void toEntity(Member member) {
        this.name = member.getName();
        this.phoneNumber = member.getPhoneNumber();
        this.birth = member.getBirth();
        this.email = member.getEmail();
        this.role = member.getRole();
    }
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
}
