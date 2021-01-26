package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //    private final DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource; // spring에서 자체적으로 dataSource를 제공. springConfig에 주입.
//    }
    // JPA에서는 dataSource가 포함된 entityManager가 필요하다. @PersistenceContect로 해서 받아도 된다!
    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    // 동작 순서
    // 1. @Bean 2개 인식해서 각각 스프링 빈으로 등록
    // 2. memberService 생성 시, 스프링 빈으로 등록되어있는 memberRepository을 넣어서 생성
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
