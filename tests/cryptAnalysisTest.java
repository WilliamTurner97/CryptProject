import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class cryptAnalysisTest {

    static Random r = new Random();


    @Test
    void randomAlphabet() {
        HashMap<Character, Character> rand = cryptAnalysis.randomAlphabet();
        assertEquals(rand.size(), 26);
    }

    @Test
    void translate() {
        HashMap<Character, Character> a = cryptAnalysis.randomAlphabet();
        String randString = randomString(r.nextInt(0,100));
        assertEquals(cryptAnalysis.translate(a, "", true).x, "");
        assertEquals(randString,
                cryptAnalysis.translate(a, cryptAnalysis.translate(a, randString, false).x, true).x);

    }

    @Test
    void numWords() {
    }

    @Test
    void assembleFragments() {
        String text = "abcdefg";
        boolean[] translated = {true, false, true, true, false, true, true};
    }

    @Test
    void invertAlphabet() {
        HashMap<Character, Character> alph = cryptAnalysis.randomAlphabet();
        HashMap<Character, Character> invAlph = cryptAnalysis.invertAlphabet(alph);

        for(Character c : alph.keySet()){
            assertEquals(c.charValue(), invAlph.get(alph.get(c)));
        }
    }

    public static String randomString(int i) {
        StringBuilder s = new StringBuilder();
        for(int j = 0; j < i; j++) {
            s.append((char)(r.nextInt(123-97)+97));
        }
        return s.toString();
    }


}

