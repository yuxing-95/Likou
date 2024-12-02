package org.example;

public class LiKou76 {
    /*给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
        注意：

        对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
        如果 s 中存在这样的子串，我们保证它是唯一的答案。


        示例 1：

        输入：s = "ADOBECODEBANC", t = "ABC"
        输出："BANC"
        解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
        示例 2：

        输入：s = "a", t = "a"
        输出："a"
        解释：整个字符串 s 是最小覆盖子串。
        示例 3:

        输入: s = "a", t = "aa"
        输出: ""
        解释: t 中两个字符 'a' 均应包含在 s 的子串中，
    因此没有符合条件的子字符串，返回空字符串。*/
    public String minWindow(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int[] arrt = new int[256];
        int[] arrs = new int[256];
        int less = 0;
        for (int i = 0; i < t.length; ++i) {
            arrt[t[i]]++;
            less++;
        }
        int l = -1;
        int len = Integer.MAX_VALUE;
        int left = 0;
        for (int i = 0; i < s.length; ++i) {
            arrs[s[i]]++;
            if(less > 0 && arrs[s[i]] <= arrt[s[i]]) {
                less--;
            }
            while(less == 0) {
                arrs[s[left]]--;
                if(arrs[s[left]] < arrt[s[left]]) {
                    less++;
                    if(len > i - left + 1) {
                        l = left;
                        len = i - left + 1;
                    }
                }
                left++;
            }
        }
        return l == -1 ? "" : S.substring(l, l +len);
    }
    public static void main(String[] args) {
        LiKou76 obj = new LiKou76();
        System.out.println(obj.minWindow("Hello", "He"));
    }
}
