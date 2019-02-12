/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.search.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javaclass.common.YTAttr;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yt.entity.tbl.PackageCityTranslation;
import yt.entity.tbl.PackageCountryTranslation;
import yt.entity.tbl.YTCategoryContent;
import yt.entity.view.KeywordSearchSuggestion;
import yt.func.search.dao.YTCategoryDAO;
import yt.func.search.dao.PackageCityTranslationDAO;
import yt.func.search.dao.PackageCountryTranslationDAO;
import yt.func.search.dao.YTPackageKeywordDAO;
import yt.func.search.dao.YTSubCategoryContentDAO;
import yt.func.search.javaclass.SubCategorySuggestionDTO;

/**
 *
 * @author Hiep
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private PackageCountryTranslationDAO searchPackageCountryTranslationDAO;

    @Autowired
    private PackageCityTranslationDAO searchPackageCityTranslationDAO;

    @Autowired
    private YTSubCategoryContentDAO searchYTSubCategoryContentDAO;

    @Autowired
    private YTCategoryDAO searchYTCategoryDAO;

    @Autowired
    private YTPackageKeywordDAO searchYTPackageKeywordDAO;

    @Override
    @Transactional
    public String loadLocationSuggestion(HttpServletRequest request) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        String kw = dataObject.get("kw").asText();

        List<PackageCountryTranslation> countries = searchPackageCountryTranslationDAO.searchCountryByName(kw);
        List<PackageCityTranslation> cities = searchPackageCityTranslationDAO.searchCityByName(kw);

        List<String[]> lts = new ArrayList<>();
        for (PackageCountryTranslation country : countries) {
            String ctn = country.getCountryName();
            String ctid = country.getCountryID();
            String[] al = {ctn, ctid};
            lts.add(al);
        }
        for (PackageCityTranslation city : cities) {
            String ctn = city.getCityName();
            String ctid = city.getCityID();
            String[] al = {ctn, ctid};
            lts.add(al);
        }

        return mapper.writeValueAsString(lts);
    }

    @Override
    @Transactional
    public String loadCategorySuggestion(HttpServletRequest request) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        String kw = dataObject.get("kw").asText();

        List<YTCategoryContent> cats = searchYTCategoryDAO.getCategoryByName(kw);
        List<String[]> lts = new ArrayList<>();
        for (YTCategoryContent cat : cats) {
            String ctn = cat.getCategoryName();
            String ctid = cat.getCategoryID();
            String[] al = {ctn, ctid};
            lts.add(al);
        }

        return mapper.writeValueAsString(lts);
    }

    @Override
    @Transactional
    public String loadSubCategorySuggestion(HttpServletRequest request) throws IOException {

        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        String kw = dataObject.get("kw").asText();
        String lc = dataObject.get("lc").asText();

        List<SubCategorySuggestionDTO> subs = searchYTSubCategoryContentDAO.searchSubCategoryByName(kw, lc);
        List<String[]> lts = new ArrayList<>();
        for (SubCategorySuggestionDTO asub : subs) {
            String subn = asub.getSubCategoryName();
            String subid = asub.getSubCategoryID();
            String catna = asub.getCategoryName();
            String[] al = {subn, subid, catna};
            lts.add(al);
        }

        return mapper.writeValueAsString(lts);
    }

    @Override
    @Transactional
    public String loadKeywordSuggestion(HttpServletRequest request) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        String kw = dataObject.get("kw").asText();
        String lc = dataObject.get("lc").asText();
        List<KeywordSearchSuggestion> kws = searchYTPackageKeywordDAO.getKeywordSuggest(kw, lc);
        List<String[]> lts = new ArrayList<>();
        for (KeywordSearchSuggestion k : kws) {
            String kword = k.getKeyword();
            String ctn = k.getCategoryName();
            String nfound = String.valueOf(k.getNoFound());
            String[] al = {kword, ctn, nfound};
            lts.add(al);
        }

        return mapper.writeValueAsString(lts);
    }

}
