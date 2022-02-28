import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Trie {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> words = cryptAnalysis.wordsFromFile("englwords.txt");
        int[][] trie = makeTrie(words);
        System.out.println(cryptAnalysis.numWords("hkbvt", trie));
    }

    /*
    Matrix representation of graph for english language trie. Each node is stored as an int array.
    node[0] represents the char value of the node. Node[27] represents if node is the endpoint for a valid word.
    node[1-26] holds 0 if the node has no child with the letter of the corresponding index, otherwise holds the index
    in the trie for the corresponding child.
     */
    public static int[][] makeTrie(ArrayList<String> words) {
        // Initialize trie and add head node and node for each letter
        ArrayList<int[]> trie = new ArrayList<>();
        trie.add(new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27});

        for(int i = 0; i < 26; i++) {
            trie.add(new int[] {i,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        }

        // Iterate over provided vocabulary
        for(String word: words) {
            int n = 0;
            int trieSize = 27;

            // add nodes to complete the path of word in trie
            for(int i = 0; i < word.length(); i++) {
                // alphabet index of current letter
                int ln = word.charAt(i) - 97;
                if(trie.get(n)[ln] == 0) {
                    int[] nextNode = new int[]{ln,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                    nextNode[ln] = trieSize;
                    trie.add(nextNode);
                    n = trieSize;
                    trieSize++;
                }
                else {
                    n = trie.get(n)[ln];
                }
            }
            // Set node's 28th element to represent a word end
            trie.get(n)[27] = 1;
        }
        return (trie.toArray(new int[28][0]));
    }
}
