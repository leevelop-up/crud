package com.example.crud.service;

import com.example.crud.domain.Member;
import com.example.crud.dto.MemberJoinRequestDto;
import com.example.crud.dto.MemberJoinResponseDto;
import com.example.crud.exception.CrudException;
import com.example.crud.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.crud.exception.ErrorCode.MEMBER_DUPLICATION;
import static com.example.crud.exception.ErrorCode.VALUE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public void joinMember(MemberJoinRequestDto memberJoinRequestDto) {
        if (memberJoinRequestDto.getName() == null || memberJoinRequestDto.getPhoneNumber() == null || memberJoinRequestDto.getEmail() == null || memberJoinRequestDto.getGender() == null || memberJoinRequestDto.getRole() == null) {
            throw new CrudException(VALUE_NOT_FOUND, "Invalid member data");
        }
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
    }

    public void deleteMember(Integer id) {
        memberRepository.findById(id)
                .orElseThrow(() -> {throw new CrudException(VALUE_NOT_FOUND, "Member not found");});
        memberRepository.deleteById(id);
    }

    public Member search(Integer id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> {throw new CrudException(VALUE_NOT_FOUND, "Member not found");});
    }

    public Page<Member> searchMember(String name, Pageable page) {

        Page<Member> result;
        if (name != null) {
            result = memberRepository.findByNameContaining(name, page);
        } else {
            result = memberRepository.findAll(page);
        }
        if(result.isEmpty()) {
            throw new CrudException(VALUE_NOT_FOUND, "No members found");
        }

        return result;
    }

    public void updateMember(Integer id, MemberJoinRequestDto memberJoinRequestDto) {
        memberRepository.findById(id)
                .orElseThrow(() -> {throw new CrudException(MEMBER_DUPLICATION, "Member not found");});
        Member existingMember = search(id);
        existingMember.toEntity(memberJoinRequestDto);
    }
}
