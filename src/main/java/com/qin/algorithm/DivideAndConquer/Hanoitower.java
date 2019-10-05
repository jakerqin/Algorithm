package com.qin.algorithm.DivideAndConquer;

/**
 * 分治算法解汉罗塔问题
 * 三个步骤：
     * 分解：将原问题分解为若干个规模较小，相互独立，与原问题形式相同的子问题
     * 解决：若子问题规模较小而容易被解决则直接解，否则递归地解各个子问题
     * 合并：将各个子问题的解合并为原问题的解
 */
public class Hanoitower {

    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }

    //汉诺塔的移动的方法
    //使用分治算法

    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if(num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            //如果我们有 n >= 2 情况，我们总是可以看做是两个盘 1.最下边的一个盘 2. 上面的所有盘
            //1. 先把 最上面的所有盘 A->B， 移动过程会使用到 c
            hanoiTower(num - 1, a, c, b);
            //2. 把最下边的盘 A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            //3. 把B塔的所有盘 从 B->C , 移动过程使用到 a塔
            hanoiTower(num - 1, b, a, c);

        }
    }
}
