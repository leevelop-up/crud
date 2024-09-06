package com.example.crud.domain;

import com.example.crud.dto.MemberJoinRequest;
import com.example.crud.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "member")
public class Member extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birth;
    private String email;
    private String password;
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

    public void updateMember(String name, String phoneNumber,Gender gender, String email, LocalDate birth) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birth = birth;
        this.email = email;
    }
}
