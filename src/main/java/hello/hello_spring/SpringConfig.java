package hello.hello_spring;



import hello.hello_spring.aop.TimeTraceAop;
//import hello.hello_spring.repository.JdbcTemplateMemberRepository;
import hello.hello_spring.repository.JpaMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.swing.*;


// Service와 Repository 어노테이션 전부 지우고 직접 스프링 빈을 등록하는 방법(단 Controller 어노테이션은 남겨둠)

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } // 스프링 데이터 jpa 사용 시

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    } // jpa 사용시 (스프링 데이터 jpa 아님)



//    private final DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    } // 순수 jdbc 사용시 필요
    @Bean
    public MemberService memberService(){
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository); // 스프링 데이터 jpa 사용 시
    }
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    } // @component 대신 사용 가능

//    @Bean spring data jpa 사용시 사용 X
//    public MemberRepository memberRepository(){
////        return new MemoryMemberRepository(); // 인터페이스는 new가 안돼
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(em);
//
//    }
}
