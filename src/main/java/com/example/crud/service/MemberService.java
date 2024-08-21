package com.example.crud.service;

import com.example.crud.domain.Member;
import com.example.crud.dto.MemberJoinRequestDto;
import com.example.crud.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public String join(MemberJoinRequestDto memberJoinRequestDto) {
        LocalDate now = LocalDate.now();
        Member member = Member.builder()
                .name(memberJoinRequestDto.getName())
                .phoneNumber(memberJoinRequestDto.getPhoneNumber())
                .email(memberJoinRequestDto.getEmail())
                .birth(now)
                .gender(memberJoinRequestDto.getGender())
                .role(memberJoinRequestDto.getRole())
                .build();

        memberRepository.save(member);

        return "Member signed up successfully";
    }

    public String withdraw(Integer id) {
        memberRepository.deleteById(id);
        return "Member signed out successfully";
    }

    public Member search(Integer id) {
        try {
            return memberRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Search failed: " + e.getMessage());
        }
    }

    public List<Member> memberList() {
        return memberRepository.findAll();
    }

    public String updateMember(Integer id, Member member) {
        Member existingMember = search(id);
        existingMember.toEntity(member);
        return "Member updated successfully";
    }
}
