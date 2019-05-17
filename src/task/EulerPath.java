package task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EulerPath {

    static List<Integer> eulerPath(Map<Integer, Map<Integer, Integer>> mp) {
        int first = -1;
        int second = -1;
        int some = 0;
        for (Map.Entry<Integer, Map<Integer, Integer>> e : mp.entrySet()) {
            some = e.getKey();
            if (e.getValue().values().stream().mapToInt(i -> i).sum() % 2 == 0) {
                continue;
            }
            if (first == -1) {
                first = e.getKey();
            } else if (second == -1) {
                second = e.getKey();
            } else {
                return null;
            }
        }
        if (first != -1 && second == -1) {
            return null;
        }
        if (first != -1) {
            mp.get(first).putIfAbsent(second, 0);
            mp.get(second).putIfAbsent(first, 0);
            mp.get(first).put(second, mp.get(first).get(second) + 1);
            mp.get(second).put(first, mp.get(second).get(first) + 1);
        }
        List<Integer> res = new ArrayList<>();

        formCycle(some, mp, res);

        if (mp.entrySet().stream().anyMatch(e -> e.getValue().entrySet().stream().anyMatch(x -> x.getValue() != 0))) {
            return null;
        }
        if (first != -1) {
            List<Integer> res2 = new ArrayList<>();
            for (int i = 0; i < res.size() - 1; i++) {
                if ((res.get(i) == first && res.get(i + 1) == second) || (res.get(i) == second
                        && res.get(i + 1) == first)) {
                    for (int h = i + 1; h < res.size(); h++) {
                        res2.add(res.get(h));
                    }
                    for (int h = 1; h <= i; h++) {
                        res2.add(res.get(h));
                    }
                    res = res2;
                    break;
                }
            }
        }
        return res;
    }

    static void formCycle(int cur, Map<Integer, Map<Integer, Integer>> mp, List<Integer> res) {
        Map<Integer, Integer> inner = mp.get(cur);
        //noinspection KeySetIterationMayUseEntrySet
        inner.keySet().forEach(k -> {
            if (inner.get(k) > 0) {
                inner.put(k, inner.get(k) - 1);
                mp.get(k).put(cur, mp.get(k).get(cur) - 1);
                formCycle(k, mp, res);
            }
        });
        res.add(cur);
    }
}
