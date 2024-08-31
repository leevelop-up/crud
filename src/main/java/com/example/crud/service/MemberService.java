package com.example.crud.service;

import com.example.crud.domain.Member;
import com.example.crud.dto.MemberJoinRequest;
import com.example.crud.exception.CrudException;
import com.example.crud.repository.MemberJpaRepository;
import com.example.crud.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.example.crud.exception.ErrorCode.VALUE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepository memberRepository;

    public void joinMember(MemberJoinRequest memberJoinRequest) {
        if (memberJoinRequest.getName() == null || memberJoinRequest.getPhoneNumber() == null || memberJoinRequest.getEmail() == null || memberJoinRequest.getGender() == null) {
            throw new CrudException(VALUE_NOT_FOUND, "Invalid member data");
        }
        LocalDate now = LocalDate.now();
        Member member = Member.builder()
                .name(memberJoinRequest.getName())
                .phoneNumber(memberJoinRequest.getPhoneNumber())
                .email(memberJoinRequest.getEmail())
                .birth(now)
                .gender(memberJoinRequest.getGender())
                .build();

        memberJpaRepository.save(member);
    }

    public void deleteMember(Integer id) {
        memberJpaRepository.findById(id)
                .orElseThrow(() -> {throw new CrudException(VALUE_NOT_FOUND, "Member not found");});
        memberJpaRepository.deleteById(id);
    }


    public Page<Member> searchMember(String name,String gender, Pageable page) {
        Page<Member> result;
        if (name != null) {
            result = memberRepository.getQueryFactory(name,gender,page);
        } else {
            result = memberJpaRepository.findAll(page);
        }
        return result;
    }

    public void updateMember(Integer id, MemberJoinRequest memberJoinRequest) {
        Member existingMember = memberJpaRepository.findById(id)
                .orElseThrow(() -> {throw new CrudException(VALUE_NOT_FOUND, "Member not found");});

        existingMember.updateMember(
                memberJoinRequest.getName(),
                memberJoinRequest.getPhoneNumber(),
                memberJoinRequest.getEmail(),
                memberJoinRequest.getBirth()
        );

    }
}
