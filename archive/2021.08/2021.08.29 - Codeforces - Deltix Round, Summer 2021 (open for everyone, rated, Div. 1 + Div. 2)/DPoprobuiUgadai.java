package task;

import java.io.PrintWriter;

public class DPoprobuiUgadai {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        out.println("and 1 2");
        out.flush();
        long aba = in.nextInt();
        out.println("or 1 2");
        out.flush();
        long abo = in.nextInt();
        out.println("or 1 3");
        out.flush();
        long aco = in.nextInt();
        out.println("and 2 3");
        out.flush();
        long bca = in.nextInt();

        long p = 1;

        long a = 0;
        long b = 0;
        for (int i = 0; i <= 40; i++) {
            if (p >= Integer.MAX_VALUE) {
                break;
            }

            if ((aba & p) > 0) {
                a |= p;
                b |= p;
            } else if ((abo & p) != 0) {
                if ((aco & p) != 0) {
                    if ((bca & p) == 0) {
                        a |= p;
                    } else {
                        b |= p;
                    }
                } else {
                    b |= p;
                }
            }
            p <<= 1;
        }

        long[] l = new long[n];
        l[0] = a;
        l[1] = b;
        for (int h = 3; h <= n; h++) {
            out.println("and 1 " + h);
            out.flush();
            long and = in.nextInt();
            out.println("or 1 " + h);
            out.flush();
            long or = in.nextInt();
            int cur = h - 1;
            p = 1;
            for (int i = 0; i <= 40; i++) {
                if (p >= Integer.MAX_VALUE) {
                    break;
                }

                if ((and & p) > 0) {
                    l[cur] |= p;
                } else if ((or & p) != 0) {
                    if ((l[0] & p) == 0) {
                        l[cur] |= p;
                    }
                }
                p <<= 1;
            }
        }
        ArrayUtils.sort(l);
        out.println("finish " + l[k - 1]);
        out.flush();
    }
}
