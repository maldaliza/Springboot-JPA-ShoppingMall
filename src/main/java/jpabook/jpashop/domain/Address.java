package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter     // 값 타입은 변경이 불가능하게 설계되어야 한다. => @Setter 제거
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // JPA 스펙상 엔티티나 임베디드 타입은 자바 기본 생성자를 public 또는 protected로 설정해야 한다.
    // JPA가 이런 제약을 두는 이유는 JPA 구현 라이브러리가 객체를 생성할 때,
    // 리플랙션 같은 기술을 사용할 수 있도록 지원하기 위해서이다.
    protected Address() {
    }

    // 생성자에서 값을 모두 초기화하여 변경 불가능한 클래스를 만든다.
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
