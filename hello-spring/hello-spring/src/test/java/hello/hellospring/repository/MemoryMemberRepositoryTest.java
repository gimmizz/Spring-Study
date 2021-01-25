package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 굳이 public으로 안 해도 된다. 다른 곳에서 사용 안하기 때문
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test가 끝날 때마다 repository를 깔끔하게 지워주는 명령어를 넣어야 한다.
    // @AfterEach : 각 메서드가 끝날 때마다 이 동작을 수행해라
    // [Ctrl] + [B] 로 MemoryMemberRepository 클래스로 가서 clearStore 메서드를 생성
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // optional에서 data를 꺼내는 방법으로 .get()를 사용, 사실 좋은 방법은 아니지만, 
        // test 케이스니까^_^

        // 일일이 로그를 확인할 수 없으니까, asssert 사용
        // 방법 1. org.junit.jupiter.api (expect, actual 순)
        // Assertions.assertEquals(member, result);

        // 방법 org.assertj.core.api (보다 더 직관적)
        assertThat(member).isEqualTo(result); // [Alt]+[Enter]로 static하게 바꾸기
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // [Shift]+[F6] 하면 동시 rename
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // [Shift]+[F6] 하면 동시 rename
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}