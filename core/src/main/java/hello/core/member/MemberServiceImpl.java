package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    // 단축키 : go to implementations [Ctrl]+[Alt]+[B]
    //         back [Ctrl]+[Alt]+[<-]


    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
