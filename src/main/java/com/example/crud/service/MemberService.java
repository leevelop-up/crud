package com.example.crud.service;

import com.example.crud.domain.Member;
import com.example.crud.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public String join(Member member) {
        try {
            memberRepository.save(member);
            return "Member joined successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Join failed: " + e.getMessage();
        }
    }

    public String signOut(Integer id) {
        try {
            memberRepository.deleteById(id);
            return "Member signed out successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Sign out failed: " + e.getMessage();
        }
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
        try {
            return memberRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("List retrieval failed: " + e.getMessage());
        }
    }

    public String updateMember(Integer id, Member member) {
        try {
            Member existingMember = search(id);
            existingMember.toEntity(member);
            memberRepository.save(existingMember);
            return "Member updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update failed: " + e.getMessage();
        }
    }
}
