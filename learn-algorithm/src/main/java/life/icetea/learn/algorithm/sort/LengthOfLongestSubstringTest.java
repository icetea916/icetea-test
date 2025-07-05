package life.icetea.learn.algorithm.sort;

import java.util.HashSet;

// 无重复字符串最长字串
public class LengthOfLongestSubstringTest {

    public static void main(String[] args) {
        String str1 = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(str1));
        String str2 = "bbbbb";
        System.out.println(lengthOfLongestSubstring(str2));
        String str3 = "pwwkew";
        System.out.println(lengthOfLongestSubstring(str3));
        String str4 = " ";
        System.out.println(lengthOfLongestSubstring(str4));
        String str5 = "a";
        System.out.println(lengthOfLongestSubstring(str5));
        String str6 = "";
        System.out.println(lengthOfLongestSubstring(str6));
        String str7 = "au";
        System.out.println(lengthOfLongestSubstring(str7));
    }

    public synchronized static int lengthOfLongestSubstring(String s) {
        if ("".equals(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        HashSet<Character> set = new HashSet<>();
        int maxLen = 1;
        for (int i = 0; i < chars.length; i++) {
            set.add(chars[i]);
            for (int j = i + 1; j < chars.length; j++) {
                char aChar = chars[j];
                if (set.contains(aChar)) {
                    set.clear();
                    break;
                }
                set.add(chars[j]);
                if (set.size() > maxLen) {
                    maxLen = set.size();
                }
            }
        }

        return maxLen;
    }

}
