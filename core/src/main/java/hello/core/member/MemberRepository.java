package hello.core.member;

public interface MemberRepository {

    void save(Member member); //회원을 저장

    Member findById(Long memberId); // 회원의 id로 찾는 기능
}
