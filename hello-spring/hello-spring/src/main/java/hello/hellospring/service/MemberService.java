package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService { // [Ctrl]+[Shift]+[T] : test 클래스 자동 생성
    // final 키워드가 붙어 있으면 값을 생성자에서 초기화 한 이후에 변경할 수 없습니다.
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository; // memberRepository를 직접 생성하는 것이 아니라, 외부에서 넣어주도록 바꾸자.
    }

    /**
     * 회원 가입
     *  - 같은 이름이 있는 중복 회원은 안 된다.
     * @param member
     * @return id
     */
    public Long join (Member member) {
        // 같은 이름이 있는 중복 회원은 안된다.
        validateDuplicateMember(member); // Extract Method 단축키 : [Ctrl]+[Alt]+[Shift]+[t] 에서 9.Extract Method

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());// [Ctrl]+[Alt]+[V] 하면 return을 바로 해준다!
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }); // 만약 이미 존재하면, 이 예외처리를 진행해라! optional로 null처리를 하니까 가능한 일
    }

    /**
     * 전체 회원 조회
     * @return list
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
