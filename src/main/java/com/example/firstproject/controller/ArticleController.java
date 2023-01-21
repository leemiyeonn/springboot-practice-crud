package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // 로깅 어노테이션
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결! // new 생성자 사용 X
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new") // url 입력 받아오는 어노테이션
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
        //System.out.println(form.toString()); -> 로깅기능으로 대체

        // 1. dto를 Entity로 변환
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB안에 저장하게 함!
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);

        // 1. id로 데이터를 가져옴
       Article articleEntity = articleRepository.findById(id).orElse(null);
        //                     articleRepositoty 반환값 = Optional<Article>

        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 article을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();

        // 2. 가져온 article 묶음을 view로 전달한다
        model.addAttribute("articleList", articleEntityList);
        // 3. 뷰 페이지를 설정한다
        return "articles/index"; //articles/index.mustache
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터를 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //모델에 데이터 등록
        model.addAttribute("article", articleEntity);
        // 뷰페이지 설정
        return "articles/edit";
    }
}