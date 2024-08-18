package com.example.crud.controller;

import com.example.crud.domain.Member;
import com.example.crud.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<String> join(@RequestBody Member member) {
        String result = memberService.join(member);
        if (result.contains("successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @DeleteMapping("/members/{id}/withdraw")
    public ResponseEntity<String> signOut(@PathVariable Integer id) {
        String result = memberService.signOut(id);
        if (result.contains("successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Member> search(@PathVariable Integer id) {
        try {
            Member member = memberService.search(id);
            return ResponseEntity.ok(member);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Member>> memberList() {
        try {
            List<Member> members = memberService.memberList();
            return ResponseEntity.ok(members);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Integer id, @RequestBody Member member) {
        String result = memberService.updateMember(id, member);
        if (result.contains("successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}
