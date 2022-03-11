import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Trie {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> words = wordsFromFile("englwords2.txt");
        int[][] trie = makeTrie(words);
        //System.out.println(cryptAnalysis.numWords("hkbvt", trie));
    }

    public static ArrayList<String> wordsFromFile(String fileName) throws FileNotFoundException {
        ArrayList<String> words= new ArrayList<>();
        File wordList = new File(fileName);
        Scanner wordScan = new Scanner(wordList);
        while(wordScan.hasNextLine()) {
            words.add(wordScan.nextLine());
        }
        return words;
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
        trie.add(new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,0});

        for(int i = 0; i < 26; i++) {
            trie.add(new int[] {i,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        }

        int trieSize = 27;

        // Iterate over provided vocabulary
        for(String word: words) {
            int n = 0;

            // add nodes to complete the path of word in trie
            for(int i = 0; i < word.length(); i++) {
                // alphabet index of current letter
                int ln = word.charAt(i) - 97;
                try {
                    if(trie.get(n)[ln+1] == 0) {
                        int[] nextNode = new int[]{ln,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                        trie.add(nextNode);
                        trie.get(n)[ln+1] = trieSize;
                        n = trieSize;
                        trieSize++;
                    }

                    else {
                        n = trie.get(n)[ln+1];
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(words.lastIndexOf(word));
                }

            }
            // Set node's 28th element to represent a word end
            trie.get(n)[27] = 1;
        }
        return (trie.toArray(new int[28][0]));
    }
}
