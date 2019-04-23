package task;

import java.io.PrintWriter;

public class BIgraSTelefonnimNomerom {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int steps = n - 11;
        int aSteps = (steps + 1) / 2;
        int bSteps = steps / 2;
        String s = in.next();
        int left = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '8') {
                if (bSteps > 0) {
                    bSteps--;
                } else {
                    if (left == -1) {
                        left = i;
                    }
                }
            }
        }
        if (left != -1 && n - left >= 11) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
}
