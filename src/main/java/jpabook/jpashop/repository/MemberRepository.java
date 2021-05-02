package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    /**
     * 회원 저장
     * @param member
     */
    public void save(Member member) {
        em.persist(member);
    }

    /**
     * 회원 단건 조회
     * @param id
     * @return
     */
    public Member findOne(Long id) {
        Member member = em.find(Member.class, id);
        return member;
    }

    /**
     * 회원 다건 조회
     * @return
     */
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        return result;
    }

    /**
     * 회원 이름으로 조회
     * @param name
     * @return
     */
    public List<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)     // 파라미터를 묶어준다.
                .getResultList();
        return result;
    }
}
