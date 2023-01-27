package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

//                                         CrudRepository<관리대상 entity, 관리대상 entity 대표 ID의 Type>
public interface ArticleRepository extends CrudRepository<Article, Long> {

    // Iterator type -> ArrayList 로 Override
    @Override
    ArrayList<Article> findAll();
}
