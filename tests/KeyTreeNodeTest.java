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


    @Test
    void expand() {

        Tuple<char[], char[]> a1 = new Tuple<>("abcdefghijklmnopqrstuvwxyz".toCharArray(), "bcdefghijklmnopqrstuvwxyza".toCharArray());
        Tuple<String, boolean[]> sampleFullEncrypt = cryptAnalysis.translate(a1, sampleText);
        Tuple<char[], char[]> a2 = cryptAnalysis.invertAlphabet(a1);

        for(int i = 0; i < 10; i++) {
            a2.y[i] = '`';
        }

        Tuple<String, boolean[]> samplePartialDecrypt = cryptAnalysis.translate(a2, sampleFullEncrypt.x);

        KeyTreeNode ktn1 = new KeyTreeNode(null, a2,
                cryptAnalysis.assembleFragments(samplePartialDecrypt, true), trie);

        ArrayList<KeyTreeNode> bestNodes = new ArrayList<>();
        ktn1.expand(samplePartialDecrypt, bestNodes, trie);
        for (KeyTreeNode k : bestNodes) {
            System.out.println(Arrays.toString(k.getAlphabet().y));
        }
    }

    @Test
    void randExpand() {
        Tuple<char[], char[]> a1 = new Tuple<>("abcdefghijklmnopqrstuvwxyz".toCharArray(), "bcdefghijklmnopqrstuvwxyza".toCharArray());
        Tuple<String, boolean[]> sampleFullEncrypt = cryptAnalysis.translate(a1, sampleText);
        Tuple<char[], char[]> a2 = cryptAnalysis.invertAlphabet(a1);

        for(int i = 0; i < 10; i++) {
            a2.y[r.nextInt(26)] = '`';
        }

        Tuple<String, boolean[]> samplePartialDecrypt = cryptAnalysis.translate(a2, sampleFullEncrypt.x);

        KeyTreeNode ktn1 = new KeyTreeNode(null, a2,
                cryptAnalysis.assembleFragments(samplePartialDecrypt, true), trie);

        ArrayList<KeyTreeNode> bestNodes = new ArrayList<>();
        ktn1.expand(samplePartialDecrypt, bestNodes, trie);
        System.out.println(Arrays.toString(a2.y));
        for (KeyTreeNode k : bestNodes) {
            System.out.println(Arrays.toString(k.getAlphabet().y));
        }
    }

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