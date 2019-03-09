package task;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

public class DMaksimizaciyaKolichestvaNulei {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long a[] = new long[n];
        long b[] = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextLong();
        }

        HashMap<Div, Integer> m = new HashMap<>();

        int ans = 0;
        for (int i = 0; i < n; i++) {
            long d1 = -b[i];
            long d2 = a[i];
            long sign = (d1 <= 0 && d2 <= 0) || (d1 >= 0 && d2 >= 0) ? 1 : 0;
            d1 = Math.abs(d1);
            d2 = Math.abs(d2);
            long g = (d2 == 0 || d1 == 0) ? 1 : gcd(d1, d2);
            d1 /= g;
            d2 /= g;

            if (d2 == 0 && d1 == 0) {
                ans++;
                continue;
            }
            if (d2 == 0 && d1 != 0) {
                continue;
            }
            Div d;
            if (d2 != 0 && d1 == 0) {
                d = new Div(0, 0, 0);
            } else {
                d = new Div(sign, d1, d2);
            }

            m.putIfAbsent(d, 0);
            m.put(d, m.get(d) + 1);
        }

        Optional<Integer> opt = m.values().stream().max(Integer::compareTo);
        if (opt.isPresent()) {
            out.println(ans + opt.get());
        } else {
            out.println(ans);
        }
    }

    long gcd(long a, long b) {
        while (b != 0) {
            a %= b;
            long z = a;
            a = b;
            b = z;
        }
        return a;
    }

    static class Div {

        long sign;
        long a, b;

        public Div(long sign, long a, long b) {
            this.sign = sign;
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Div div = (Div) o;
            return sign == div.sign && a == div.a && b == div.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(sign, a, b);
        }
    }
}
