package com.sbb.qna.answer;

import com.sbb.qna.question.Question;
import com.sbb.qna.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    @PostMapping("/create/{id}")
    public String create(Model model, @PathVariable("id") Integer id, @RequestParam("content") String content) {
        Question question = questionService.getQuestion(id);

        this.answerService.create(question,content);

        return String.format("redirect:/question/detail/%d",id);
    }
}