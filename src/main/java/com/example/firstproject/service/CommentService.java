package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
//        // 조회 : 댓글 목록
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        // 변환 : 엔티티 -> Dto (컨트롤러에서 commentDto로 반환하기로 함)
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for (int i = 0; i < comments.size(); i++) {
//            Comment comment = comments.get(i); // 엔티티
//            CommentDto dto = CommentDto.createCommentDto(comment); // 디티오
//            dtos.add(dto);
//        }
        // 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional // DB 데이터에 변경이 일어 날 수 있기 때문에 트랜젝션 처리 해야함 - rollback
    public CommentDto create(Long articleId, CommentDto dto) {
        //log.info("입력값 => {}", articleId);
        //log.info("입력값 => {}", dto);
        // !게시글! 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        // 댓글 엔티티를 db로 저장
        Comment created = commentRepository.save(comment);

        // dto로 변경하여 반환
        return CommentDto.createCommentDto(created);

        //CommentDto createdDto = CommentDto.createCommentDto(created);
        //log.info("반환값 => {}", createdDto);
        //return createdDto;
    }

    @Transactional // DB 데이터에 변경이 일어 날 수 있기 때문에 트랜젝션 처리 해야함 - rollback
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        // 댓글 수정
        target.patch(dto);

        // db로 갱신
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티를 dto로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional // DB 데이터에 변경이 일어 날 수 있기 때문에 트랜젝션 처리 해야함 - rollback
    public CommentDto delete(Long id) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상 댓글이 없습니다."));

        // 댓글 db에서 삭제
        commentRepository.delete(target);

        // 삭제 댓글을 dto로 반환
        return CommentDto.createCommentDto(target);
    }
}


