import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    ArrayList<String> words = cryptAnalysis.wordsFromFile("englwords.txt");
    int[][] trie = Trie.makeTrie(words);

    TrieTest() throws FileNotFoundException {
    }


    @Test
    void makeTrie(){
        for(int i = 0; i < 50; i++) {
            System.out.println(Arrays.toString(trie[i]));
        }
    }


}