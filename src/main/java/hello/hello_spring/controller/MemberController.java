package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
    //@Autowired private MemberService memberService; 도 가능하지만 이 방법은 권장x 바꿀수가 없음
    /*@Autowired
    public void setMemberService(MemberService memberService){
        this.memberService = memberService;
    } setter로 DI 하는 법, 단점 public하게 해야 하기 때문에 노출될 위험있음
     */

    @Autowired // spring container에서 memberservice를 가져와 자동으로 연결 시켜줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    } // Dependency Injection(DI) 생성될 때 한번 호출되기 때문에 안전 (추천 !!!!)


    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm"; // url 입력해서 단순히 페이지 이동(getMapping)
    }

    // getMapping=조회, postMapping=데이터등록
    @PostMapping("/members/new") // postMapping인 이유 createMemberForm.html에서 form 태그 메소드가 post로 설정되었기 때문
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
// MemberForm에 어떻게 데이터들이 들어감?? -> controller에 있는 변수 중 변수 명이 name인 변수에 저장됨.
        memberService.join(member); //회원가입 로직

        return "redirect:/"; // home 화면으로 보내는것
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members"/*local 뒤에 주소*/, members);
        return "members/memberList"; // html파일의 memberList로 간다
    }
}


//@Autowired는 스프링 빈에 등록된
//(@Controller, @Repository, @Service, @Component 한 객체) 객체만 가능
