/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.search.service;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Hiep
 */
public interface SearchService {

    public String loadLocationSuggestion(HttpServletRequest request) throws IOException;

    public String loadCategorySuggestion(HttpServletRequest request) throws IOException;
    
    public String loadSubCategorySuggestion(HttpServletRequest request) throws IOException;
    
    public String loadKeywordSuggestion(HttpServletRequest request) throws IOException;
}
