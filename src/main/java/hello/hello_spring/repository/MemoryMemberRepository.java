package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository // 이미 SpringConfig에 저장 했으므로 생략가능
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이 반환될 가능성이 있으면 ofNullable 함수 사용
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // store를 끝까지 루프해서 filter 조건에 맞는 값들을 반환 없으면 null을 반환 따라서 리턴타입은 optional
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // member들을 반환
    }

    public void clearStore(){
        store.clear();
    }

}
