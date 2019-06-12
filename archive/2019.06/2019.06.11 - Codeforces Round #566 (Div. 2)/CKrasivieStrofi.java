package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CKrasivieStrofi {
    public void solve(int testNumber, InputReader in, PrintWriter out) {

        int n = in.nextInt();

        Map<Key, List<Integer>> mp = new HashMap<>();
        List<String> ss = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String s = in.next();
            ss.add(s);
            Key key = getKey(s);
            mp.putIfAbsent(key, new ArrayList<>());
            mp.get(key).add(i);
        }

        boolean[] taken = new boolean[n];
        List<Pair<Integer, Integer>> seconds = new ArrayList<>();

        for (List<Integer> val : mp.values()) {
            for (int i = 0; i + 1 < val.size(); i += 2) {
                taken[val.get(i)] = true;
                taken[val.get(i + 1)] = true;
                seconds.add(new Pair<>(val.get(i), val.get(i + 1)));
            }
        }

        Map<Integer, List<Integer>> mp2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (taken[i]) {
                continue;
            }
            Key key = getKey(ss.get(i));
            mp2.putIfAbsent(key.syl, new ArrayList<>());
            mp2.get(key.syl).add(i);
        }

        List<Pair<Integer, Integer>> firsts = new ArrayList<>();

        for (List<Integer> val : mp2.values()) {
            for (int i = 0; i + 1 < val.size(); i += 2) {
                firsts.add(new Pair<>(val.get(i), val.get(i + 1)));
            }
        }

        while (seconds.size() > firsts.size()) {
            Pair<Integer,Integer> p = seconds.get(seconds.size() - 1);
            seconds.remove(seconds.size() - 1);
            firsts.add(p);
        }
        int ct = Math.min(firsts.size(), seconds.size());
        out.println(ct);

        for (int i = 0; i < ct; i++) {
            out.print(ss.get(firsts.get(i).fs));
            out.print(' ');
            out.println(ss.get(seconds.get(i).fs));
            out.print(ss.get(firsts.get(i).sc));
            out.print(' ');
            out.println(ss.get(seconds.get(i).sc));
        }
    }

    Key getKey(String s) {
        Key key = new Key();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'o' || c == 'i' || c == 'u') {
                key.syl++;
                key.last = c;
            }
        }
        return key;
    }

    class Key {
        int syl;
        char last;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Key key = (Key) o;
            return syl == key.syl && last == key.last;
        }

        @Override
        public int hashCode() {
            return Objects.hash(syl, last);
        }
    }
}
