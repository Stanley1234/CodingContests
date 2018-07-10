class Solution {
    private class Pair<K, V> {
        K key;
        V val;
        Pair(K k, V v) {
            this.key = k;
            this.val = v;
        }
    }


    public String reorganizeString(String str) {
        char[] input = str.toCharArray();


        // calculate the total amount
        int[] count = new int[26];
        int n = input.length;
        for (char each : input) {
            count[each - 'a'] ++;
        }

        // check if it is possible
        for (int each : count) {
            if (each > (n + 1) / 2) {
                return "";
            }
        }

        // make a string in which characters appear more frequently position first
        LinkedList<Pair<Character, Integer>> segments = new LinkedList<>();
        for (int i = 0;i < 26;i ++) {
            if (count[i] != 0) {
                segments.add(new Pair<>((char)(i + 'a'), count[i]));
            }
        }
        Collections.sort(segments, new Comparator<Pair<Character, Integer>>() {
            @Override
            public int compare(Pair<Character, Integer> o1, Pair<Character, Integer> o2) {
                return o2.val - o1.val;
            }
        });
        StringBuilder reoderedBuilder = new StringBuilder();
        for (Pair<Character, Integer> each : segments) {
            while (each.val -- > 0) {
                reoderedBuilder.append(each.key);
            }
        }
        String reorder = reoderedBuilder.toString();

        // calculate the answer
        // length = 4, j = 2
        // length = 5, j = 3
        int i = 0, j = (input.length + 1) / 2;
        String ans = "";
        while (i < (input.length + 1) / 2) {
            ans += reorder.charAt(i ++);
            if (j < input.length) {
                ans += reorder.charAt(j ++);
            }
        }
        return ans;
    }
}
