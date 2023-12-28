package com.sbb.qna;

import com.sbb.qna.answer.Answer;
import com.sbb.qna.answer.AnswerRepository;
import com.sbb.qna.question.Question;
import com.sbb.qna.question.QuestionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class QnaApplicationTests {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void save() {
        Question q1 = new Question();
        q1.setSubject("저메추");
        q1.setContent("저녁 메뉴 추천받습니다~");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);  // 첫번째 질문 저장

        Question q2 = new Question();
        q2.setSubject("wtf");
        q2.setContent("what the hell is going on?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);  // 두번째 질문 저장
    }

    @Test
    void findAll() {
        List<Question> all = this.questionRepository.findAll();
        assertEquals(4, all.size());

        Question q = all.get(0);
        assertEquals("test1", q.getSubject());

        System.out.println(q.getSubject() + " / " + q.getContent());
    }

    @Test
    void findById() {
        Optional<Question> oq = this.questionRepository.findById(3);
        if (oq.isPresent()) {
            Question q = oq.get();
            assertEquals("test3", q.getSubject());
            System.out.println(q.getSubject() + " / " + q.getContent());
        }
    }

    @Test
    void findBySubject() {
        Question q = this.questionRepository.findBySubject("test4");
        assertEquals("test4", q.getSubject());

        System.out.println("제목으로 검색 : test4");
        System.out.println("질문제목 : " + q.getSubject() + " / 질문내용 : " + q.getContent());
    }

    @Test
    void test04() {
        Question q = this.questionRepository.findBySubject("test3");
        assertEquals("test3", q.getSubject());
    }

    @Test
    void test05() {
        Question q = this.questionRepository.findByContent("testContent1");
        assertEquals("testContent1", q.getContent());
    }

    @Test
    void test06() {
        Question q = this.questionRepository.findBySubjectAndContent("test1", "testContent1");
        assertEquals("test1", q.getSubject());
        assertEquals("testContent1", q.getContent());
    }

    @Test
    void test07() {
        List<Question> questionList = this.questionRepository.findBySubjectLike("te%");
        assertEquals(4, questionList.size());
    }

    @Test
    void modifySubjectById() {
        Optional<Question> q = this.questionRepository.findById(2);
        assertTrue(q.isPresent());
        Question q1 = q.get();
        q1.setSubject("modifySubject2");
        this.questionRepository.save(q1);
    }

    @Test
    void modifyContentById() {
        Optional<Question> q = this.questionRepository.findById(4);
        assertTrue(q.isPresent());
        Question q1 = q.get();
        q1.setContent("modifyContent4");
        this.questionRepository.save(q1);
    }

    @Test
    void removeQuestionById() {
        Optional<Question> q = this.questionRepository.findById(1);
        assertTrue(q.isPresent());
        Question q1 = q.get();
        this.questionRepository.delete(q1);
    }

    @Test
    void removeQuestionBySubject() {
        Question q = this.questionRepository.findBySubject("test3");
        assertEquals("test3", q.getSubject());
        this.questionRepository.delete(q);
    }

    @Test
    void answerCreateTest() {
        Optional<Question> q = this.questionRepository.findById(2);

        assertTrue(q.isPresent());
        Question q1 = q.get();

        Answer answer = new Answer();
        answer.setContent("Answer4");
        answer.setQuestion(q1);
        answer.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    @Test
    void answerReadTest() {
        List<Answer> answerList = this.answerRepository.findAll();
        for(Answer answer : answerList) {
            System.out.println(answer.getId() +" / "+ answer.getContent() +" / "+ answer.getCreateDate() + " / " + answer.getQuestion().getId());
        }
    }

    @Test
    void answerModifyTest() {
        Optional<Answer> a = this.answerRepository.findById(4);
        assertTrue(a.isPresent());
        Answer a1 = a.get();
        a1.setContent("modifiedAnswer2");
        this.answerRepository.save(a1);
    }
    @Test
    void answerRemoveTest() {
        Optional<Answer> a = this.answerRepository.findById(2);
        assertTrue(a.isPresent());
        Answer a1 = a.get();
        this.answerRepository.delete(a1);
    }

    @Transactional
    @Test
     void testJpa() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
    }

}
