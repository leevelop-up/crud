package com.example.crud.controller;

import com.example.crud.domain.Member;
import com.example.crud.dto.MemberJoinRequestDto;
import com.example.crud.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members/join")
    public ResponseEntity<String> join(@RequestBody MemberJoinRequestDto memberJoinRequestDto) {
        String result = memberService.join(memberJoinRequestDto);
        if (result.contains("successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<String> memberOut(@PathVariable Integer id) {
        String result = memberService.withdraw(id);
        if (result.contains("successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @GetMapping("/members/search")
    public Page<Member> memberList(@RequestParam(required = false) String name, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)  Pageable page) {
        return memberService.memberList(name, page);
    }

    @PatchMapping("/members/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Integer id, @RequestBody Member member) {
        String result = memberService.updateMember(id, member);
        if (result.contains("successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}
