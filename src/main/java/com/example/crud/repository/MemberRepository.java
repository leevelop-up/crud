package com.example.crud.repository;

import com.example.crud.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {


    Page<Member> findByNameContaining(String keyword, Pageable page);
}
