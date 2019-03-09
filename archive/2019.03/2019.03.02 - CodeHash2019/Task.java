package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Task {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        Map<String, Integer> tagToId = new HashMap<>();

        int nTags = 0;
        int n = in.nextInt();

        List<Picture> pics = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String orient = in.next();
            int m = in.nextInt();
            List<Integer> tags = new ArrayList<>();
            for (int h = 0; h < m; h++) {
                String tag = in.next();
                if (!tagToId.containsKey(tag)) {
                    tagToId.put(tag, nTags);
                    nTags++;
                }
                tags.add(tagToId.get(tag));
            }
            tags.sort(Integer::compare);
            pics.add(new Picture(i, orient.equals("H"), tags));
        }
        Collections.shuffle(pics);

        List<Integer> prevTags = pics.get(0).tagIds;
        List<String> outs = new ArrayList<>();
        Set<Integer> taken = new HashSet<>();
        boolean first = true;

        int overallScore = 0;
        while (true) {
            List<Integer> finalPrevTags = prevTags;
            Optional<Pair<Integer, Picture>> opt = pics.parallelStream()
                    .filter(pic -> !taken.contains(pic.id))
                    .map(pic -> Pair.of(scoreTags(finalPrevTags, pic.tagIds, Collections.emptyList()), pic))
                    .max(Comparator.comparingInt(x -> x.fs));
            if (!opt.isPresent()) {
                break;
            }
            Picture p1 = opt.get().sc;
            taken.add(p1.id);
            int score = opt.get().fs;
            if (!p1.isHorizontal) {
                opt = pics.parallelStream()
                        .filter(pic -> !taken.contains(pic.id) && !pic.isHorizontal)
                        .map(pic -> Pair.of(scoreTags(finalPrevTags, p1.tagIds, pic.tagIds), pic))
                        .max(Comparator.comparingInt(x -> x.fs));
                if (!opt.isPresent()) {
                    break;
                }
                Picture p2 = opt.get().sc;
                taken.add(p2.id);
                Set<Integer> tags = new HashSet<>(p1.tagIds);
                tags.addAll(p2.tagIds);
                List<Integer> sTags = new ArrayList<>(tags);
                Collections.sort(sTags);
                prevTags = sTags;
                outs.add(String.format("%d %d", p1.id, p2.id));
                score = opt.get().fs;
            } else {
                prevTags = p1.tagIds;
                outs.add(Integer.toString(p1.id));
            }
            if (!first) {
                overallScore += score;
            }
            first = false;
            if (outs.size() % 100 == 0) {
                System.out.println("Processed " + outs.size());
            }
        }

        System.out.println(overallScore);
        out.println(outs.size());
        for (String o : outs) {
            out.println(o);
        }
    }

    public int scoreTags(List<Integer> tagsA, List<Integer> tagsB, List<Integer> tagsC) {
        int aPos = 0;
        int bPos = 0;
        int cPos = 0;
        int same = 0;
        int onlyA = 0;
        int onlyB = 0;
        while (aPos < tagsA.size() || bPos < tagsB.size() || cPos < tagsC.size()) {
            int a = aPos < tagsA.size() ? tagsA.get(aPos) : Integer.MAX_VALUE;
            int b = bPos < tagsB.size() ? tagsB.get(bPos) : Integer.MAX_VALUE;
            int c = cPos < tagsC.size() ? tagsC.get(cPos) : Integer.MAX_VALUE;
            int m = Math.min(a, Math.min(b, c));
            if (a == m) {
                aPos++;
            }
            if (b == m) {
                bPos++;
            }
            if (c == m) {
                cPos++;
            }
            if (a == m && (b == m || c == m)) {
                same++;
            } else if (a == m) {
                onlyA++;
            } else {
                onlyB++;
            }
        }
        return Math.min(same, Math.min(onlyA, onlyB));
    }

    public static class Picture {

        int id;
        boolean isHorizontal;
        List<Integer> tagIds;

        public Picture(int id, boolean isHorizontal, List<Integer> tagIds) {
            this.id = id;
            this.isHorizontal = isHorizontal;
            this.tagIds = tagIds;
        }
    }
}
