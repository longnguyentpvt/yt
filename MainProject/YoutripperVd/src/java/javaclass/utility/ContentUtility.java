/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.utility;

/**
 *
 * @author nickn
 */
public class ContentUtility {

    /**
     * is used to get content for text (not inside "" or '') as HTML elements
     *
     * @param content string needs to be refined
     * @return string of refined content
     */
    public static String refineContentAsTextHTML(String content) {
        return content.replaceAll("<", "&lt;");
    }

    /**
     * is used to get content for text used to put inside "" or '' in jsp
     * <p>
     * for ex.
     * <p>
     * var text = 'content' or var text = "content" or class='content'
     *
     * @param content string needs to be refined
     * @return string of refined content
     */
    public static String refineContentAsStringInJSP(String content) {
        return content.replaceAll("\"", "\\\\\"").replaceAll("'", "\\\\'").replaceAll("/", "\\\\/");
    }

    /**
     * is used to get content for json text in javascript in jsp
     * <p>
     * for ex.
     * <p>
     * var text = json;
     *
     * @param content string needs to be refined
     * @return string of refined content
     */
    public static String refineContentAsJsonInJavaSript(String content) {
        return content.replaceAll("/", "\\\\/");
    }
}
