package task;

import java.io.PrintWriter;

public class CKotikGurman {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        Items items = new Items(in.nextInt(), in.nextInt(), in.nextInt());
        Items weekly = new Items(0, 0, 0);
        for (int i = 0; i < 7; i++) {
            passDay(i, weekly);
        }
        long maxDays = Integer.MIN_VALUE;
        outer:
        for (int st = 0; st < 7; st++) {
            long days = 0;
            Items cur = new Items(items.a1, items.a2, items.a3);
            int i = st;
            while (i != 0) {
                passDay(i, cur);
                if (cur.a1 < 0 || cur.a2 < 0 || cur.a3 < 0) {
                    maxDays = Math.max(days, maxDays);
                    continue outer;
                }
                i++;
                days++;
                if (i == 7) {
                    i = 0;
                }
            }
            long t1 = 0;
            long t2 = 8 * (int) 1e8;
            while (t1 < t2) {
                long m = (t1 + t2 + 1) / 2;
                boolean can = cur.a1 >= -weekly.a1 * m && cur.a2 >= -weekly.a2 * m && cur.a3 >= -weekly.a3 * m;
                if (can) {
                    t1 = m;
                } else {
                    t2 = m - 1;
                }
            }
            days += 7 * t1;
            cur.a1 += weekly.a1 * t1;
            cur.a2 += weekly.a2 * t1;
            cur.a3 += weekly.a3 * t1;
            while (cur.a1 >= 0 && cur.a2 >= 0 && cur.a3 >= 0) {
                passDay(i, cur);
                i++;
                days++;
                if (i == 7) {
                    i = 0;
                }
            }
            maxDays = Math.max(days - 1, maxDays);
        }
        out.println(maxDays);
    }

    void passDay(int day, Items items) {
        switch (day) {
            case 0:
                items.a1--;
                break;
            case 1:
                items.a2--;
                break;
            case 2:
                items.a3--;
                break;
            case 3:
                items.a1--;
                break;
            case 4:
                items.a3--;
                break;
            case 5:
                items.a2--;
                break;
            case 6:
                items.a1--;
                break;
            default:
                throw new RuntimeException("");
        }
    }

    static class Items {

        long a1;
        long a2;
        long a3;

        public Items(long a1, long a2, long a3) {
            this.a1 = a1;
            this.a2 = a2;
            this.a3 = a3;
        }
    }
}
