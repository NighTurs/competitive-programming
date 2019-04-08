package task;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MoreSquares {

    public int countLocations(int N, int SX, int SY, int[] Xprefix, int[] Yprefix) {

        Point[] points = new Point[N];

        for (int i = 0; i < Xprefix.length; i++) {
            points[i] = new Point(Xprefix[i], Yprefix[i]);
        }

        for (int i = Xprefix.length; i < N; i++) {
            points[i] = new Point((points[i - 1].x * 47 + 42) % SX, (points[i - 1].y * 47 + 42) % SY);
        }

        Set<Point> origS = new HashSet<>();
        Set<Point> newS = new HashSet<>();

        for (int i = 0; i < N; i++) {
            origS.add(points[i]);
        }

        Point c = new Point(0, 0);
        Point d = new Point(0, 0);
        for (Point a : origS) {
            for (Point b : origS) {
                if (a.equals(b)) {
                    continue;
                }
                int diffX = a.x - b.x;
                int diffY = a.y - b.y;
                c.x = a.x + diffY;
                c.y = a.y - diffX;
                d.x = c.x - diffX;
                d.y = c.y - diffY;

//                if (!(dist(a, b) == dist(a, c) && dist(a, c) == dist(c, d) && dist(a, b) == dist(d, b))) {
//                    throw new RuntimeException();
//                }

                boolean cContains = origS.contains(c);
                boolean dContains = origS.contains(d);
                if (cContains && !dContains) {
                    newS.add(new Point(d.x, d.y));
                }
                if (!cContains && dContains) {
                    newS.add(new Point(c.x, c.y));
                }

                c.x = a.x - diffY;
                c.y = a.y + diffX;
                d.x = c.x - diffX;
                d.y = c.y - diffY;

//                if (!(dist(a, b) == dist(a, c) && dist(a, c) == dist(c, d) && dist(a, b) == dist(d, b))) {
//                    throw new RuntimeException();
//                }

                cContains = origS.contains(c);
                dContains = origS.contains(d);
                if (cContains && !dContains) {
                    newS.add(new Point(d.x, d.y));
                }
                if (!cContains && dContains) {
                    newS.add(new Point(c.x, c.y));
                }
            }
        }


        return newS.size();
    }

    public int dist(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    public static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
