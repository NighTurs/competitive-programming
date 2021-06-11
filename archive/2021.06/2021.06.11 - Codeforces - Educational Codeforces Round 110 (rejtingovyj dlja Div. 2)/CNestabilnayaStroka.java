package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CNestabilnayaStroka {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        List<Character> a = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            a.add(s.charAt(i));
        }

        out.println(doit(a));
    }

    private long doit(List<Character> a) {
        char last = '?';
        int chunk = 0;
        long ans = 0;
        int quest = 0;
        for (int i = 0; i < a.size(); i++) {
            char cur = a.get(i);

            if (cur == '0' && last == '0') {
                chunk = 1 + quest;
            } else if (cur == '1' && last == '1') {
                chunk = 1 + quest;
            } else {
                chunk += 1;
            }

            if (cur == '?') {
                quest++;
            } else {
                quest = 0;
            }
            if (cur == '?') {
                if (last == '1') {
                    last = '0';
                } else if (last == '0')  {
                    last = '1';
                } else {
                    last = '?';
                }
            } else {
                last = cur;
            }
            ans += chunk;
        }
        return ans;
    }

}