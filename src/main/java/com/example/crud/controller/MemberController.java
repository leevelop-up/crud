package com.example.crud.controller;

import com.example.crud.domain.Member;
import com.example.crud.dto.MemberJoinRequestDto;
import com.example.crud.dto.MemberJoinResponseDto;
import com.example.crud.dto.MemberListResponseDto;
import com.example.crud.dto.response.ApiResponse;
import com.example.crud.dto.response.CrudPage;
import com.example.crud.enums.ReturnCode;
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
    public ApiResponse<?> join(@RequestBody @Valid MemberJoinRequestDto memberJoinRequestDto) {
        memberService.joinMember(memberJoinRequestDto);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    @DeleteMapping("/members/{id}")
    public ApiResponse<?> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    @GetMapping("/members/search")
    public ApiResponse<?> searchMember(@RequestParam(required = false) String name, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)  Pageable page) {
        Page<Member> searchMember = memberService.searchMember(name, page);
        return ApiResponse.of(CrudPage.of(searchMember.map(MemberListResponseDto::new)));
    }

    @PatchMapping("/members/{id}")
    public ApiResponse<?> updateMember(@PathVariable Integer id, @RequestBody MemberJoinRequestDto memberJoinRequestDto) {
        memberService.updateMember(id, memberJoinRequestDto);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }
}
