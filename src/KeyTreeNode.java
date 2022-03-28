import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.IntStream;

public class KeyTreeNode {

    Tuple<char[], char[]> alphabet;
    KeyTreeNode parent;
    ArrayList<KeyTreeNode> children = new ArrayList<>();

    double totalRestarts = 0;
    double improvedRestarts;
    double relativeImprovement;
    double numwords;

    public KeyTreeNode(KeyTreeNode parent, Tuple<char[], char[]> alphabet, String[] translatedFragments, int[][] trie) {

        this.parent = parent;
        this.alphabet = alphabet;

        // objective heuristic measurement
        for (String fragment: translatedFragments) {
            totalRestarts += numRestarts(fragment, trie);
            numwords += cryptAnalysis.numWords(fragment, trie);
        }

        // relative heuristic measurement
        if(parent != null) {
            improvedRestarts = parent.getTotalRestarts() - totalRestarts;
            relativeImprovement = parent.getTotalRestarts()/totalRestarts;
        }
        else{
            improvedRestarts = 1;
            relativeImprovement = 1.5;
        }
    }

    // create, evaluate, and sort nodes representing all possibilities for descendant nodes containing c
    public void expand(Tuple<String, boolean[]> partial, ArrayList<KeyTreeNode> bestNodes, int[][] trie) {

        char c = cryptAnalysis.bestExpansion(partial, alphabet);
        ArrayList<Character> taken = cryptAnalysis.toCharacterArray(alphabet.y);

        for (int i = 0; i < 26; i++) {
            if(!taken.contains((char) (i + 97))) {
               Tuple<char[], char[]> newAlph = new Tuple<char[], char[]>(
                       Arrays.copyOf(alphabet.x, alphabet.x.length), Arrays.copyOf(alphabet.y, alphabet.y.length));
               newAlph.x[c - 97] = c;
               newAlph.y[c - 97] = ((char) (i + 97));
                KeyTreeNode k = new KeyTreeNode(this, newAlph, cryptAnalysis.assembleFragments(partial, true), trie);
                children.add(k);
                bestNodes.add(k);
            }
        }
        //bestNodes.sort(Comparator.comparing(KeyTreeNode::getImprovedRestarts));
    }





    // number of times matching text to lines through trie has to restart without a word end
    public static double numRestarts(String text, int[][] trie) {
        //System.out.println(text);
        double restarts = 0;
        int n = 0;

        // match text to path through trie
        for(int i = 0; i < text.length(); i++){
            int ln = text.charAt(i) - 96;

            // continue path if valid
            if(trie[n][ln] != 0) {
                n = trie[n][ln];
            }
            // otherwise, keep position in text but restart trie path from head node
            if(trie[n][ln] == 0 || i == text.length() - 1) {
                if(trie[n][27] == 0) {
                    restarts++;
                }
                n = 0;
            }
        }
        return restarts;
    }

    // setters and getters

    public Tuple<char[], char[]> getAlphabet() {
        return alphabet;
    }

    public KeyTreeNode getParent() {
        return parent;
    }

    public ArrayList<KeyTreeNode> getChildren() {
        return children;
    }

    public void setParent(KeyTreeNode parent) {
        this.parent = parent;
    }

    public double getTotalRestarts() {
        return totalRestarts;
    }

    public double getImprovedRestarts() {
        return improvedRestarts;
    }

    public double getRelativeImprovement() {
        return relativeImprovement;
    }

    public double getNumwords() {
        return numwords;
    }

    public int getAlphabetSize() {
        return alphabet.x.length;
    }
}
