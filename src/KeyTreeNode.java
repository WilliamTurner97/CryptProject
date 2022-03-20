import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class KeyTreeNode {

    HashMap<Character, Character> alphabet;
    KeyTreeNode parent;
    ArrayList<KeyTreeNode> children = new ArrayList<>();

    double totalRestarts = 0;
    double improvedRestarts;
    double relativeImprovement;

    public KeyTreeNode(KeyTreeNode parent, HashMap<Character, Character> alphabet,
                       List<String> textFragments, int[][] trie) {

        this.parent = parent;
        this.alphabet = alphabet;

        // objective heuristic measurement
        for (String fragment: textFragments) {
            totalRestarts += numRestarts(fragment, trie);
        }

        // relative heuristic measurement
        if(parent != null) {
            improvedRestarts = totalRestarts - parent.getTotalRestarts();
            relativeImprovement = totalRestarts/parent.getTotalRestarts();
        }
        else{
            improvedRestarts = totalRestarts;
            relativeImprovement = 0;
        }
    }

    // create, evaluate, and sort nodes representing all possibilities for descendant nodes containing c
    public void expand(Character c, List<String> textFragments, ArrayList<KeyTreeNode> bestNodes, int[][] trie) {
        for (int i = 0; i < 26; i++) {
            HashMap<Character, Character> newAlph = new HashMap<Character, Character>(alphabet);
            newAlph.put((char) (i + 97), c);
            KeyTreeNode k = new KeyTreeNode(this, newAlph, textFragments, trie);
            children.add(k);
            bestNodes.add(k);
        }
        bestNodes.sort(Comparator.comparing(KeyTreeNode::getTotalRestarts));
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
}
