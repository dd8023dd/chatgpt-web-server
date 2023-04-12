package com.home.chat.utils;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Validator;
import cn.hutool.dfa.WordTree;
import cn.hutool.http.HtmlUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Deacription TODO
 * @Author Zhangmj
 * @Date 2023/4/11 10:46
 * @Version 1.0
 **/
@Slf4j
public class SensitiveWordsUtils {

    // 敏感词、违禁词 Map
    private static final WordTree SENSITIVEWORDS = new WordTree();

    /**
     * 加载敏感词、违禁词
     */
    public static void loadBadwords() throws IOException {
        String path = "sensitivewords.dic";
        ClassPathResource resource = new ClassPathResource(path);
        InputStream inputStream = resource.getStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while((line = br.readLine())!=null) {
            SENSITIVEWORDS.addWord(line.trim());
        }
        br.close();
    }

    /**
     * 敏感词，违禁词检测
     */
    public static List<String> checkBadwords(String content) {
        if(SENSITIVEWORDS.size() == 0) {
            try {
                loadBadwords();
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }

        // 匹配到最长关键词，跳过已经匹配的关键词
        String sentence = HtmlUtil.cleanHtmlTag(content);
        List<String> words = SENSITIVEWORDS.matchAll(sentence, -1, false, false);

        List<String> newWords = new ArrayList<>();
        // 过滤该词在某个单词里面
        for(String word : words) {
            if(Validator.isWord(word) && !content.contains(" " + word) && !content.contains(word + " ")) {
                continue;
            }
            newWords.add(word);
            log.info("触发敏感词：{}",word);
        }
        return newWords;
    }
}
