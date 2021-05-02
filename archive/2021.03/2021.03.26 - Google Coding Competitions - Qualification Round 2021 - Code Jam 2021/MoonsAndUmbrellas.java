package task;

import java.io.PrintWriter;

public class MoonsAndUmbrellas {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int cj = in.nextInt();
        int jc = in.nextInt();

        String s = in.next();

        int NAN = Integer.MAX_VALUE;
        long lc = NAN;
        long lj = NAN;
        switch (s.charAt(0)) {
            case 'C':
                lc = 0;
                break;
            case 'J':
                lj = 0;
                break;
            case '?':
                lc = 0;
                lj = 0;
                break;
        }

        for (int i = 1; i < s.length(); i++) {
            long pc = lc;
            long pj = lj;
            lc = NAN;
            lj = NAN;
            switch (s.charAt(i)) {
                case 'C':
                    lc = Math.min(pc, pj + jc);
                    break;
                case 'J':
                    lj = Math.min(pj, pc + cj);
                    break;
                case '?':
                    lc = Math.min(pc, pj + jc);
                    lj = Math.min(pj, pc + cj);
                    break;
            }
        }
        out.println(String.format("Case #%d: %d", testNumber, Math.min(lc, lj)));
    }
}
