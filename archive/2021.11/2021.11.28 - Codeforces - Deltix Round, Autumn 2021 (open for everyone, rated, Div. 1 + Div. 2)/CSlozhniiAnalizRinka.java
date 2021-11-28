package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CSlozhniiAnalizRinka {


    int N = 1000010;
    boolean[] prime = new boolean[N + 1];

    public CSlozhniiAnalizRinka() {
        prime[0] = true;
        prime[1] = true;
        for (int i = 2; i * i < N; i++) {
            for (int h = i + i; h < N; h += i) {
                prime[h] = true;
            }
        }
    }

    long query(long[] cum, int l, int r) {
        if (l == 0) {
            return cum[r];
        } else {
            return cum[r] - cum[l - 1];
        }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        long[] cum = new long[n];

        long ans = 0;

        for (int q = 0; q < m; q++) {
            List<Long> v = new ArrayList<>();
            for (int i = q; i < n; i += m) {
                v.add(a[i]);
            }
            cum[0] = v.get(0);
            for (int i = 1; i < v.size(); i++) {
                cum[i] = cum[i - 1] + v.get(i);
            }

            for (int i = 0; i < v.size(); i++) {
                if (prime[v.get(i).intValue()]) {
                    continue;
                }
                int l = 0;
                if (i != 0) {
                    int j1 = 0;
                    int j2 = i - 1;
                    while (j1 < j2) {
                        int mid = (j1 + j2) / 2;
                        if (query(cum, mid, i - 1) == (i - 1 - mid + 1)) {
                            j2 = mid;
                        } else {
                            j1 = mid + 1;
                        }
                    }
                    l = j1;
                    if (v.get(l) != 1) {
                        l = i;
                    }
                }
                int r = v.size() - 1;
                if (i != v.size() - 1) {
                    int j1 = i + 1;
                    int j2 = v.size() - 1;
                    while (j1 < j2) {
                        int mid = (j1 + j2 + 1) / 2;
                        if (query(cum, i + 1, mid) == (mid - (i + 1) + 1)) {
                            j1 = mid;
                        } else {
                            j2 = mid - 1;
                        }
                    }
                    r = j1;
                    if (v.get(r) != 1) {
                        r = i;
                    }
                }
                ans += (i - l + 1L) * (r - i + 1L) - 1;
            }

        }
        out.println(ans);
    }
}
