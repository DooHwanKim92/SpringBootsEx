package com.sbb.qna_ex_1.answer;

import com.sbb.qna_ex_1.question.Question;
import com.sbb.qna_ex_1.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    public void create(Question question, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setContent(content);
        answer.setAuthor(author);
        answer.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
}
