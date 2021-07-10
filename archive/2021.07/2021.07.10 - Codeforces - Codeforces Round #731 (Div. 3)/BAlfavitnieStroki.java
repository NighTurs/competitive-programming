package task;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class BAlfavitnieStroki {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        Set<Character> a = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (a.contains(s.charAt(i))) {
                out.println("NO");
                return;
            }
            a.add(s.charAt(i));
        }
        int st = -1;
        int ed = -1;
        for (int i = 0; i <= Math.min(s.length() - 1, 'z' - 'a'); i++) {
            int pos = -1;
            for (int h = 0; h < s.length(); h++) {
                if (s.charAt(h) - 'a' == i) {
                    pos = h;
                    break;
                }
            }
            if (pos == -1) {
                out.println("NO");
                return;
            }
            if (i == 0) {
                st = pos;
                ed = pos;
            } else {
                if (pos == st - 1) {
                    st--;
                } else if (pos == ed + 1) {
                    ed++;
                } else {
                    out.println("NO");
                    return;
                }
            }
        }
        out.println("YES");
    }
}
