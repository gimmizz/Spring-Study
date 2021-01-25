package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id); // ID로 회원을 찾을 수 있다.
    Optional<Member> findByName(String name);
    // Optional이란? 가져오려는 값이 null일 수 있는데, 이를 optional이라는 방법으로 감싸서 반환하는 방법을 선호
    List<Member> findAll();

}
