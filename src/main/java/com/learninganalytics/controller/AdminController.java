package com.learninganalytics.controller;

import com.learninganalytics.domain.Pager;
import com.learninganalytics.model.LabelSentence;
import com.learninganalytics.model.Rater;
import com.learninganalytics.model.Sentence;
import com.learninganalytics.service.LabelSentenceService;
import com.learninganalytics.service.RaterService;
import com.learninganalytics.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Luthfi on 05/04/2017.
 */
@Controller
public class AdminController {
    @Autowired
    RaterService raterService;

    @Autowired
    SentenceService sentenceService;

    @Autowired
    LabelSentenceService labelSentenceService;

    @RequestMapping(value = "/admin/view", method = RequestMethod.GET)
    public ModelAndView view(@PageableDefault(value = 15) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Rater rater = raterService.findRater(auth.getName());

        Page<Sentence> sentencePage = sentenceService.findPaginateSentence(pageable, true);
        List<Sentence> sentences = sentencePage.getContent();
        Pager pager = new Pager(sentencePage.getTotalPages(), sentencePage.getNumber(), 5);

        modelAndView.addObject("sentences", sentences);
        modelAndView.addObject("rater", rater);
        modelAndView.addObject("sentencePage", sentencePage);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("admin/view");
        return modelAndView;
    }
}
