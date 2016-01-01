package task;

import task.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class TaskD {

    int[] t = new int[20];

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();

        t[0] = 1;
        for (int i = 1; i < 20; i++) {
            t[i] = t[i - 1] * 2;
        }

        OP[] ops = new OP[m];
        for (int i = 0; i < m; i++) {
            OP op = new OP();
            ops[i] = op;
            op.type = in.nextInt();
            if (op.type == 1) {
                op.level = in.nextInt();
                op.left = in.nextInt();
                op.right = in.nextInt();
                op.element = in.nextInt();
            } else {
                op.level = in.nextInt();
                op.pos = in.nextInt();
                op.set = new TreeSet<>();
            }
        }

        int[] ll = new int[n + 1];
        int[] lr = new int[n + 1];

        for (int i = m - 1; i >= 0; i--) {
            OP o = ops[i];
            if (o.type == 1) {
                buildPath(ll, o.level, o.left);
                buildPath(lr, o.level, o.right);
                for (int h = i + 1; h < m; h++) {
                    if (ops[h].type == 2 && ops[h].level <= o.level) {
                        OP oo = ops[h];
                        if (ll[oo.level] <= oo.pos && oo.pos <= lr[oo.level]) {
                            oo.set.add(o.element);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            if (ops[i].type == 2) {
                out.println(ops[i].set.size());
            }
        }

    }

    void buildPath(int[] levels, int level, int pos) {
        levels[level] = pos;

        for (int l = level - 1; l >= 1; l--) {
            int i = 0;
            int curp = 0, ans = 0;
            pos = levels[l + 1];

            while (true) {
                if (curp + 2 >= pos) {
                    ans = t[i];
                    break;
                } else if (curp + 2 + t[i + 1] - t[i] - 1 >= pos) {
                    ans = t[i] + (pos - curp - 2);
                    break;
                }
                curp += 2 + t[i + 1] - t[i] - 1;
                i++;
            }

            levels[l] = ans;
        }
    }

    public static class OP {
        int type;
        int left, right;
        int element;
        
        int level, pos;
        Set<Integer> set;
    }

/*    public static void main(String[] args) {
        TaskD d = new TaskD();
        d.t[0] = 1;
        for (int i = 1; i < 20; i++) {
            d.t[i] = d.t[i - 1] * 2;
        }
        int[] ar = new int[10];
        d.buildPath(ar, 4, 8);
        for (int i = 0; i < 10; i++) {
            System.out.print(i + ":" + ar[i] + " ");
        }
    }*/
}
