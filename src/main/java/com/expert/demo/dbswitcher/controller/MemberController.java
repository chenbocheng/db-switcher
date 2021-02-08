package com.expert.demo.dbswitcher.controller;

import com.expert.demo.dbswitcher.config.annotation.DS;
import com.expert.demo.dbswitcher.config.db.DataSourceConstants;
import com.expert.demo.dbswitcher.dto.R;
import com.expert.demo.dbswitcher.entity.Member;
import com.expert.demo.dbswitcher.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/find")
    public R find(String username) {
        Member member = memberService.findByUsername(username);
        return R.ok().put("member", member);
    }

    @DS(DataSourceConstants.DS_KEY_REPLICA)
    @GetMapping("/findInReplica")
    public R findInReplica(String username) {
        Member member = memberService.findByUsername(username);
        return R.ok().put("member", member);
    }

    @DS(DataSourceConstants.DS_KEY_PRIMARY)
    @GetMapping("/addMembers")
    public R addMembers() {
        List<Member> memberList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Member member = Member.builder()
                    .username("master" + i)
                    .enabled(true)
                    .build();
            memberService.save(member);
        }
        return R.ok();
    }

    @DS(DataSourceConstants.DS_KEY_REPLICA)
    @GetMapping("/addToReplica")
    public Object addToReplica() {
        List<Member> memberList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            memberList.add(
                    Member.builder().username("replica" + i)
                            .enabled(true)
                            .build()
            );
        }
        memberService.saveAll(memberList);
        return R.ok().put("members", memberList);
    }
}
