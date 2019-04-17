package task;

import java.util.ArrayList;
import java.util.List;

public class OneHandSort {
    public int[] sortShelf(int[] target) {

        List<Integer> actions = new ArrayList<>();
        int n = target.length;
        int[] tg = new int[n + 1];
        System.arraycopy(target, 0, tg, 0, n);
        tg[n] = -1;
        target = tg;

        int placeholder = n;
        for (int i = 0; i < n; i++) {
            if (i == target[i]) {
                continue;
            }
            for (int h = 0; h <= n; h++) {
                if (target[h] != i) {
                    continue;
                }
                if (target[i] != -1) {
                    actions.add(i);
                    target[placeholder] = target[i];
                }
                actions.add(h);
                placeholder = h;
                target[h] = -1;
                target[i] = i;
            }
        }

        int[] ans = new int[actions.size()];
        for (int i = 0; i < actions.size(); i++) {
            ans[i] = actions.get(i);
        }
        return ans;
    }
}
