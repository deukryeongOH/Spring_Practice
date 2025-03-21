package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
//@Service  // @Service안에 @Component 있음
public class MemberService {
// ctrl + shift + T 바로 Test 만들어짐
    private final MemberRepository memberRepository;

    @Autowired // 생성자에 붙여주면 자동 연결
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } // 이것을 dependency injection 소위 DI라 불린다.

    /**
     * 회원 가입
     *
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원x
        validateDuplicateMember(member); // 중복회원 검증
//        Optional<Member> result = memberRepository.findByName(member.getName());
//
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
            //        Member member1 = result.get(); 위에 대신 이것도 가능하지만 권장하진 않음

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 회원 1명 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
