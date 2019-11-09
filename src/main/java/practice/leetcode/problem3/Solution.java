package practice.leetcode.problem3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by wenqi on 2019/9/19.
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring2("abcabcbb"));
    }

    /**
     * this is not a good solution
     * we should first get position,then calculate length.
     * this method is reversely.
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }

        char[] arr = s.toCharArray();
        //use to record the final max length
        int maxLen = 1;
        //use to record max len in one turn
        int curMaxLen = maxLen;
        //use to record current start index in one turn
        int start = 0;
        //use to recored current end index in one turn
        int end = start + 1;

        //store already exist chars and their indexes in one turn
        //char->last index
        Map<Character, Integer> hashMap = new HashMap<>(30);
        hashMap.put(arr[0], 0);

        while (end < arr.length) {

            if (hashMap.containsKey(arr[end])) {
                //one turn end
                if (curMaxLen > maxLen) {
                    maxLen = curMaxLen;
                }
                start = Math.max(start, hashMap.get(arr[end]) + 1);

                //record cur end index,must after get and before calculate new end
                hashMap.put(arr[end], end);

               /* //end should move forward 1
                if(end<start){
                    start=end;
                }*/
                curMaxLen = end - start + 1;
                end++;
            } else {
                //record cur end index
                hashMap.put(arr[end], end);
                curMaxLen++;
                end++;
            }
        }

        //last turn update
        if (curMaxLen > maxLen) {
            maxLen = curMaxLen;
        }
        return maxLen;
    }

    /**
     * slice-window simple format
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> indexMap = new HashMap<>();
        int ans = 0;
        int curLen = 0;
        //slice window is [i,j]
        for (int i = 0, j = 0; j < s.length(); j++) {
            if (!indexMap.containsKey(s.charAt(j))) {
                //not int the indexMap
                indexMap.put(s.charAt(j), j);
                curLen++;
                ans = Math.max(curLen, ans);
            } else {
                //in the indexMap
                //i skip to indexMap[char[j]]+1
                //i must large than current i, otherwise it is invalid
                // take "abba" as an example
                i = Math.max(i, indexMap.get(s.charAt(j)) + 1);
                //update duplicate index
                indexMap.put(s.charAt(j), j);
                curLen = j - i + 1;
                ans = Math.max(curLen, ans);
            }
        }
        return ans;
    }
}
