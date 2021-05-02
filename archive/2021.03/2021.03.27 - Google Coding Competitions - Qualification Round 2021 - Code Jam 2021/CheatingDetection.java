package task;

import java.io.PrintWriter;

public class CheatingDetection {

    int N = 100;
    int M = 10000;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        int p = in.nextInt();
        for (int t = 0; t < tt; t++) {
            solve2(t + 1, in, out);
        }
    }

    public void solve2(int testNumber, InputReader in, PrintWriter out) {
        int ans[][] = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = in.next();
            for (int h = 0; h < M; h++) {
                ans[i][h] = (s.charAt(h) - '0');
            }
        }

        double skill[] = new double[N];
        double diff[] = new double[M];

        for (int adj = 0; adj <= 10; adj++) {
            for (int i = 0; i < N; i++) {
                double sum = 0;
                for (int h = 0; h < M; h++) {
                    sum += ans[i][h] - diff[h] - skill[i];
                }
                skill[i] += sum / M;
            }

            for (int h = 0; h < M; h++) {
                double sum = 0;
                for (int i = 0; i < N; i++) {
                    sum += ans[i][h] - skill[i] - diff[h];
                }
                diff[h] += sum / N;
            }
        }

        double rmin = 0.05;
        double rmax = 0.95;
        double rdiff = rmax - rmin;

        double mmin = Integer.MAX_VALUE;
        double mmax = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            mmin = Math.min(mmin, skill[i]);
            mmax = Math.max(mmax, skill[i]);
        }
        for (int i = 0; i < N; i++) {
            skill[i] = rmin + (skill[i] - mmin) / (mmax - mmin) * rdiff;
            skill[i] = Math.log(skill[i] / (1 - skill[i]));
        }

        mmin = Integer.MAX_VALUE;
        mmax = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            mmin = Math.min(mmin, diff[i]);
            mmax = Math.max(mmax, diff[i]);
        }
        for (int i = 0; i < M; i++) {
            diff[i] = rmin + (diff[i] - mmin) / (mmax - mmin) * rdiff;
            diff[i] = -Math.log(diff[i] / (1 - diff[i]));
        }




        double maxStd = Double.MAX_VALUE;
        int res = -1;
        for (int i = 0; i < N; i++) {
            double var = 0;
            for (int h = 0; h < M; h++) {
                var += 1 - Math.abs(ans[i][h] - (1 / (1 + Math.exp(-(skill[i] - diff[h])))));
            }
            var /= M;
            if (maxStd > var) {
                maxStd = var;
                res = i + 1;
            }
        }
        out.println(maxStd);
        out.println(String.format("Case #%d: %d", testNumber, res));

    }
}
