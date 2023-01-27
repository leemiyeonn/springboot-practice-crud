package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // jpa와 연동한 테스트
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1 : 4번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 4L;
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상하기
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ");
            Comment a = new Comment(1L, article, "Park", "굳 윌 헌팅");
            Comment b = new Comment(2L, article, "Kim", "아이 엠 샘");
            Comment c = new Comment(3L, article, "Choi", "쇼생크의 탈출");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력");
        }

        /* Case 2 : 1번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 1L;
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상하기
            Article article = new Article(1L, "가가가", "111");
            List<Comment> expected = Arrays.asList();
            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }

        /* Case 3 : 9번 게시글의 모든 댓글 조회 */{
            // 입력 데이터 준비
            Long articleId = 9L;
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상하기
            Article article = new Article(articleId, null, null);
            List<Comment> expected = Arrays.asList();
            // 검증
            assertEquals(expected.toString(), comments.toString(), "9번 글이 존재하지 않음");
        }


        /* Case 4 : 999번 게시글의 모든 댓글 조회 */
        /* Case 5 : -1번 게시글의 모든 댓글 조회 */

    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickName() {

        /* Case 1 : park 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            String nickname = "Park";
            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상하기
            Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"), nickname, "굳 윌 헌팅");
            Comment b = new Comment(4L, new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ"), nickname, "치킨");
            Comment c = new Comment(7L, new Article(6L, "당신의 취미는?", "댓글 ㄱㄱㄱ"), nickname, "조깅");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "Park의 모든 댓글");
        }

        /* Case 2 : kim 모든 댓글 조회 */{
            // 입력 데이터 준비
            String nickname = "Kim";
            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상하기 // Comment(댓글 id , 부모 게시물)
            Comment a = new Comment(2L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"), nickname, "아이 엠 샘");
            Comment b = new Comment(5L, new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ"), nickname, "샤브샤브");
            Comment c = new Comment(8L, new Article(6L, "당신의 취미는?", "댓글 ㄱㄱㄱ"), nickname, "유튜브");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "Kim 의 모든 댓글");
        }

        /* Case 3 : null 모든 댓글 조회 */{
            // 입력 데이터 준비
            String nickname = null;
            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상하기 // Comment(댓글 id , 부모 게시물)
            List<Comment> expected = Arrays.asList();
            // 검증
            assertEquals(expected.toString(), comments.toString(), "null 의 모든 댓글");
        }

        /* Case 4 : "" 모든 댓글 조회 */

        /* Case 5 : i가 포함된 모든 댓글 조회 */

            // 입력 데이터 준비
//            SELECT 특성_1, nickname
//            FROM comment
//            WHERE nickname LIKE "%i%"
            // 실제 수행
//            List<Comment> comments = commentRepository.findByNickname(nickname);
//            // 예상하기 // Comment(댓글 id , 부모 게시물)
//            Comment a = new Comment(2L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"), nickname, "아이 엠 샘");
//            Comment b = new Comment(3L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"), nickname, "쇼생크의 탈출");
//            Comment c = new Comment(5L, new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ"), nickname, "샤브샤브");
//            Comment d = new Comment(6L, new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ"), nickname, "초밥");
//            Comment e = new Comment(8L, new Article(6L, "당신의 취미는?", "댓글 ㄱㄱㄱ"), nickname, "유튜브");
//            Comment f = new Comment(9L, new Article(6L, "당신의 취미는?", "댓글 ㄱㄱㄱ"), nickname, "독서");
//            List<Comment> expected = Arrays.asList(a, b, c, d, e, f);
//            // 검증
//            assertEquals(expected.toString(), comments.toString(), "i를 포함한 모든 댓글");

        }

    }
