package hello.hellospring.domain;

import javax.persistence.*;

@Entity // JPA가 관리하는 Entity임을 표시
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Id : PK임을 표시, GeneratedValue : DB에서 값을 자동적으로 생성해줘! -> IDENTITY라고 한다
    private Long id;

//    @Column(name = "username") // 컬럼 이름을 username으로 해줘!
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
