package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_16940 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] list = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; ++i) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            list[n1].add(n2);
            list[n2].add(n1);
        }

        Queue<Integer> inputSeq = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            inputSeq.offer(Integer.parseInt(st.nextToken()));
        }
        int currNodeNum = inputSeq.poll();
        if (currNodeNum != 1) {
            System.out.println(0);
            return;
        }
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();
        boolean[] visited = new boolean[N + 1];
        visited[currNodeNum] = true;
        q.offer(currNodeNum);
        while (!q.isEmpty()) {
            currNodeNum = q.poll();
//            System.out.println("currNodeNum = " + currNodeNum);
            for (Integer nextNodeNum : list[currNodeNum]) {
                if (visited[nextNodeNum]) {
                    continue;
                }
                visited[nextNodeNum] = true;
                set.add(nextNodeNum);
            }

            while (!set.isEmpty()) {
                int num = inputSeq.poll();
//                System.out.println("num = " + num);
                if (!set.contains(num)) {
                    System.out.println(0);
                    return;
                }
                q.offer(num);
                set.remove(num);
            }
        }
        System.out.println(1);

    }
}
