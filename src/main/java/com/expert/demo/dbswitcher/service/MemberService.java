package com.expert.demo.dbswitcher.service;

import com.expert.demo.dbswitcher.entity.Member;
import com.expert.demo.dbswitcher.exception.AppException;
import com.expert.demo.dbswitcher.repoitory.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("Member '" + username + "' does not exist!"));
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> saveAll(Iterable<Member> members) {
        return memberRepository.saveAll(members);
    }
}
