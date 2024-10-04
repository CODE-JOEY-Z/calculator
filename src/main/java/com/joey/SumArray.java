package com.joey;

import java.text.DecimalFormat;

public class SumArray {
    public static void main(String[] args) {
        // 示例数据元素数组
        double[] numbers = {
                810.0, 921.8, -5.58, -385.17, 633.04, 921.8, 5.59, 697.5, 921.8,
                -25.81, -19.53, 1843.6, -51.62, 1341.6, 921.8, 810.0, 2.79, 2.79,
                838.5, 921.8, 1843.6, -25.81, -51.62, -23.48, 810.0, 1544.0, 1341.6,
                1341.6, -2.79, 5.58, 810.0, -3.24, 3.24, 465.09, -444.14, 697.5,
                -58.59, 1872.5, 2.79, 3.24, 3.24, 231.0, 21.0, 409.5, 810.0, -4.5,
                -21.0, 960.0, -476.09, -46.09, -1432.88, -654.19, -732.60, -58.0,
                -16.09, -5.58, -5.6
        };

        double sum = 0.0;

        // 计算数组中所有元素的总和
        for (double num : numbers) {
            sum += num;
        }

        // 打印数组长度
        int length = numbers.length;
        System.out.println(length);

        DecimalFormat df = new DecimalFormat("#.00");
        // 输出总和
        System.out.println("数组中所有元素的总和是: " + df.format(sum));
    }
}
