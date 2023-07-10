package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_3055 {
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rowSize = Integer.parseInt(st.nextToken());
        int colSize = Integer.parseInt(st.nextToken());

        int goalRow = -1;
        int goalCol = -1;
        int startRow = -1;
        int startCol = -1;

        Queue<int[]> tempQ = new LinkedList<>();

        char[][] board = new char[rowSize][colSize];
        for (int i = 0; i < rowSize; ++i) {
            String str = br.readLine();
            for (int j = 0; j < colSize; ++j) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'D') {
                    goalRow = i;
                    goalCol = j;
                } else if (board[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                } else if (board[i][j] == '*') {
                    tempQ.offer(new int[]{i, j});
                }
            }
        }
        int[][] waterDist = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; ++i) {
            Arrays.fill(waterDist[i], Integer.MAX_VALUE);
        }

        while (!tempQ.isEmpty()) { // 물이 닿는 최소시간 찾기
            boolean[][] visited = new boolean[rowSize][colSize];
            int[] currWater = tempQ.poll();
            int currRow = currWater[0];
            int currCol = currWater[1];
            visited[currRow][currCol] = true;
            Queue<int[]> currWaterQ = new LinkedList<>();
            currWaterQ.offer(currWater);
            waterDist[currRow][currCol] = 0;
            while (!currWaterQ.isEmpty()) {
                int[] testWater = currWaterQ.poll();
                int waterRow = testWater[0];
                int waterCol = testWater[1];
                for (int i = 0; i < 4; ++i) {
                    int tempRow = waterRow + dx[i];
                    int tempCol = waterCol + dy[i];
                    if (tempRow >= rowSize || tempCol >= colSize || tempRow < 0 || tempCol < 0) {
                        continue;
                    }
                    if (visited[tempRow][tempCol]) {
                        continue;
                    }
                    if (board[tempRow][tempCol] == 'S' || board[tempRow][tempCol] == 'D' || board[tempRow][tempCol] == 'X') {
                        continue;
                    }
                    if (waterDist[tempRow][tempCol] <= waterDist[waterRow][waterCol] + 1) {
                        continue;
                    }
                    visited[tempRow][tempCol] = true;
                    waterDist[tempRow][tempCol] = Math.min(waterDist[tempRow][tempCol], waterDist[waterRow][waterCol] + 1);
                    currWaterQ.offer(new int[]{tempRow, tempCol});
                }
            }
        }

        int[][] userDist = new int[rowSize][colSize];
        Queue<int[]> userQ = new LinkedList<>();
        userQ.offer(new int[]{startRow, startCol});
        boolean[][] visited = new boolean[rowSize][colSize];
        visited[startRow][startCol] = true;
        userDist[startRow][startCol] = 0;

        while (!userQ.isEmpty()) {
            int[] currNode = userQ.poll();
            int currRow = currNode[0];
            int currCol = currNode[1];
            if (currRow == goalRow && currCol == goalCol) {
                break;
            }
            for (int i = 0; i < 4; ++i) {
                int tempRow = currRow + dx[i];
                int tempCol = currCol + dy[i];
                if (tempRow >= rowSize || tempCol >= colSize || tempRow < 0 || tempCol < 0) {
                    continue;
                }
                if (visited[tempRow][tempCol]) {
                    continue;
                }
                if (board[tempRow][tempCol] == 'X') {
                    continue;
                }
                if (waterDist[tempRow][tempCol] <= userDist[currRow][currCol] + 1) {
                    continue;
                }
                visited[tempRow][tempCol] = true;
                userDist[tempRow][tempCol] = userDist[currRow][currCol] + 1;
                userQ.offer(new int[]{tempRow, tempCol});
            }
        }
        if (userDist[goalRow][goalCol] == 0) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(userDist[goalRow][goalCol]);
        }

    }
}
