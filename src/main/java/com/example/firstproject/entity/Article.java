package com.example.firstproject.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity // DB가 해당 객체를 인식 가능! (해당 클래스로 테이블을 만든다)
@AllArgsConstructor // lombok - 생성자
@NoArgsConstructor // 디폴트 생성자 (Article(){} 파라메터가 없는 생성자)
@ToString // lombok
@Getter // lombok
public class Article {
    @Id // 대표값을 지정! like a 주민등록번호 // entity에 기본적으로 있어야함
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 ID 자동 생성
    private Long id;
    @Column // DB에서 관리하는 table 단위에 연결
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
    }

}
