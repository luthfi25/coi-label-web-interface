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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Luthfi on 17/03/2017.
 */

@Controller
public class LoginController {
    @Autowired
    private RaterService raterService;

    @Autowired
    private SentenceService sentenceService;

    @Autowired
    private LabelSentenceService labelSentenceService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {

            /* The user is logged in :) */
            return new ModelAndView("redirect:/home");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        Rater rater = new Rater();
        modelAndView.addObject("rater", rater);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewRater(@Valid Rater rater, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        Rater raterExists = raterService.findRater(rater.getId());
        if (raterExists != null){
            bindingResult.rejectValue("id", "error.rater", "There is already a user registered with that particular nickname");
        }
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("registration");
        } else {
            raterService.saveRater(rater);
            modelAndView.addObject("successMessage", "Rater has been registered successfully");
            modelAndView.addObject("rater", new Rater());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(@PageableDefault(value = 15) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Rater rater = raterService.findRater(auth.getName());
        Page<Sentence> sentencePage = sentenceService.findPaginateSentence(pageable);
        List<Sentence> sentences = sentencePage.getContent();
        Pager pager = new Pager(sentencePage.getTotalPages(), sentencePage.getNumber(), 5);
        List<LabelSentence> labelSentence = labelSentenceService.findLabelSentencebyIdRater(rater.getId());
        HashMap<Sentence, LabelSentence> sentenceToLabel = new HashMap<>();

        for ( Sentence s : sentences) {
            List<LabelSentence> ls = s.getLabelSentence();

            if (ls.size() > 0){
                List<LabelSentence> new_ls = ls.stream().filter(label -> labelSentence.contains(label)).collect(Collectors.toList());
                if(new_ls.size() > 0){
                    sentenceToLabel.put(s, new_ls.get(0));
                }
            }
        }

        modelAndView.addObject("sentences", sentences);
        modelAndView.addObject("label", sentenceToLabel);
        modelAndView.addObject("rater", rater);
        modelAndView.addObject("labelSentence", new LabelSentence());
        modelAndView.addObject("sentencePage", sentencePage);
        modelAndView.addObject("pager", pager);

        modelAndView.setViewName("home");
        return modelAndView;
    }
}
