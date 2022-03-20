import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    ArrayList<String> words = Trie.wordsFromFile("englwords2.txt");
    int[][] trie = Trie.makeTrie(words);

    TrieTest() throws FileNotFoundException {
    }


    @Test
    void makeTrie(){
        for(int i = 0; i < 50; i++) {
            System.out.println(Arrays.toString(trie[i]));
        }
    }

    @Test
    void trieFromFile() throws FileNotFoundException {
        int[][] trie1 = Trie.trieFromFile("testFile.txt");
        int[][] trie2 = Trie.makeTrie(Trie.wordsFromFile("englwords2.txt"));

        assertArrayEquals(trie1, trie2);
    }

}