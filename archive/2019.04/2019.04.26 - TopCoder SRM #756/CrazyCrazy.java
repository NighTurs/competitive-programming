package task;

import java.util.ArrayList;

public class CrazyCrazy {

    public String possible(String song) {
        if (doit(song, new ArrayList<Character>(), 0, 0)) {
            return "possible";
        } else {
            return "impossible";
        }
    }

    private boolean doit(String s, ArrayList<Character> first, int pos, int posMatch) {
        if (pos >= s.length()) {
            if (first.size() == posMatch) {
                return true;
            } else {
                return false;
            }
        }
        int ct = 0;
        int i = pos;
        while (i < s.length() && s.charAt(pos) == s.charAt(i)) {
            i++;
            ct++;
        }
        for (i = 0; i <= ct; i++) {
            for (int h = 0; h < i; h++) {
                first.add(s.charAt(pos));
            }

            int toMatch = ct - i;
            int newPosMatch = posMatch;
            while (toMatch > 0 && newPosMatch < first.size() && first.get(newPosMatch) == s.charAt(pos)) {
                toMatch--;
                newPosMatch++;
            }
            if (toMatch == 0) {
                if (doit(s, first, pos + ct, newPosMatch)) {
                    return true;
                }
            }
            for (int h = 0; h < i; h++) {
                first.remove(first.size() - 1);
            }
        }
        return false;
    }
}
