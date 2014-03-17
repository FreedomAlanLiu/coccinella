package org.daybreak.coccinella.app.admin;

import org.daybreak.coccinella.app.BaseController;
import org.daybreak.coccinella.domain.model.AIC;
import org.daybreak.coccinella.domain.model.Crawler;
import org.daybreak.coccinella.domain.model.Enterprise;
import org.daybreak.coccinella.domain.model.Parser;
import org.daybreak.coccinella.domain.service.AicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Alan on 14-3-14.
 */
@Controller
@RequestMapping("/admin/aics")
public class AicController extends BaseController {

    @Inject
    private AicService aicService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("aics", aicService.loadAics());
        return "admin/aics/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String _new(Model model) {
        model.addAttribute("aic", new AIC());
        return "admin/aics/new";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid AIC aic, Model model) {
        AIC newAic = aicService.saveAic(aic);
        model.addAttribute("aic", newAic);
        return "admin/aics/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("aic", aicService.loadAic(id));
        return "admin/aics/view";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("aic", aicService.loadAic(id));
        return "admin/aics/edit";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public String update(@PathVariable("id") long id, @Valid AIC aic, Model model) {
        AIC newAic = aicService.loadAic(id);
        newAic.setName(aic.getName());
        newAic.setProvince(aic.getProvince());
        newAic.setWebsite(aic.getWebsite());
        newAic.setMaxFrequency(aic.getMaxFrequency());
        model.addAttribute("aic", aicService.saveAic(newAic));
        return "admin/aics/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") long id, Model model) {
        aicService.deleteAic(id);
        model.addAttribute("aics", aicService.loadAics());
        return "admin/aics/index";
    }

    @RequestMapping(value = "/{aicId}/crawlers/new", method = RequestMethod.GET)
    public String newCrawler(@PathVariable long aicId, Model model) {
        model.addAttribute("aic", aicService.loadAic(aicId));
        model.addAttribute("crawler", new Crawler());
        return "admin/aics/crawlers/new";
    }

    @RequestMapping(value = "/{aicId}/crawlers/{crawlerId}", method = RequestMethod.GET)
    public String showCrawler(@PathVariable("aicId") long aicId, @PathVariable("crawlerId") long crawlerId, Model model) {
        Crawler crawler = aicService.loadCrawler(crawlerId);
        model.addAttribute("aic", crawler.getAic());
        model.addAttribute("crawler", crawler);
        return "admin/aics/crawlers/view";
    }

    @RequestMapping(value = "/{aicId}/crawlers", method = RequestMethod.POST)
    public String createCrawler(@PathVariable long aicId, @Valid Crawler crawler, Model model) {
        AIC aic = aicService.loadAic(aicId);
        crawler.setAic(aic);
        aic.getCrawlers().add(crawler);
        crawler = aicService.saveCrawler(crawler);

        Parser parser1 = new Parser();
        parser1.setCrawler(crawler);
        parser1.setNameKey(Enterprise.NAME_KEY);
        crawler.getParsers().add(parser1);
        Parser parser2 = new Parser();
        parser2.setNameKey(Enterprise.ADDRESS_KEY);
        parser2.setCrawler(crawler);
        crawler.getParsers().add(parser2);
        Parser parser3 = new Parser();
        parser3.setNameKey(Enterprise.AGENT_KEY);
        parser3.setCrawler(crawler);
        crawler.getParsers().add(parser3);
        Parser parser4 = new Parser();
        parser4.setCrawler(crawler);
        parser4.setNameKey(Enterprise.CATEGORY_KEY);
        crawler.getParsers().add(parser4);
        Parser parser5 = new Parser();
        parser5.setCrawler(crawler);
        parser5.setNameKey(Enterprise.SCOPE_KEY);
        crawler.getParsers().add(parser5);
        Parser parser6 = new Parser();
        parser6.setCrawler(crawler);
        parser6.setNameKey(Enterprise.TYPE_KEY);
        crawler.getParsers().add(parser6);
        Parser parser7 = new Parser();
        parser7.setCrawler(crawler);
        parser7.setNameKey(Enterprise.APPROVAL_DATE_KEY);
        crawler.getParsers().add(parser7);
        Parser parser8 = new Parser();
        parser8.setNameKey(Enterprise.CURRENT_STATUS_KEY);
        parser8.setCrawler(crawler);
        crawler.getParsers().add(parser8);
        Parser parser9 = new Parser();
        parser9.setNameKey(Enterprise.ESTABLISHMENT_DATE_KEY);
        parser9.setCrawler(crawler);
        crawler.getParsers().add(parser9);
        Parser parser10 = new Parser();
        parser10.setNameKey(Enterprise.POSTAL_CODE_KEY);
        parser10.setCrawler(crawler);
        crawler.getParsers().add(parser10);
        Parser parser11 = new Parser();
        parser11.setNameKey(Enterprise.REGISTERED_CAPITAL_KEY);
        parser11.setCrawler(crawler);
        crawler.getParsers().add(parser11);
        Parser parser12 = new Parser();
        parser12.setNameKey(Enterprise.REGISTRATION_AUTHORITY_KEY);
        parser12.setCrawler(crawler);
        crawler.getParsers().add(parser12);
        Parser parser13 = new Parser();
        parser13.setCrawler(crawler);
        parser13.setNameKey(Enterprise.REGISTRATION_NUMBER_KEY);
        crawler.getParsers().add(parser13);

        aicService.saveCrawler(crawler);

        model.addAttribute("aic", crawler.getAic());
        return "admin/aics/view";
    }

    @RequestMapping(value = "/{aicId}/crawlers/{crawlerId}/edit", method = RequestMethod.GET)
    public String editCrawler(@PathVariable("aicId") long aicId, @PathVariable("crawlerId") long crawlerId, Model model) {
        Crawler crawler = aicService.loadCrawler(crawlerId);
        model.addAttribute("aic", crawler.getAic());
        model.addAttribute("crawler", crawler);
        return "admin/aics/crawlers/edit";
    }

    @RequestMapping(value = "/{aicId}/crawlers/{crawlerId}", method = RequestMethod.DELETE)
    public String deleteCrawler(@PathVariable("aicId") long aicId, @PathVariable("crawlerId") long crawlerId, Model model) {
        Crawler crawler = aicService.loadCrawler(crawlerId);
        AIC aic = crawler.getAic();
        aic.getCrawlers().remove(crawler);
        model.addAttribute("aic", aicService.saveAic(aic));
        return "admin/aics/view";
    }

    @RequestMapping(value = "/{aicId}/crawlers/{crawlerId}", method = RequestMethod.PATCH)
    public String updateCrawler(@PathVariable("aicId") long aicId, @PathVariable("crawlerId") long crawlerId, @Valid Crawler crawler, Model model) {
        Crawler pCrawler = aicService.loadCrawler(crawlerId);
        pCrawler.getParsers().clear();
        pCrawler = aicService.saveCrawler(pCrawler);
        pCrawler.setName(crawler.getName());
        pCrawler.setUrl(crawler.getUrl());
        pCrawler.setMethod(crawler.getMethod());
        pCrawler.setParams(crawler.getParams());
        pCrawler.setReferer(crawler.getReferer());
        List<Parser> parsers = crawler.getParsers();
        for (Parser parser : parsers) {
            parser.setCrawler(pCrawler);
        }
        pCrawler.getParsers().addAll(parsers);
        pCrawler = aicService.saveCrawler(pCrawler);
        model.addAttribute("aic", pCrawler.getAic());
        model.addAttribute("crawler", pCrawler);
        return "admin/aics/crawlers/view";
    }

    @RequestMapping(value = "/{aicId}/crawlers/{crawlerId}/parsers/new", method = RequestMethod.GET)
    public String newParser(@PathVariable("aicId") long aicId, @PathVariable("crawlerId") long crawlerId, Model model) {
        Crawler crawler = aicService.loadCrawler(crawlerId);
        crawler= aicService.saveCrawler(crawler);
        Parser parser = new Parser();
        parser.setCrawler(crawler);
        crawler.getParsers().add(parser);
        crawler = aicService.saveCrawler(crawler);
        model.addAttribute("aic", crawler.getAic());
        model.addAttribute("crawler", crawler);
        return "redirect:/admin/aics/{aicId}/crawlers/{crawlerId}/edit";
    }

    @RequestMapping(value = "/{aicId}/crawlers/{crawlerId}/parsers/{parserId}/delete", method = RequestMethod.GET)
    public String deleteParser(@PathVariable("aicId") long aicId, @PathVariable("crawlerId") long crawlerId,
                            @PathVariable("parserId") long parserId, Model model, HttpServletResponse response) {

        Parser parser = aicService.loadParser(parserId);
        Crawler crawler = parser.getCrawler();
        crawler.getParsers().remove(parser);
        aicService.saveCrawler(crawler);

        model.addAttribute("aic", crawler.getAic());
        model.addAttribute("crawler", crawler);
        return "redirect:/admin/aics/{aicId}/crawlers/{crawlerId}/edit";
    }
}
