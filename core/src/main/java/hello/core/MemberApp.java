package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp { // 잘 되는지 테스트 해보자.
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "memberA", Grade.VIP); // 뒷부분만 작성하고 [Ctrl]+[Alt]+[V] : 자동완성
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName()); // 단축키 : soutv
        System.out.println("find member = " + findMember.getName());
    }
}
