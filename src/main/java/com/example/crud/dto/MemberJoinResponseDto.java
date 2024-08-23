package com.example.crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(value=NON_NULL)
public record MemberJoinResponseDto(boolean isSuccess, String comment) {
}
