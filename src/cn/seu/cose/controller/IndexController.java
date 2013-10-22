package cn.seu.cose.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.entity.ArticlePojo;
import cn.seu.cose.entity.PublicationPojo;
import cn.seu.cose.entity.SlidePojo;
import cn.seu.cose.service.ArticleService;
import cn.seu.cose.service.PublicationService;
import cn.seu.cose.service.SlideService;

@Controller
public class IndexController extends AbstractController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private PublicationService publicationService;

	@RequestMapping("/")
	public String index(Model model) {
		List<SlidePojo> slides = slideService.getSlides();
		List<ArticlePojo> news = articleService.newCenterInIndex();
		List<PublicationPojo> recentPublications = publicationService
				.getRecentPublications();
		model.addAttribute("slides", slides);
		model.addAttribute("news", news);
		model.addAttribute("recentPublications", recentPublications);
		addCategories(model);
		return "index";
	}
}
