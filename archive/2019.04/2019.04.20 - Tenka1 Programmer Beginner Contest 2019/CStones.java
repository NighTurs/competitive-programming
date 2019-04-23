package task;

import java.io.PrintWriter;

public class CStones {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        int white = 0;
        int black = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) =='.') {
                white++;
            } else {
                black++;
            }
        }
        int curWhite = 0;
        int curBlack = 0;

        int ans = Math.min(white, black);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) =='.') {
                curWhite++;
            } else {
                curBlack++;
            }
            int curCost = curBlack + (white - curWhite);
            if (curCost < ans) {
                ans = curCost;
            }
        }
        out.println(ans);
    }
}
