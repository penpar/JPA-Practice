package com.jack.jpastd.service;

import com.jack.jpastd.repository.MemberRepository;

import java.util.List;

import com.jack.jpastd.domain.Member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@Service
@Transactional(readOnly = true) // Transactional : javax, spring 두 종류 있다. spring꺼 사용 권장
//@AllArgsConstructor : 자동으로 생성자 생성
@RequiredArgsConstructor // : final로 잡혀있는 경우에 생성자 생성
public class MemberService {
    
    // final 넣는걸 추천
    private final MemberRepository memberRepository;

    // 원활한 테스트를 위해 성성자 생성
    // 최신 Spring에서는 생성자가 하나 일 경우 자동으로 Autowired 해준다.
    // @Autowired 
    // public MemberService(MemberRepository memberRepository){
    //     this.memberRepository = memberRepository;
    // }


    // 회원 가입
    @Transactional // Default가 false 
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
