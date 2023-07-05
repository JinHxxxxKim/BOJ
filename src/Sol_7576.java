package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_7576 {
    public static int[] dx = new int[]{-1, 1, 0, 0};
    public static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        int[][] dist = new int[N][M];

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    visited[i][j] = true;
                    q.offer(new int[]{i, j});
                }
            }
        }
        int ans = 0;
        while (!q.isEmpty()) {
            int[] currNode = q.poll();
            int currRow = currNode[0];
            int currCol = currNode[1];

            for (int i = 0; i < 4; ++i) {
                int tempRow = currRow + dx[i];
                int tempCol = currCol + dy[i];
                if (tempRow >= N || tempRow < 0 || tempCol >= M || tempCol < 0) { // 범위 벗어나면 PASS
                    continue;
                }
                if (board[tempRow][tempCol] == -1 || visited[tempRow][tempCol]) { // 벽 또는 방문한 노드라면 PASS
                    continue;
                }
                dist[tempRow][tempCol] = dist[currRow][currCol] + 1;
                q.offer(new int[]{tempRow, tempCol});
                visited[tempRow][tempCol] = true;
            }
        }
        for (int i = 0; i < N; ++i) {
            System.out.println(Arrays.toString(dist[i]));

        }
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (board[i][j] == 0 && dist[i][j] == 0) {
                    System.out.println(-1);
                    return;
                } else if (board[i][j] == 0) {
                    ans = Math.max(ans, dist[i][j]);
                }
            }
        }
        System.out.println(ans);
    }
}
