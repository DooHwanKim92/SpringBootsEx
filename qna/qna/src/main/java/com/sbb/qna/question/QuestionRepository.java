package com.sbb.qna.question;

import com.sbb.qna.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Question findBySubject(String s);

    Question findByContent(String c);

    Question findBySubjectAndContent(String s, String c);

    List<Question> findBySubjectLike(String s);

}
