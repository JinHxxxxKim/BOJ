package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_16947 {
    static class Node{
        private int nodeNum;

        public Node(int nodeNum) {
            this.nodeNum = nodeNum;
        }

        public int getNodeNum() {
            return nodeNum;
        }

        public void setNodeNum(int nodeNum) {
            this.nodeNum = nodeNum;
        }
    }
    static ArrayList<Node>[] adjacencyList;
    static boolean[] isCycle, isVisited;
    static int cycleStart = -1;
    static int cycleEnd = -1;

    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        adjacencyList = new ArrayList[N+1];
        isCycle = new boolean[N + 1];
        isVisited = new boolean[N + 1];

        for (int i = 1; i < N + 1; ++i) {
            adjacencyList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            adjacencyList[n1].add(new Node(n2));
            adjacencyList[n2].add(new Node(n1));
        }
        // 먼저 사이클을 형성하는 노드들을 알아내야함
        dfs(1, 1);
        // 사이클까지의 거리
        for (int i = 1; i < N + 1; ++i) {
            if (isCycle[i]) {
                sb.append(0 + " ");
            } else {
                Queue<Node> q = new LinkedList<>();
                int[] dist = new int[N + 1];
                isVisited = new boolean[N + 1];

                q.offer(new Node(i));
                while (!q.isEmpty()) {
                    Node currNode = q.poll();
                    if (isVisited[currNode.getNodeNum()]) {
                        continue;
                    }
                    if (isCycle[currNode.getNodeNum()]) {
                        sb.append(dist[currNode.getNodeNum()] + " ");
                        break;
                    }
                    isVisited[currNode.getNodeNum()] = true;
                    for (Node node : adjacencyList[currNode.getNodeNum()]) {
                        q.offer(node);
                        dist[node.getNodeNum()] = dist[currNode.getNodeNum()] + 1;
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int parent, int curr) {
        isVisited[curr] = true;
        for (Node node : adjacencyList[curr]) {
            int nextNodeNum = node.getNodeNum();
            if (nextNodeNum == parent) {
                continue;
            }

            if (isVisited[nextNodeNum] && nextNodeNum != cycleEnd) {
                isCycle[curr] = true;
                cycleStart = nextNodeNum;
                cycleEnd = curr;
                continue;
            }
            dfs(curr, nextNodeNum);
            if (isCycle[nextNodeNum] && nextNodeNum != cycleStart) {
                isCycle[curr] = true;
                return;
            }
        }
    }
}
