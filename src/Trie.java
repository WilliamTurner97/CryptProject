import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Trie {

    public static void main(String[] args) throws IOException {
        //ArrayList<String> words = wordsFromFile("englwords2.txt");
        //int[][] trie = makeTrie(words);
    }

    // return file contents as list of string representations of each line
    public static ArrayList<String> wordsFromFile(String fileName) throws FileNotFoundException {
        ArrayList<String> words= new ArrayList<>();
        File wordList = new File(fileName);
        Scanner wordScan = new Scanner(wordList);
        while(wordScan.hasNextLine()) {
            words.add(wordScan.nextLine());
        }
        return words;
    }

    // create a trie from corresponding representation in a text file
    public static int[][] trieFromFile(String fileName) throws FileNotFoundException {

        ArrayList<String> nodeStrings = wordsFromFile(fileName);

        int[][] trie = new int[nodeStrings.size()][28];
        for (int i = 0; i < nodeStrings.size(); i++) {
            String[] numbers = nodeStrings.get(i).replaceAll("\\[", "")
                    .replaceAll("]", "").replaceAll(" ", "").split(",");
            for (int j = 0; j < 28; j++) {
                trie[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        return trie;
    }

    // write the representation of a trie that can be read by trieFromFile to a text file
    public static void trieToFile(int[][] trie) throws IOException {
        FileWriter trieWriter = new FileWriter("testFile.txt");
        for (int[] ints : trie) {
            trieWriter.write(Arrays.toString(ints));
            trieWriter.write("\n");
        }
        trieWriter.close();
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
        return (trie.toArray(new int[0][0]));
    }
}
