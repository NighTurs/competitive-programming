package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DNapryazhennayaTrenirovka {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        List<Pair<Long, Long>> a = new ArrayList<>();
        List<Pair<Long, Long>> aa = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new Pair<>(in.nextLong(), 0L));
            aa.add(new Pair<>(0L, 0L));
        }
        for (int i = 0; i < n; i++) {
            a.get(i).sc = in.nextLong();
        }
        List<List<Pair<Long, Long>>> events = new ArrayList<>(k + 1);
        for (int i = 0; i <= k; i++) {
            events.add(new ArrayList<>());
        }

        long lower = 0;
        long high = (long) 1e13;
        while (lower < high) {
            long mid = (lower + high) / 2;
            if (possible(mid, a, aa, events, k)) {
                high = mid;
            } else {
                lower = mid + 1;
            }
        }

        if (lower >= (long) 1e13) {
            out.println(-1);
        } else {
            out.println(lower);
        }
    }

    public boolean possible(long charge,
                            List<Pair<Long, Long>> a,
                            List<Pair<Long, Long>> aa,
                            List<List<Pair<Long, Long>>> events,
                            int k) {

        for (int i = 0; i <= k; i++) {
            events.get(i).clear();
        }
        for (int i = 0; i < a.size(); i++) {
            Pair<Long, Long> it = a.get(i);
            Pair<Long, Long> itCopy = aa.get(i);
            int bin = (int) Math.min(it.fs / it.sc, k);
            itCopy.fs = it.fs;
            itCopy.sc = it.sc;
            events.get(bin).add(itCopy);
        }

        int cur = 0;
        int curK = 1;
        while (curK < k) {
            while (cur < events.size() && (events.get(cur) == null || events.get(cur).isEmpty())) {
                cur++;
            }
            if (cur >= events.size()) {
                return true;
            }
            Pair<Long, Long> laptop = events.get(cur).get(events.get(cur).size() - 1);
            events.get(cur).remove(events.get(cur).size() - 1);
            if (laptop.fs - laptop.sc * (curK - 1) < 0) {
                return false;
            }
            laptop.fs += charge;
            if (laptop.fs - laptop.sc * curK < 0) {
                return false;
            }
            int bin = (int) Math.min(laptop.fs / laptop.sc, k);

            events.get(bin).add(laptop);
            cur = Math.min(cur, bin);

            curK++;
        }

        for (List<Pair<Long, Long>> l : events) {
            for (Pair<Long, Long> event : l) {
                if (event.fs - event.sc * (k - 1) < 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
