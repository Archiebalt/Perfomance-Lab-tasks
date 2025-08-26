package may.code.task1;

public class Task1 {
    public static void main(String[] args) {
        int n1 = Integer.parseInt(args[0]);
        int m1 = Integer.parseInt(args[1]);
        int n2 = Integer.parseInt(args[2]);
        int m2 = Integer.parseInt(args[3]);

        System.out.println(getPath(n1, m1) + getPath(n2, m2));
    }

    private static String getPath(int n, int m) {
        StringBuilder result = new StringBuilder();
        int begin = 1;
        int end = 1;
        int state = 1;
        result.append(begin);

        while (true) {
            end++;
            state++;

            if (end > n) end = 1;

            if (state == m) {
                if (end == 1) {
                    break;
                }

                result.append(end);
                state = 1;
            }
        }

        return result.toString();
    }
}