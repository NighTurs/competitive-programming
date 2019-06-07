package task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MedianFaking {

    public int[] minimize(int F, int M, int[] data, int goal) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < F; i++) {
            items.add(new Item(i, 0, 0));
        }

        int less = 0;
        int greater = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] < goal) {
                less++;
                items.get(i / M).less++;
            }
            if (data[i] > goal) {
                greater++;
                items.get(i / M).great++;
            }
        }

        int[] first = doit(true, data, items, less, greater);
        int[] second = doit(false, data, items, less, greater);
        if (first[0] > second[0] || (first[0] == second[0] && first[1] > second[1])) {
            first = second;
        }

        return first;
    }


    int[] doit(boolean isGreater, int[] data, List<Item> items, int less, int greater) {
        int mid = data.length / 2 - (data.length % 2 == 0 ? 1 : 0);
        int x = 0;
        int y = 0;
        if (less <= mid && mid <= data.length - 1 - greater) {
            return new int[]{x, y};
        }
        items.sort(Comparator.comparingInt(a -> isGreater ? -a.great : -a.less));
        for (int i = 0; i < items.size(); i++) {
            x++;
            int ct = isGreater ? items.get(i).great : items.get(i).less;
            for (int h = 0; h < ct; h++) {
                if (isGreater) {
                    greater--;
                } else {
                    less--;
                }
                y++;
                if (less <= mid && mid <= data.length - 1 - greater) {
                    return new int[]{x, y};
                }
            }
        }
        return new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
    }

    class Item {

        int idx;
        int less;
        int great;

        public Item(int idx, int less, int great) {
            this.idx = idx;
            this.less = less;
            this.great = great;
        }
    }
}
