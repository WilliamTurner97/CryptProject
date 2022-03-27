import java.io.FileNotFoundException;
import java.util.*;

public class cryptAnalysis {
    public static void main(String[] args) throws FileNotFoundException {
    }


/*    public static String loop(int restarts, int iterations, HashMap<Character, Character> alph, String encryptedText) throws FileNotFoundException {

        int[][] trie = Trie.makeTrie(Trie.wordsFromFile("englwords2.txt"));
        KeyTreeNode k = new KeyTreeNode(null, alph, assembleFragments(translate(alph, encryptedText, false), true), trie);
        ArrayList<KeyTreeNode> bestNodes = new ArrayList<>();

        int i = 0;
        while(k.getTotalRestarts() > restarts && i < iterations) {
            Tuple<String, boolean[]> t = translate(k.getAlphabet(), encryptedText, false);
            k.expand(mostCommonChar(assembleFragments(t, false).toArray(new String[0])),
                    assembleFragments(t, true), bestNodes, trie);
            k = bestNodes.get(bestNodes.size()-1);
            i += 1;
        }

        return translate(k.getAlphabet(), encryptedText, true).x;
    }*/

    // encrypt or decrypt given text with given monoalphabetic cipher
    public static Tuple<String, boolean[]> translate(Tuple<char[], char[]> alphabet, String text) {

        char[] translatedText = new char[text.length()];
        boolean[] translatedChars = new boolean[text.length()];
        char[] textChars = alphabet.x;
        char[] correspondingChars = alphabet.y;

        for(int i = 0; i < text.length(); i++){
            // pass along chars for which they key does not have a value
            if(textChars[text.charAt(i) - 97] == '`'){
                translatedText[i] = (text.charAt(i));
                translatedChars[i] = false;
            }
            // otherwise, swap for corresponding char in alphabet
            else{
                translatedText[i] = correspondingChars[text.charAt(i) - 97];
                translatedChars[i] = true;
            }
        }
        return(new Tuple<>(new String(translatedText), translatedChars));
    }

    // returns number of identifiable words in a given string
    public static int numWords(String text, int[][] trie) {
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

    /* takes a string and boolean array, assembles chars for which corresponding boolean is true or false depending
    on translatedSection parameter
     */
    public static String[] assembleFragments(Tuple<String, boolean[]> partial, boolean translatedSection) {
        ArrayList<String> fragments = new ArrayList<>();

        int i = 0;
        while(i < partial.x.length()) {
            // assemble each fragment and add to fragments
            if((partial.y[i] && translatedSection) || (!partial.y[i] && !translatedSection)) {
                StringBuilder s = new StringBuilder();
                while (i < partial.x.length() && ((partial.y[i] && translatedSection) || (!partial.y[i] && !translatedSection))) {
                    s.append(partial.x.charAt(i));
                    i++;
                }
                fragments.add(s.toString());
            }
            i++;
        }
        return fragments.toArray(new String[0]);
    }

    // counts frequencies of each char in a string
    public static int[] charFrequencies(String text){
        int[] frequencies = new int[26];
        for (int i = 0; i < text.length(); i++) {
            frequencies[(char) (text.charAt(i) - 97)] ++;
        }
        return frequencies;
    }

    // counts frequencies of each char across an array of strings
    public static int[] charFrequencies(String[] fragments){
        int[] frequencies = new int[26];
        for (String fragment : fragments) {
            int[] fragmentFrequencies = charFrequencies(fragment);
            for (int i = 0; i < 26; i++) {
                frequencies[i] = frequencies[i] + fragmentFrequencies[i];
            }
        }
        return frequencies;
    }

    // finds the most common character in an array of strings
    public static char mostCommonChar(String[] fragments) {
        int[] frequencies = charFrequencies(fragments);
        int best = 0;
        int bestValue = frequencies[0];
        for (int i = 0; i < 26; i++) {
            if(frequencies[i] > bestValue) {
                best = i;
                bestValue = frequencies[i];
            }
        }
        return((char) (best + 97));
    }

    public static Tuple<char[], char[]> invertAlphabet(Tuple<char[], char[]> alph){
        char[] rearranged = new char[alph.x.length];
        for(int i = 0; i < alph.x.length; i++){
            rearranged[alph.y[i] - 97] = alph.x[i];
        }
        return new Tuple<char[], char[]>(alph.x, rearranged);
    }

    public static char[] toCharArray(Collection<Character> characters) {
        Character[] charactersArray = characters.toArray(new Character[0]);
        char[] chars = new char[charactersArray.length];
        for(int i = 0; i < charactersArray.length; i++){
            chars[i] = charactersArray[i];
        }
        return chars;

    }
}
