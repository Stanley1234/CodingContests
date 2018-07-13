class Solution {
    
    private boolean beginWithVowel(String word) {
        return word.charAt(0) == 'a' || word.charAt(0) == 'e' || word.charAt(0) == 'i' || word.charAt(0) == 'o'
            || word.charAt(0) == 'u' || word.charAt(0) == 'A' || word.charAt(0) == 'E' || word.charAt(0) == 'I'
            || word.charAt(0) == 'O' || word.charAt(0) == 'U';
    }
            
    private String repeat(char ch, int cnt) {
        StringBuilder sb = new StringBuilder();
        while (cnt -- > 0) {
            sb.append(ch);
        }
        return sb.toString();
    }
    
    public String toGoatLatin(String input) {
        String[] words = input.split(" ");  // assume only one space between two words
        if (words.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (String word : words) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            if (beginWithVowel(word)) {
                sb.append(word);   
            } else {
                sb.append(word.substring(1));
                sb.append(word.charAt(0));
            }
            sb.append("ma");
            sb.append(repeat('a', index));
            index ++;
        }
        return sb.toString();
    }
}
