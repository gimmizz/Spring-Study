package hello.core;

import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;

public class AppConfig {

    public MemberService memberService(){ // MemberServiceImpl을 여기서 만들어준다!
        return new MemberServiceImpl();
    }

    public OrderService orderService(){

    }
}
