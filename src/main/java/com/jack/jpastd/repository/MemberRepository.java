package com.jack.jpastd.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jack.jpastd.domain.Member;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository // Spring boot에서 자동 관리
@RequiredArgsConstructor
public class MemberRepository {
    
    //@PersistenceContext // JPA에서 표준으로 제공  
    private final EntityManager em; // Spring이 EntityManager를 만들어서 주입해준다.
    
    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
    
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
    
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
