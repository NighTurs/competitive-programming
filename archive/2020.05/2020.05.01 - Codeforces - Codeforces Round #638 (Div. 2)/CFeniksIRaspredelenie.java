package task;

import java.io.PrintWriter;

public class CFeniksIRaspredelenie {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        outer:
        for (int t = 0; t < tt; t++) {
            int n = in.nextInt();
            int k = in.nextInt();
            int kk = k;
            String s = in.next();
            int[] ct = new int['z' - 'a' + 1];
            for (int i = 0; i < s.length(); i++) {
                ct[s.charAt(i) - 'a']++;
            }
            boolean second = false;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= 'z' - 'a'; i++) {
                if (ct[i] == 0 || k == 0) {
                    continue;
                }
                if (second && k <= ct[i]) {
                    out.println(toChar(i));
                    continue outer;
                }
                int c = Math.min(k, ct[i]);
                k -= c;
                ct[i] -= c;
                sb.append(toChar(i));
                second = true;
            }
            int c = 0;
            int num = 0;
            int iLeft = 0;
            for (int i = 0; i <= 'z' - 'a'; i++) {
                if (ct[i] == 0) {
                    continue;
                }
                c++;
                num = ct[i];
                iLeft = i;
            }
            if (c > 1) {
                for (int i = 0; i <= 'z' - 'a'; i++) {
                    while (ct[i] > 0) {
                        sb.append(toChar(i));
                        ct[i]--;
                    }
                }
                out.println(sb);
                continue;
            }
            for (int i = 0; i < (num + kk - 1) / kk; i++) {
                sb.append(toChar(iLeft));
            }
            out.println(sb);
        }
    }

    private static char toChar(int i) {
        return (char) (i + 'a');
    }
}
