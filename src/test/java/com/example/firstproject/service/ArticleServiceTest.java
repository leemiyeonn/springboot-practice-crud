package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 사용한다
class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Test
    void index() {
        // 예상
        Article a = new Article (1L,"가가가", "111");
        Article b = new Article (2L,"나나나", "222");
        Article c = new Article (3L,"다다다", "333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        // 실제
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공__존재하는_id_입력() {
        // 예상
        Long id = 1L;
        Article expected = new Article(id, "가가가", "111");
        // 실제
        Article article = articleService.show(id);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패__존재하지_않는_id_입력(){
        // 예상
        Long id = -1L;
        Article expected = null;
        // 실제
        Article article = articleService.show(id);
        // 비교
        assertEquals(expected, article);
        // null - toString 불가
    }

    @Test
    @Transactional
    void create_성공__title과_content만_있는_dto_입력() {
        // 예상
        String title = "라라라";
        String content = "777";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(7L, title, content);
        // 실제
        Article article = articleService.create(dto);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_실패__id가_포함된_dto_입력() {
        // 예상
        Long id = 7L;
        String title = "라라라";
        String content = "777";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        // 실제
        Article article = articleService.create(dto);
        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공__존재하는_id_title_content_있는_dto_입력() {
        // 예상
        Long id = 1L;
        String title = "가가가가";
        String content = "1111";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);
        // 실제
        Article article = articleService.update(id, dto);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_성공__존재하는_id_title만_있는_dto_입력() {
        Long id = 1L;
        String title = "가가가가";
        String content = null;
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, "111");
        // 실제
        Article article = articleService.update(id, dto);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_성공__존재하는_id_content만_있는_dto_입력() {
        Long id = 1L;
        String title = null;
        String content = "1111";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, "가가가", content);
        // 실제
        Article article = articleService.update(id, dto);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void update_실패__존재하지_않는_id의_dto_입력() {
        // 예상
        Long id = 100L;
        ArticleForm dto = new ArticleForm(id, null, null);
        Article expected = null;
        // 실제
        Article article = articleService.update(id, dto);
        // 비교
        assertEquals(expected, article);

    }

    @Test
    void update_실패__id만_있는_dto_입력() {
        // 예상
        Long id = 1L;
        ArticleForm dto = new ArticleForm(id, null, null);
        Article expected = null;
        // 실제
        Article article = articleService.update(id, dto);
        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void delete_성공__존재하는_id_입력() {
        // 예상
        Long id = 1L;
        Article expected = new Article(id, "가가가", "111");
        // 실제
        Article article = articleService.delete(id);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void delete_실패__존재하지않는_id_입력() {
        // 예상
        Long id = 100L;
        Article expected = null;
        // 실제
        Article article = articleService.delete(id);
        // 비교
        assertEquals(expected, article);
    }


}