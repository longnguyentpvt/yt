/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.search;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yt.func.search.service.SearchService;

/**
 *
 * @author Hiep
 */
@Controller
@RequestMapping(value = {"", "/{locale}"})
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = SearchViewMapping.LOCATION_SEARCH_SUGGESTION_URL, method = RequestMethod.POST)
    @ResponseBody
    public String loadLocationSuggestion(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        return searchService.loadLocationSuggestion(request);
    }

    @RequestMapping(value = SearchViewMapping.CATEGORY_SEARCH_SUGGESTION_URL, method = RequestMethod.POST)
    @ResponseBody
    public String loadCategorySuggestion(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        return searchService.loadCategorySuggestion(request);
    }

    @RequestMapping(value = SearchViewMapping.SUB_CATEGORY_SEARCH_SUGGESTION_URL, method = RequestMethod.POST)
    @ResponseBody
    public String loadSubCategorySuggestion(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        return searchService.loadSubCategorySuggestion(request);
    }

    @RequestMapping(value = SearchViewMapping.KEYWORD_SEARCH_SUGGESTION_URL, method = RequestMethod.POST)
    @ResponseBody
    public String loadKeywordSuggestion(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        return searchService.loadKeywordSuggestion(request);
    }
}
