package com.company.project.utils;

import java.util.*;

public class UnionUtil {

    public static void main(String[] args) {
        int[] numbers = {3637, 3617, 3652, 3609, 3611, 3610, 3614, 3619, 3620, 3626, 3628, 3635, 3636, 3641, 3643, 3644, 3645, 3646, 3650, 3651, 3654, 3655, 3658, 3659, 3663, 3666,
                3662, 3664, 3666, 3609, 3611, 3613, 3614, 3617, 3624, 3631, 3632, 3635, 3636, 3638, 3641, 3646, 3647, 3650, 3625, 3652, 3655, 3656, 3657,
                3658, 3659, 3615, 3661, 3623};

        Map<Integer, Integer> numberCount = new HashMap<>();

        for (int number : numbers) {
            if (numberCount.containsKey(number)) {
                int count = numberCount.get(number);
                System.out.println("数字 " + number + " 出现 " + count + " 次");
            } else {
                numberCount.put(number, 1);
            }
        }
    }

}
