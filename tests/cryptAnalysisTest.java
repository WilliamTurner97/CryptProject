import org.junit.jupiter.api.Test;
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
    void invertAlphabet() {
        HashMap<Character, Character> alph = cryptAnalysis.randomAlphabet();
        HashMap<Character, Character> invAlph = cryptAnalysis.invertAlphabet(alph);

        char[] ak = cryptAnalysis.toCharArray(alph.keySet());
        char[] iv = cryptAnalysis.toCharArray(invAlph.values());
        char[] av = cryptAnalysis.toCharArray(alph.values());
        char[] ik = cryptAnalysis.toCharArray(invAlph.keySet());
        Arrays.sort(ak);
        Arrays.sort(iv);
        Arrays.sort(av);
        Arrays.sort(ik);

        assertArrayEquals(ak, iv);
        assertArrayEquals(av, ik);
    }

    public static String randomString(int i) {
        StringBuilder s = new StringBuilder();
        for(int j = 0; j < i; j++) {
            s.append((char)(r.nextInt(123-97)+97));
        }
        return s.toString();
    }


}

