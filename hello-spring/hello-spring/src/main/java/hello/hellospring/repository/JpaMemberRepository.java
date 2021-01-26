package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;
    // JPA는 entitymanager를 통해 모든 동작 수행.
    // 스프링 부트가 자동적으로 DB connection(내부적으로 datasource도 있다), 설정값 들 모두를 설정해서, EntityManager를 생성.
    // 우리가 해야할 일은, 이를 Injection하는 것 뿐.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    // 저장하고, 조회하고, 업데이트 하는 등 단순작업은 쿼리를 짤 필요가 없다.
    // PK 기반이 아닌 기능(findByName, findAll)은 jpql을 작성해야 한다.
    @Override
    public Member save(Member member) {
        em.persist(member); // persist : 영속화하다 -> jpa가 insert 쿼리 만들어서 db에 주입, setId까지 모두 해준다..
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);// (조회할 type, 식별자/pk값)
        return Optional.ofNullable(member); // null 값일 수도 있어서 of Nullable 사용
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny(); // jpql이라는 객체지향 쿼리 언어를 사용해야 한다.
    }

    @Override
    public List<Member> findAll() {
        // jpql이라는 객체지향 쿼리 언어를 사용해야 한다. 테이블을 대상으로 쿼리를 날리는 게 아니라, 객체(m)를 대상으로!
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
//        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
//        return result;
    }
}
