package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService(){ // MemberServiceImpl을 여기서 만들어준다!
        return new MemberServiceImpl(memberRepository()); // 생성자를 통해 객체가 들어간다 | 생성자 주입.
    }

    private MemberRepository memberRepository() { // Refactoring : [Ctrl]+[Alt]+[M] / 주의 : return type은 인터페이스.
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
