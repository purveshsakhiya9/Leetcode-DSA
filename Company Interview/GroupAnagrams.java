import java.util.*;

public class GroupAnagrams {
    public static List<List<String>> GroupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedKey = new String(charArray);
            map.putIfAbsent(sortedKey, new ArrayList<>());
            map.get(sortedKey).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = GroupAnagrams(input);
        for (List<String> group : result) {
            System.out.println(group);
        }
    }
}
