package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; // 항상 1000원만 할인하겠다

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { // Enum은 == 으로 비교하는 것이 맞다!
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
