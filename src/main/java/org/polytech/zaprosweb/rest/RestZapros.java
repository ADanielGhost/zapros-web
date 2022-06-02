package org.polytech.zaprosweb.rest;

import java.util.List;

import org.polytech.zapros.bean.Answer;
import org.polytech.zapros.bean.AnswerCheckResult;
import org.polytech.zaprosweb.exception.UserNotFoundException;
import org.polytech.zaprosweb.service.ZaprosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/zapros")
public class RestZapros {

    @Autowired private ZaprosService zaprosService;

    @ResponseBody
    @RequestMapping(value = "/ask/first/{userId}", method = RequestMethod.GET)
    public AnswerCheckResult askFirst(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return zaprosService.askFirst(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/add/answer/{userId}/{answerType}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AnswerCheckResult addAnswer(@PathVariable("userId") Long userId,
                                       @PathVariable("answerType") Answer.AnswerType answerType,
                                       @RequestBody AnswerCheckResult answerCheckResult) throws UserNotFoundException {
        return zaprosService.addAnswer(userId, answerCheckResult, answerType);
    }

    @ResponseBody
    @RequestMapping(value = "/send/answers/{userId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sendAnswers(@PathVariable("userId") Long userId,
                            @RequestBody List<Answer> answers) throws UserNotFoundException {
        zaprosService.sendAnswers(userId, answers);
    }
}
