package task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {

    List<Vertex> vs;

    public Trie() {
        vs = new ArrayList<>();
        vs.add(new Vertex(0, -1, ' '));
    }

    public void addString(String s) {
        addString(s, 0, vs.get(0));
    }

    public void addString(String s, int pos, Vertex v) {
        if (pos == s.length()) {
            return;
        }
        if (!v.adj.containsKey(s.charAt(pos))) {
            vs.add(new Vertex(vs.size(), pos, s.charAt(pos)));
            v.adj.put(s.charAt(pos), vs.get(vs.size() - 1));
        }
        addString(s, pos + 1, v.adj.get(s.charAt(pos)));
    }

    public static class Vertex {

        int idx;
        int height;
        char letter;
        Map<Character, Vertex> adj = new HashMap<>();

        public Vertex(int idx, int height, char letter) {
            this.idx = idx;
            this.height = height;
            this.letter = letter;
        }
    }
}
