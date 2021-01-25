package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // new 생성자를 사용하면 발생하는 문제 -> MemberController 말고 다른 여러 컨트롤러들이 MemberService를 사용할 것
    // 이 경우, 여러 인스턴스를 쓸 필요가 없다. 하나만 생성해서 공용으로 사용하면 된다.
    // -> 스프링 컨테이너에 등록하고 공용으로 사용!!
    // private final MemberService memberService = new MemberService(); 대신에 아래와 같이 생성자 사용하자.
    private final MemberService memberService;

    @Autowired // 생성자에 붙은 Autowired : 스프링이 컨테이너에 있는 MemberService를 가져와 연결시켜 줌 = D.I (Dependency Injection)
    public MemberController(MemberService memberService) { // [Alt]+[Insert] Generate Constructor 단축키
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/"; // 회원 가입이 끝났으니까, 홈 화면으로 돌아간다.
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
