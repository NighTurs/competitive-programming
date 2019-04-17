package task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DejaVu {
    public int mostDejaVus(int N, int seed, int R) {
        long[] a = new long[N];
        a[0] = seed;
        for (int i = 1; i < N; i++) {
            a[i] = (a[i-1] * 1664525 + 1013904223) % 4294967296L;
        }

        long[] m = new long[N];
        for (int i = 0; i < N; i++) {
            m[i] = a[i] % R;
        }

        HashMap<Long, Entry> mp = new HashMap<>();
        int seqStart = 0;
        int maxLen = 0;

        for (int i = 0; i < N; i++) {
            mp.putIfAbsent(m[i], new Entry(i));
            Entry e = mp.get(m[i]);
            if (e.pos.size() - e.curPos > 2) {
                seqStart = e.pos.get(e.curPos) + 1;
                e.curPos++;
            }
            e.pos.add(i);
            if (maxLen < i - seqStart + 1) {
                maxLen = i - seqStart + 1;
            }
        }

        return maxLen;
    }

    static class Entry {
        int curPos = 0;
        List<Integer> pos;

        public Entry(int firstEntry) {
            this.curPos = 0;
            this.pos = new ArrayList<>();
            this.pos.add(firstEntry);
        }
    }
}
