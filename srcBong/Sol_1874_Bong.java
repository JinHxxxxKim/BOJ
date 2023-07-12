import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Sol_1974_Bong {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();

        int N = sc.nextInt();
        ArrayList<String> arrayList = new ArrayList<>();

        int[] array = new int[N];
        int index = 0;

        for (int i = 0; i < N; i++) {
            array[i] = sc.nextInt();
        }
        for (int i = 1; i <= N; i++) {
            stack.push(i);
            arrayList.add("+");
            while (!stack.isEmpty() && stack.peek() == array[index]) {
                stack.pop();
                arrayList.add("-");
                index++;
            }
        }
        if (stack.isEmpty()){
            for (String s : arrayList) {
                System.out.println(s);
            }
        }
        else{
            System.out.print("NO");
        }
    }
}

