package com.learninganalytics.controller;

import com.learninganalytics.domain.Pager;
import com.learninganalytics.model.LabelPost;
import com.learninganalytics.model.LabelSentence;
import com.learninganalytics.model.Rater;
import com.learninganalytics.model.Sentence;
import com.learninganalytics.service.LabelPostService;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Luthfi on 05/04/2017.
 */
@Controller
public class AdminController {
    @Autowired
    private RaterService raterService;

    @Autowired
    private SentenceService sentenceService;

    @Autowired
    private LabelSentenceService labelSentenceService;

    @Autowired
    private LabelPostService labelPostService;

    @RequestMapping(value = "/admin/view", method = RequestMethod.GET)
    public ModelAndView view(@PageableDefault(value = 15) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Rater rater = raterService.findRater(auth.getName());

        Page<Sentence> sentencePage = sentenceService.findPaginateSentence(pageable);
        List<Sentence> sentences = sentencePage.getContent();
        Pager pager = new Pager(sentencePage.getTotalPages(), sentencePage.getNumber(), 5);

        List<LabelPost> posts = labelPostService.findAllLabelPosts();
        HashMap<Sentence, List<LabelPost>> postLabel = new HashMap<>();

        for (Sentence s: sentences) {
            List<LabelPost> lp = posts.stream().filter(post -> post.getId_post() == s.getPost_id()).collect(Collectors.toList());
            postLabel.put(s, lp);
        }

        modelAndView.addObject("sentences", sentences);
        modelAndView.addObject("rater", rater);
        modelAndView.addObject("sentencePage", sentencePage);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("postLabel", postLabel);
        modelAndView.setViewName("admin/view");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/manage-rater", method = RequestMethod.GET)
    public ModelAndView manageRater(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Rater rater = raterService.findRater(auth.getName());
        List<Rater> allRater = raterService.findAllRater();

        modelAndView.addObject("rater", rater);
        modelAndView.addObject("allRater", allRater);
        modelAndView.setViewName("admin/manage-rater");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/delete-rater", method = RequestMethod.POST)
    public ModelAndView deleteRater(@RequestBody Rater rater){
        List<LabelSentence> labelSentences = labelSentenceService.findLabelSentencebyIdRater(rater.getId());
        for (LabelSentence ls : labelSentences) {
            labelSentenceService.deleteLabelSentence(ls);
        }
        raterService.deleteRater(rater);
        return new ModelAndView("redirect:/admin/manage-rater");
    }
}
