package task;

public class NewBanknote {

    int[] coins = new int[]{1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000};

    public int[] fewestPieces(int newBanknote, int[] amountsToPay) {
        int[] ans = new int[amountsToPay.length];

        for (int i = 0; i < amountsToPay.length; i++) {
            int amount = amountsToPay[i];
            int init = doit(amount);
            int min = init;
            for (long h = 1; h <= init; h++) {
                long minus = h * newBanknote;
                long p = amount - minus;
                if (p >= 0) {
                    int ct = doit((int)p);
                    if (ct + h < min) {
                        min = ct + (int)h;
                    }
                }
            }
            ans[i] = min;
        }
        return ans;
    }

    int doit(int price) {
        int ans = 0;
        for (int h = coins.length - 1; h >= 0; h--) {
            int ct = price / coins[h];
            price -= ct * coins[h];
            ans += ct;
        }
        return ans;
    }
}
