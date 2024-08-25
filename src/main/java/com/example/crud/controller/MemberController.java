package com.example.crud.controller;

import com.example.crud.domain.Member;
import com.example.crud.dto.MemberJoinRequestDto;
import com.example.crud.dto.MemberJoinResponseDto;
import com.example.crud.dto.MemberListResponseDto;
import com.example.crud.service.MemberService;
import jakarta.validation.Valid;
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
    public ResponseEntity<MemberJoinResponseDto> join(@RequestBody @Valid MemberJoinRequestDto memberJoinRequestDto) {
        memberService.joinMember(memberJoinRequestDto);
        MemberJoinResponseDto responseDto = MemberJoinResponseDto.builder().returnStatus(HttpStatus.OK.toString()).returnMessage("요청에 성공하였습니다.").build();
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<MemberJoinResponseDto> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        MemberJoinResponseDto responseDto = MemberJoinResponseDto.builder().returnStatus(HttpStatus.OK.toString()).returnMessage("요청에 성공하였습니다.").build();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/members/search")
    public Page<MemberListResponseDto> searchMember(@RequestParam(required = false) String name, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)  Pageable page) {
        Page<Member> searchMember = memberService.searchMember(name, page);

        return searchMember.map(MemberListResponseDto::new);
    }

    @PatchMapping("/members/{id}")
    public ResponseEntity<MemberJoinResponseDto> updateMember(@PathVariable Integer id, @RequestBody MemberJoinRequestDto memberJoinRequestDto) {
        memberService.updateMember(id, memberJoinRequestDto);
        MemberJoinResponseDto responseDto = MemberJoinResponseDto.builder().returnStatus(HttpStatus.OK.toString()).returnMessage("요청에 성공하였습니다.").build();
        return ResponseEntity.ok(responseDto);
    }
}
