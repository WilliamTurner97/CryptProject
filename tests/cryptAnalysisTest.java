import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class cryptAnalysisTest {

    static Random r = new Random();
    String sampleText = "iamtheverymodelofamodernmajorgeneralihaveinformationvegetableanimalandmineral";
    int[][] trie = Trie.makeTrie(Trie.wordsFromFile("englwords2.txt"));

    cryptAnalysisTest() throws FileNotFoundException {
    }


/*    @Test
    void loop() throws FileNotFoundException {
        HashMap<Character, Character> a1 = new HashMap<>();
        char[] letters1 = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] letters2 = "bcdefghijklmnopqrstuvwxyza".toCharArray();

        for(int i = 0; i < 26; i++){
            a1.put(letters1[i], letters2[i]);
        }

        Tuple<String, boolean[]> sampleFullEncrypt = cryptAnalysis.translate(a1, sampleText, false);

        for(int i = 0; i < 10; i++) {
            a1.remove((char) (i + 97));
        }

        Tuple<String, boolean[]> samplePartialDecrypt = cryptAnalysis.translate(a1, sampleFullEncrypt.x, true);

        KeyTreeNode ktn1 = new KeyTreeNode(null, a1,
                cryptAnalysis.assembleFragments(samplePartialDecrypt, true), trie);

         System.out.println(cryptAnalysis.loop(10, 1000, a1, sampleText));
    }*/


    @Test
    void translate() {

        for (int i = 0; i < 10; i++) {
            String testText = randomString(r.nextInt(1000));
            Tuple<char[], char[]> randAlph = randomAlphabet();
            Tuple<String, boolean[]> translation = cryptAnalysis.translate(randAlph, testText);
            for (int j = 0; j < translation.x.length(); j++) {
                assertEquals(translation.x.charAt(i), randAlph.y[testText.charAt(i) - 97]);
            }
            assertEquals(testText, cryptAnalysis.translate(cryptAnalysis.invertAlphabet(randAlph), translation.x).x);
        }
    }

    @Test
    void invertAlphabet() {
        Tuple<char[], char[]> a1 = randomAlphabet();
        Tuple<char[], char[]> a2 = cryptAnalysis.invertAlphabet(a1);
        for (int i = 0; i < 25; i++) {
            assertEquals(a1.x[i], a2.y[a1.y[i] - 97]);
        }
    }

    @Test
    void numWords() {
    }

    @Test
    void assembleFragments() {
        String text = "abcdefg";
        boolean[] translated = {true, false, true, true, false, true, true};
        assertArrayEquals(cryptAnalysis.assembleFragments(new Tuple<String, boolean[]>(text, translated), true),
                new String[]{"a", "cd", "fg"});
    }

    @Test
    void charFrequencies() {
        String s = "aaaaabbcdeeeeffffgg";
        assertArrayEquals(cryptAnalysis.charFrequencies(s),
                new int[] {5,2,1,1,4,4,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
    }



    public static Tuple<char[], char[]> randomAlphabet() {

        char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] randLetters = Arrays.copyOf(letters, letters.length);

        for (int i = 0; i < 100; i++) {
            int index1 = r.nextInt(randLetters.length);
            int index2 = r.nextInt(randLetters.length);
            char c = randLetters[index1];
            randLetters[index1] = randLetters[index2];
            randLetters[index2] = c;
        }

        return new Tuple<>(letters, randLetters);
    }


    public static String randomString(int i) {
        StringBuilder s = new StringBuilder();
        for(int j = 0; j < i; j++) {
            s.append((char)(r.nextInt(123-97)+97));
        }
        return s.toString();
    }


}

