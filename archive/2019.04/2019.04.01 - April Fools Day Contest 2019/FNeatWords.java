package task;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class FNeatWords {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        Set<String> set = new HashSet<>();
        set.add("NEAT");
        set.add("AI");
        set.add("JUROR");
        if (set.contains(s)) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
}
