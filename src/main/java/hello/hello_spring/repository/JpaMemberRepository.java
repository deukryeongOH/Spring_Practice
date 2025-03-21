package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

// spring data JPA
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //jpa를 쓰려면 EntityManager 주입 받아야함 EntityManager는 gradle에서 jpa 설정할때 spring이 만들어옴

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // Spring Data JPA가 알아서 member를 저장해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //객체 자체를 select (select m from Member as m)
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
//        List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
//        return result;
    }
}
