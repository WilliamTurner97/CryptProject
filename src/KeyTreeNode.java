import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeyTreeNode {
    HashMap<Character, Character> alphabet;
    KeyTreeNode parent;
    double totalRestarts = 0;
    double improvedRestarts = 0;
    double relativeImprovement = 0;

    public KeyTreeNode(KeyTreeNode parent, HashMap<Character, Character> alphabet, List<String> textFragments)
            throws FileNotFoundException {

        this.parent = parent;
        this.alphabet = alphabet;
        int[][] trie = Trie.makeTrie(Trie.wordsFromFile("englwords2.txt"));
        for (String fragment: textFragments) {
            totalRestarts += numRestarts(fragment, trie);
        }

        if(parent != null) {
            improvedRestarts = (parent == null)? totalRestarts : totalRestarts - parent.getTotalRestarts();
            relativeImprovement = totalRestarts/parent.getTotalRestarts();
        }
    }

    public static double numRestarts(String text, int[][] trie) {
        ArrayList<String> words = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        double restarts = 0;
        int n = 0;

        // match text to path through trie
        for(int i = 0; i < text.length(); i++){
            int ln = text.charAt(i) - 96;


            // continue path if valid
            if(trie[n][ln] != 0) {
                s.append(text.charAt(i));
                n = trie[n][ln];
            }
            // otherwise, keep position in text but restart trie path from head node
            else {
                if(trie[n][27] == 0) {
                    restarts++;
                }
                n = 0;
                if(s.length() > 0) {
                    words.add(s.toString());
                }
                s.delete(0, s.length());
            }
        }

        for(String word : words) {
            System.out.println(word);
        }
        return restarts;
    }

    public KeyTreeNode getParent() {
        return parent;
    }

    public void setParent(KeyTreeNode parent) {
        this.parent = parent;
    }

    public double getTotalRestarts() {
        return totalRestarts;
    }
}
