package may.code.task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String el;
            while ((el = reader.readLine()) != null) {
                nums.add(Integer.parseInt(el));
            }
        } catch (IOException exc) {
            exc.getMessage();
        }

        Collections.sort(nums);

        int limit = 20;
        int count = limit;
        int complete = 0;
        int m = nums.get(nums.size() / 2);

        for (int num : nums) {
            if (count <= 0) {
                break;
            }

            if (num == m) {
                complete++;
                continue;
            }

            if (Math.abs(num - m) <= count) {
                count -= Math.abs(num - m);
                complete++;
            } else {
                break;
            }
        }

        int answer = limit - count;

        if (complete == nums.size()) {
            System.out.println(answer);
        } else {
            System.out.println("20 ходов недостаточно для приведения всех элементов массива к одному числу");
        }
    }
}