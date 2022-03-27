import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class KeyTreeNodeTest {

    static Random r = new Random();
    String sampleText = "iamtheverymodelofamodernmajorgeneralihaveinformationvegetableanimalandmineral";
    int[][] trie = Trie.makeTrie(Trie.wordsFromFile("englwords2.txt"));

    KeyTreeNodeTest() throws FileNotFoundException {
    }


/*    @Test
    void fixedAlph(){
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
    }*/
/*

    @Test
    void randAlph() {
        HashMap<Character, Character> a2 = cryptAnalysis.randomAlphabet();
        Tuple<String, boolean[]> randomEncrypt = cryptAnalysis.translate(a2, sampleText, false);

        for(int i = 0; i < r.nextInt(5, 20); i++) {
            a2.remove((char) (r.nextInt(97, 122)));
        }
        Tuple<String, boolean[]> randomDecrypt = cryptAnalysis.translate(a2, randomEncrypt.x, true);

        KeyTreeNode ktn1 = new KeyTreeNode(null, a2,
                cryptAnalysis.assembleFragments(randomDecrypt, true), trie);
    }
*/

/*    @Test
    void expand() {
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
        List<String> remainingFragments = cryptAnalysis.assembleFragments(samplePartialDecrypt, false);

        KeyTreeNode ktn1 = new KeyTreeNode(null, a1, remainingFragments, trie);
        ktn1.expand(cryptAnalysis.mostCommonChar(remainingFragments.toArray(new String[0])),
                remainingFragments, new ArrayList<KeyTreeNode>(), trie);
    }*/

    @Test
    void charFrequencies(){
        int[] testFrequencies = cryptAnalysis.charFrequencies("aaaabcccdefghhfrijklmnopz");
        assertEquals(testFrequencies[0], 4);
    }

    @Test
    void charFrequencies2(){
        int[] testFrequencies = cryptAnalysis.charFrequencies(new String[]{"abcdefg", "abcde", "ab"});
        assertArrayEquals(testFrequencies, new int[]{3,3,2,2,2,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
    }
}