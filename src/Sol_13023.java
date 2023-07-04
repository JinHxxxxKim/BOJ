package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_13023 {
    // dist가 4가 되면 종료
    static int N, M;
    static ArrayList<Integer>[] adjacencyList;
    static boolean[] visited;
    static boolean flag = false;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjacencyList = new ArrayList[N];
        for (int i = 0; i < N; ++i) {
            adjacencyList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int friend1 = Integer.parseInt(st.nextToken());
            int friend2 = Integer.parseInt(st.nextToken());
            adjacencyList[friend1].add(friend2);
            adjacencyList[friend2].add(friend1);
        }
        // 알고리즘 시작
        for (int i = 0; i < N; ++i) {
            visited = new boolean[N];

            visited[i] = true;
            calcDist(0, i);
            visited[i] = false;

            if (flag) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

    public static void calcDist(int cnt, int current) {
        if (flag) return;
        if (cnt >= 4) {
            flag = true;
            return;
        }

        for (Integer nextNode : adjacencyList[current]) {
            if (visited[nextNode]) {
                continue;
            }
            visited[nextNode] = true;
            calcDist(cnt+1, nextNode);
            visited[nextNode] = false;
        }
    }
}
