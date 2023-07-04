package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_16929 {
    public static boolean[][] visited;
    public static char[][] board;
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    public static int N, M;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; ++i) {
            board[i] = br.readLine().toCharArray();
        }
        boolean isHasCycle = false;
        visited = new boolean[N][M];
        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < M; ++col) {
                if (!visited[row][col]) {
                    isHasCycle = DFS_isCycle(row, col, row, col);
                    if (isHasCycle) {
                        System.out.println("YES");
                        return;
                    }
                }
            }
        }
        System.out.println("NO");
    }

    public static boolean DFS_isCycle(int parentRow, int parentCol, int row, int col) {
        visited[row][col] = true;
        for (int i = 0; i < 4; ++i) {
            int tempRow = row + dx[i];
            int tempCol = col + dy[i];
            if (tempRow < 0 || tempRow >= N || tempCol < 0 || tempCol >= M) {
                // 범위를 벗어날 경우
                continue;
            }
            if (board[row][col] != board[tempRow][tempCol]) {
                // 색이 다를 경우
                continue;
            }
            if (tempRow == parentRow && tempCol == parentCol) {
                // 부모노드인 경우
                continue;
            }
            if (visited[tempRow][tempCol]) {
                return true;
            }
            if (DFS_isCycle(row, col, tempRow, tempCol)) {
                return true;
            }
        }
        return false;
    }
}
