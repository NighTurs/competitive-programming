package task;

import java.io.PrintWriter;

public class CPenalti {

    final int NUM = 10;

    int doit(int pos, int score, int[] pred) {
        if (pos >= NUM) {
            return pos;
        }
        if (score < 0) {
            if ((NUM - pos) / 2 + score < 0) {
                return pos;
            }
        } else {
            if ((NUM - pos + 1) / 2 - score < 0) {
                return pos;
            }
        }
        if (NUM - pos < Math.abs(score)) {
            return pos;
        }

        int goal = pos % 2 == 0 ? 1 : -1;

        if (pred[pos] == 1) {
            return doit(pos + 1, score + goal, pred);
        }
        if (pred[pos] == 0) {
            return doit(pos + 1, score, pred);
        }
        return Math.min(doit(pos + 1, score + goal, pred), doit(pos + 1, score, pred));
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        int[] pred = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '1':
                    pred[i] = 1;
                    break;
                case '0':
                    pred[i] = 0;
                    break;
                case '?':
                    pred[i] = -1;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + s.charAt(i));
            }
        }
        out.println(doit(0, 0, pred));
    }
}
