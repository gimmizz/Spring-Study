package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    // clear 필요
    MemoryMemberRepository memberRepository;

    // MemoryMemberRepository가 main에서와 test에서 각각 따로 생성(new 연산자를 통해)되는 것을 막기 위해
    // memberService의 생성자에 MemoryMemberRepository를 받도록 하고,
    // test에서는, test 할 때마다, 하기 전에 객체를 생성하도록 한다.

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given (이런 게 주어졌을 때)
        Member member = new Member();
        member.setName("spring");

        // when (이런 걸 시행했을 때)
        Long saveId = memberService.join(member);

        // then (이런 결과가 나와야 해)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // 예외처리 방법 1 : try, catch 구문
//        try{
//            memberService.join(member2); // 예외가 발생해야 한다.
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
//            // [Ctrl]+[Shift]+[B]으로 memberService 갔다가, [Ctrl]+[E]로 recent로 back
//        }

        // 예외처리 방법 2 : assertThrows 구문 (param2 실행했을때, param1 에러가 발생해야 한다.) + 반환 가능
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // [Ctrl]+[Alt]+[V] 로 자동 return화

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 에러 발생 시, 메시지 확인용 출력


        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}