package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DCvetnieBotinki {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Pair<List<List<Integer>>, List<Integer>> s = count(in.next());
        List<List<Integer>> s1 = s.fs;
        List<Integer> q1 = s.sc;
        s = count(in.next());
        List<List<Integer>> s2 = s.fs;
        List<Integer> q2 = s.sc;

        List<Pair<Integer, Integer>> ans = new ArrayList<>();
        List<Integer> left1 = new ArrayList<>();
        List<Integer> left2 = new ArrayList<>();

        for (int i = 0; i <= 'z' - 'a'; i++) {
            int minS = Math.min(s1.get(i).size(), s2.get(i).size());
            for (int h = 0; h < minS; h++) {
                ans.add(new Pair<>(s1.get(i).get(h), s2.get(i).get(h)));
            }
            for (int h = minS; h < s1.get(i).size(); h++) {
                left1.add(s1.get(i).get(h));
            }
            for (int h = minS; h < s2.get(i).size(); h++) {
                left2.add(s2.get(i).get(h));
            }
        }
        int minS = Math.min(left1.size(), q2.size());
        int used2 = minS;
        for (int i = 0; i < minS; i++) {
            ans.add(new Pair<>(left1.get(i), q2.get(i)));
        }
        minS = Math.min(left2.size(), q1.size());
        int used1 = minS;
        for (int i = 0; i < minS; i++) {
            ans.add(new Pair<>(q1.get(i), left2.get(i)));
        }

        for (int i = used1, h = used2; i < q1.size() && h < q2.size(); i++, h++) {
            ans.add(new Pair<>(q1.get(i), q2.get(h)));
        }
        out.println(ans.size());
        for (int i = 0; i < ans.size(); i++) {
            out.println(String.format("%d %d", ans.get(i).fs + 1, ans.get(i).sc + 1));
        }
    }

    public Pair<List<List<Integer>>, List<Integer>> count(String s) {
        List<List<Integer>> chars = new ArrayList<>();
        List<Integer> q = new ArrayList<>();
        for (int i = 0; i <= 'z' - 'a'; i++) {
            chars.add(new ArrayList<>());
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '?') {
                q.add(i);
            } else {
                int c = s.charAt(i) - 'a';
                chars.get(c).add(i);
            }
        }
        return new Pair<>(chars, q);
    }
}
