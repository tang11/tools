import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author tanglijuan
 * @date 2021/12/2
 */
public class CheckContainsMultiWords {

    public void check(String msg, List<List<String>> keywords) {
        List<String> one = new ArrayList<>();
        one.add("配置");
        one.add("白名单");
        keywords.add(one);
        List<String> two = new ArrayList<>();
        two.add("白名单");
        keywords.add(two);

    }
    public static boolean containsWordsWithAC(String inputString, String[] words) {
        Trie trie = Trie.builder().ignoreCase().addKeywords(words).build();

        Collection<Emit> emits = trie.parseText(inputString);
        emits.forEach(System.out::println);

        boolean found = true;
        for(String word : words) {
            boolean contains = Arrays.toString(emits.toArray()).contains(word);
            if (!contains) {
                found = false;
                break;
            }
        }

        return found;
    }

    public static void main(String[] args) {
        String msg = "qwqwqwwww配置3fescds配置cdfd白名单";
        List<String> keywords = new ArrayList<>();
        keywords.add("配置");
        keywords.add("白名单");
        Trie trie = Trie.builder().onlyWholeWords().addKeywords(keywords).build();
        Collection<Emit> emits = trie.parseText(msg);
        emits.forEach(System.out::println);

    }



}
