import java.util.*;

public class cryptAnalysis {
    public static void main(String[] args) {
        HashMap<Character, Character> k = randomAlphabet();
        System.out.println(translate(k, "blahblahblah", true).x);
    }

    // generate a random monoalphabetic cipher
    public static HashMap<Character, Character> randomAlphabet(){

        HashMap<Character, Character> hm = new HashMap<Character, Character>();
        char[] primLetters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        List<Character> letters = new ArrayList<>();
        List<Character> randLetters = new ArrayList<>();

        for(char c : primLetters) {
            letters.add(c);
            randLetters.add(c);

        }

        Collections.shuffle(randLetters);

        for(int i = 0; i < 26; i++){
            hm.put(letters.get(i), randLetters.get(i));
        }
        return hm;
    }

    // encrypt or decrypt given text with given monoalphabetic cipher
    public static Tuple<String, boolean[]> translate(HashMap<Character, Character> alphabet, String text, boolean decrypt) {
        StringBuilder translatedText = new StringBuilder();
        HashMap<Character, Character> alph = alphabet;
        boolean[] translatedChars = new boolean[text.length()];
        for(int i = 0; i < text.length(); i++){
            // pass along characters for which they key does not have a value
            if(alph.get(text.charAt(i)) == null){
                translatedText.append(text.charAt(i));
                translatedChars[i] = false;
            }
            // otherwise, swap for corresponding character in key
            else{
                translatedText.append(alph.get(text.charAt(i)));
                translatedChars[i] = true;
            }
        }

        return(new Tuple<String, boolean[]>(translatedText.toString(), translatedChars));
    }

    // returns number of identifiable words in a given string
    public int numWords(String text, int[][] trie) {
        int total = 0;
        int n = 0;

        // match text to path through trie
        for(int i = 0; i < text.length(); i++){
            int ln = text.charAt(i) - 97;

            // increment total every time corresponding trie node has a word end
            if(trie[n][27] == 1) {
                total++;
            }
            // continue path if valid
            if(trie[n][ln] != 0) {
                n = trie[n][ln];
            }
            // otherwise, keep position in text but restart trie path from head node
            else {
                n = 0;
            }
        }
        return total;
    }
}
