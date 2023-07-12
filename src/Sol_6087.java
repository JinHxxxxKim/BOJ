package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_6087 {
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int colSize, rowSize;
        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());
        int goalRow=0, goalCol=0;
        char[][] board = new char[rowSize][colSize];
        boolean[][] visited = new boolean[rowSize][colSize];
        int[][] dist = new int[rowSize][colSize];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < rowSize; ++i) {
            String temp = br.readLine();
            for (int j = 0; j < colSize; ++j) {
                board[i][j] = temp.charAt(j);
                if (board[i][j] == 'C' && q.isEmpty()) {
                    q.offer(new int[]{i, j});
                    dist[i][j] = -1;
                    visited[i][j] = true;
                } else if (board[i][j] == 'C' && !q.isEmpty()) {
                    goalRow = i;
                    goalCol = j;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] currNode = q.poll();
            int currRow = currNode[0];
            int currCol = currNode[1];

            for (int i = 0; i < 4; ++i) {
                int tempRow = currRow + dx[i];
                int tempCol = currCol + dy[i];
                while (!(tempRow >= rowSize || tempCol >= colSize || tempRow < 0 || tempCol < 0) && !(board[tempRow][tempCol] == '*')) {
                    if (visited[tempRow][tempCol]) {
                        tempRow = tempRow + dx[i];
                        tempCol = tempCol + dy[i];
                        continue;
                    }
                    dist[tempRow][tempCol] = dist[currRow][currCol] + 1;
                    q.offer(new int[]{tempRow, tempCol});
                    visited[tempRow][tempCol] = true;
                    tempRow = tempRow + dx[i];
                    tempCol = tempCol + dy[i];
                }
            }
            ;
        }
        System.out.println(dist[goalRow][goalCol]);
    }
}
