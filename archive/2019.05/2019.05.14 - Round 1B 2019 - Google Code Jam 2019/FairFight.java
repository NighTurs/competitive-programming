package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

public class FairFight {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        List<Pair<Integer, Integer>> a = new ArrayList<>();
        List<Pair<Integer, Integer>> b = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new Pair<>(i, in.nextInt()));
        }
        for (int i = 0; i < n; i++) {
            b.add(new Pair<>(i, in.nextInt()));
        }
        a.sort(Comparator.comparingInt(x -> -x.sc));
        b.sort(Comparator.comparingInt(x -> -x.sc));
        TreeSet<Integer> include = new TreeSet<>();
        TreeSet<Integer> exclude = new TreeSet<>();
        TreeSet<Integer> counted = new TreeSet<>();
        int idxAdd = 0;
        int idxRemove = 0;
        int idxHigher = 0;
        long ans = 0;
        for (int i = 0; i < a.size(); i++) {
            int aVal = a.get(i).sc;
            int aIdx = a.get(i).fs;
            while (idxAdd < n && b.get(idxAdd).sc >= aVal - k) {
                include.add(b.get(idxAdd).fs);
                idxAdd++;
            }
            while (idxRemove < n && b.get(idxRemove).sc > aVal + k) {
                include.remove(b.get(idxRemove).fs);
                idxRemove++;
            }
            while (idxHigher < n && b.get(idxHigher).sc > aVal + k) {
                exclude.add(b.get(idxHigher).fs);
                idxHigher++;
            }
            long inclR = Optional.ofNullable(include.ceiling(aIdx)).orElse(n);
            long inclL = Optional.ofNullable(include.floor(aIdx)).orElse(-1);
            long exclR = Optional.ofNullable(exclude.ceiling(aIdx)).orElse(n) - 1;
            long exclL = Optional.ofNullable(exclude.floor(aIdx)).orElse(-1) + 1;
            long ctR = Optional.ofNullable(counted.ceiling(aIdx)).orElse(n) - 1;
            long ctL = Optional.ofNullable(counted.floor(aIdx)).orElse(-1) + 1;
            long rFit = Math.max(0, Math.min(exclR, ctR) - inclR + 1);
            long lFit = Math.max(0, inclL + 1 - Math.max(exclL, ctL));
            if (inclL == aIdx) {
                lFit--;
                rFit--;
                ans++;
            }
            ans += lFit + rFit + lFit * Math.max(0, (Math.min(exclR, ctR) - aIdx))
                    + rFit * Math.max(0, (aIdx - Math.max(exclL, ctL))) - lFit * rFit;
            counted.add(aIdx);
        }
        out.println(String.format("Case #%d: %d", testNumber, ans));
    }
}
