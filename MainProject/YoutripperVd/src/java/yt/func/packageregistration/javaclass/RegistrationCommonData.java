/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.javaclass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javaclass.common.YTData;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 *
 * @author nickn
 */
@Service
public class RegistrationCommonData {

    private static MessageSource MESSAGE_SOURCE;

    @Autowired
    private MessageSource msgSource;

    private static Map<String, List<String[]>> DESCRIPTION_LANGUAGE_HM;
    private static Map<String, List<String[]>> SERVING_LANGUAGE_HM;

    private static final String SERVING_LANGUAGE_ENGLISH = "en";
    private static final String SERVING_LANGUAGE_THAI = "th";
    private static final String SERVING_LANGUAGE_JAPANESE = "ja";
    private static final String SERVING_LANGUAGE_CHINESE = "zh";
    private static final String SERVING_LANGUAGE_KOREAN = "ko";
    private static final String SERVING_LANGUAGE_RUSSIAN = "ru";

    public static final int CSS_COVER_WIDTH = 600;
    public static final int CSS_COVER_HEIGHT = 185;
    public static final int CSS_MAIN_THUMB_WIDTH = 260;
    public static final int CSS_MAIN_THUMB_HEIGHT = 390;

    @PostConstruct
    private void initMessageSource() {
        MESSAGE_SOURCE = this.msgSource;

        Map<String, List<String[]>> descriptionLanguageHM = new HashMap<>();

        List<String[]> languagesInEn = new ArrayList<>();
        String enNameInEn = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.lanTab.en", new String[]{}, Locale.ENGLISH);
        String thNameInEn = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.lanTab.th", new String[]{}, Locale.ENGLISH);
        String[] enLanguageInEn = new String[]{YTData.LOCALE_ENGLISH_CODE, enNameInEn};
        String[] thLanguageInEn = new String[]{YTData.LOCALE_THAI_CODE, thNameInEn};
        languagesInEn.add(enLanguageInEn);
        languagesInEn.add(thLanguageInEn);

        List<String[]> languagesInTh = new ArrayList<>();
        Locale thLocale = new Locale(YTData.LOCALE_THAI_CODE);
        String enNameInTH = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.lanTab.en", new String[]{}, thLocale);
        String thNameInTH = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.lanTab.th", new String[]{}, thLocale);
        String[] enLanguageInTH = new String[]{YTData.LOCALE_ENGLISH_CODE, enNameInTH};
        String[] thLanguageInTH = new String[]{YTData.LOCALE_THAI_CODE, thNameInTH};
        languagesInTh.add(enLanguageInTH);
        languagesInTh.add(thLanguageInTH);

        descriptionLanguageHM.put(YTData.LOCALE_ENGLISH_CODE, languagesInEn);
        descriptionLanguageHM.put(YTData.LOCALE_THAI_CODE, languagesInTh);

        DESCRIPTION_LANGUAGE_HM = Collections.unmodifiableMap(descriptionLanguageHM);

        Map<String, List<String[]>> servingLanguageHM = new HashMap<>();

        List<String[]> servingLanguagesInEn = new ArrayList<>();
        String enServingInEn = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.servingLanguage.en", new String[]{}, Locale.ENGLISH);
        String thServingInEn = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.servingLanguage.th", new String[]{}, Locale.ENGLISH);
        String jaServingInEn = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.servingLanguage.ja", new String[]{}, Locale.ENGLISH);
        String cnServingInEn = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.servingLanguage.cn", new String[]{}, Locale.ENGLISH);
        String koServingInEn = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.servingLanguage.ko", new String[]{}, Locale.ENGLISH);
        String ruServingInEn = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.servingLanguage.ru", new String[]{}, Locale.ENGLISH);
        String[] enSerInEn = new String[]{SERVING_LANGUAGE_ENGLISH, enServingInEn};
        String[] thSerInEn = new String[]{SERVING_LANGUAGE_THAI, thServingInEn};
        String[] jaSerInEn = new String[]{SERVING_LANGUAGE_JAPANESE, jaServingInEn};
        String[] zhSerInEn = new String[]{SERVING_LANGUAGE_CHINESE, cnServingInEn};
        String[] koSerInEn = new String[]{SERVING_LANGUAGE_KOREAN, koServingInEn};
        String[] ruSerInEn = new String[]{SERVING_LANGUAGE_RUSSIAN, ruServingInEn};
        servingLanguagesInEn.add(enSerInEn);
        servingLanguagesInEn.add(thSerInEn);
        servingLanguagesInEn.add(jaSerInEn);
        servingLanguagesInEn.add(zhSerInEn);
        servingLanguagesInEn.add(koSerInEn);
        servingLanguagesInEn.add(ruSerInEn);

        List<String[]> servingLanguagesInTh = new ArrayList<>();
        String enServingInTh = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.servingLanguage.en", new String[]{}, thLocale);
        String thServingInTh = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.servingLanguage.th", new String[]{}, thLocale);
        String jaServingInTh = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.servingLanguage.ja", new String[]{}, thLocale);
        String cnServingInTh = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.servingLanguage.cn", new String[]{}, thLocale);
        String koServingInTh = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.servingLanguage.ko", new String[]{}, thLocale);
        String ruServingInTh = MESSAGE_SOURCE.getMessage("yt.pkgEditing.descriptionStep.servingLanguage.ru", new String[]{}, thLocale);
        String[] enSerInTH = new String[]{SERVING_LANGUAGE_ENGLISH, enServingInTh};
        String[] thSerInTH = new String[]{SERVING_LANGUAGE_THAI, thServingInTh};
        String[] jaSerInTH = new String[]{SERVING_LANGUAGE_JAPANESE, jaServingInTh};
        String[] zhSerInTH = new String[]{SERVING_LANGUAGE_CHINESE, cnServingInTh};
        String[] koSerInTH = new String[]{SERVING_LANGUAGE_KOREAN, koServingInTh};
        String[] ruSerInTH = new String[]{SERVING_LANGUAGE_RUSSIAN, ruServingInTh};
        servingLanguagesInTh.add(enSerInTH);
        servingLanguagesInTh.add(thSerInTH);
        servingLanguagesInTh.add(jaSerInTH);
        servingLanguagesInTh.add(zhSerInTH);
        servingLanguagesInTh.add(koSerInTH);
        servingLanguagesInTh.add(ruSerInTH);

        servingLanguageHM.put(YTData.LOCALE_ENGLISH_CODE, servingLanguagesInEn);
        servingLanguageHM.put(YTData.LOCALE_THAI_CODE, servingLanguagesInTh);

        SERVING_LANGUAGE_HM = Collections.unmodifiableMap(servingLanguageHM);

    }

    public static List<String[]> getDescriptionLanguages(String locale) {
        List<String[]> languages = DESCRIPTION_LANGUAGE_HM.get(locale);
        return new ArrayList<>(languages);
    }

    public static List<String[]> getServingLanguages(String locale) {
        List<String[]> languages = SERVING_LANGUAGE_HM.get(locale);
        return new ArrayList<>(languages);
    }
}
