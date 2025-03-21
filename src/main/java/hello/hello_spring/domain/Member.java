package hello.hello_spring.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 알아서 생성해주는 걸 identity라고함
    private Long id; // 시스템이 저장하는 id // db에서는 bigint

//    @Column(name="username") // 컬럼명이 username일 때 이렇게 쓰면 db에 있는 username과 맵핑
    private String name; // 이름

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
