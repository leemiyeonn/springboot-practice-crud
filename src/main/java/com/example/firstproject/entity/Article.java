package com.example.firstproject.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id; // ID import값 확인

@Entity // DB가 해당 객체를 인식 가능!
@AllArgsConstructor // lombok - 생성자
@NoArgsConstructor // 디폴트 생성자 (Article(){} 파라메터가 없는 생성자)
@ToString // lombok
@Getter // lombok
public class Article {
    @Id // 대표값을 지정! like a 주민등록번호 // entity에 기본적으로 있어야함
    @GeneratedValue // 1, 2, 3, .. 자동 생성 어노테이션
    private Long id;
    @Column // DB에서 관리하는 table 단위에 연결
    private String title;
    @Column
    private String content;

}
