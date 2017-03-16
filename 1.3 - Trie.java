class TrieNode {
    char c;
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;

    public TrieNode(char c) {
        this.c = c;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (node.children[index] == null) {
                TrieNode temp = new TrieNode(c);
                node.children[index] = temp;
                node = temp;
            } else {
                node = node.children[index];
            }
        }

        node.isEnd = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = searchNode(word);

        if (node == null) {
            return false;
        } else {
            return node.isEnd;
        }
    }

    // Returns if there is any word in the trie that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = searchNode(prefix);

        if (node == null) {
            return false;
        } else {
            return true;
        }
    }

    public TrieNode searchNode(String s) {
        TrieNode node = root;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            if (node.children[index] != null) {
                node = node.children[index];
            } else {
                return null;
            }
        }

        if (node == root) return null;

        return node;
    }
}
