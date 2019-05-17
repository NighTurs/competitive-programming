package task;

public class DSU {

    int[] parent;
    int[] size;

    public DSU(int n) {
        this.parent = new int[n];
        this.size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    int findSet(int v) {
        if (v == parent[v]) {
            return v;
        }
        parent[v] = findSet(parent[v]);
        return parent[v];
    }

    @SuppressWarnings("AssignmentToMethodParameter")
    void unionSets(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        if (a != b) {
            if (size[a] < size[b]) {
                int z = a;
                a = b;
                b = z;
            }
            parent[b] = a;
            size[a] += size[b];
        }
    }
}
