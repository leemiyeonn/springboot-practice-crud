package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor // h2 console - api 데이터 받기
@ToString
@Setter // h2 console - api 데이터 내용 받기
public class ArticleForm {

    private Long id; // id 필드 추가
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
    // dto (로직이 없는, 필드만 가지고 있는 단위),
    // dto -> entity (dto를 사용할 수 있는 새로운 객체로 변환)
}
