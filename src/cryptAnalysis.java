import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class cryptAnalysis {
    public static void main(String[] args) throws FileNotFoundException {

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
    public static Tuple<String, boolean[]> translate(
            HashMap<Character, Character> alphabet, String text, boolean decrypt) {

        StringBuilder translatedText = new StringBuilder();
        HashMap<Character, Character> alph = decrypt? invertAlphabet(alphabet): alphabet;
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
    public static List<String> assembleFragments(String text, boolean[] translated, boolean translatedSection) {
        ArrayList<String> fragments = new ArrayList<>();

        int i = 0;
        while(i < text.length()) {
            // assemble each fragment and add to fragments
            if((translated[i] && translatedSection) || (!translated[i] && !translatedSection)) {
                StringBuilder s = new StringBuilder();
                while (i < text.length() && ((translated[i] && translatedSection) || (!translated[i] && !translatedSection))) {
                    s.append(text.charAt(i));
                    i++;
                }
                fragments.add(s.toString());
            }
            i++;
        }
        return fragments;
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


    // takes a Hashmap and swaps keys and values
    public static HashMap<Character, Character> invertAlphabet(HashMap<Character, Character> alph){
        HashMap<Character, Character> newAlph = new HashMap<>();
        for(Character k : alph.keySet()){
            newAlph.put(alph.get(k), k);
        }
        return newAlph;
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
