import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class alientdict {
    public String alienOrder(String[] words, int n, int k) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int len = Math.min(word1.length(), word2.length());
            for (int j = 0; j < len; j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    graph[word1.charAt(j) - 'a'].add(word2.charAt(j) - 'a');
                    break;
                }
            }
        }
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j : graph[i]) {
                inDegree[j]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append((char) (cur + 'a'));
            count++;
            for (int i : graph[cur]) {
                inDegree[i]--;
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }
        }
        if (count != n) {
            return "";
        }
        return sb.toString();
    }
}
