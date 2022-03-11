import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class KeyTreeNodeTest {

    static Random r = new Random();
    String sampleText = "iamtheverymodelofamodernmajorgeneralihaveinformationanimalvegetableandmineral";
    HashMap<Character, Character> a1 = new HashMap<>();



    @Test
    void KeyTreeNode() throws FileNotFoundException {

        char[] letters1 = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] letters2 = "bcdefghijklmnopqrstuvwxyza".toCharArray();

        for(int i = 0; i < 26; i++){
            a1.put(letters1[i], letters2[i]);
        }


        for(int i = 0; i < 10; i++) {
            a1.remove((char) (i + 97));
        }

        Tuple<String, boolean[]> translation = cryptAnalysis.translate(a1, sampleText, false);

        KeyTreeNode ktn1 = new KeyTreeNode(null, a1,
                cryptAnalysis.assembleFragments(translation.x, translation.y));
        System.out.println(ktn1.getTotalRestarts());
    }
}