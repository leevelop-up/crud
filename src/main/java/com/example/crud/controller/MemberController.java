package com.example.crud.controller;

import com.example.crud.domain.Member;
import com.example.crud.dto.MemberJoinRequest;
import com.example.crud.dto.MemberListResponse;
import com.example.crud.dto.param.MemberJoinParam;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members/join")
    public ApiResponse<?> join(@RequestBody @Valid MemberJoinRequest memberJoinRequest) {
        MemberJoinParam param = memberJoinRequest.toParam();
        memberService.joinMember(param);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    @DeleteMapping("/members/{id}")
    public ApiResponse<?> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    @GetMapping("/members/search")
    public ApiResponse<?> searchMember(@RequestParam(required = false) String name,@RequestParam(required = false) String Gender, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)  Pageable page) {
        Page<Member> searchMember = memberService.searchMember(name, Gender,page);
        return ApiResponse.of(CrudPage.of(searchMember.map(MemberListResponse::new)));
    }

    @PatchMapping("/members/{id}")
    public ApiResponse<?> updateMember(@PathVariable Integer id, @RequestBody MemberJoinRequest memberJoinRequest) {
        memberService.updateMember(id, memberJoinRequest);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }
}
