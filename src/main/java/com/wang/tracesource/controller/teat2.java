package com.wang.tracesource.controller;

/**
 * @ClassName teat2
 * @Description TODO
 * @Author WY
 * @Date 2021/9/8 19:44
 * Version 1.0
 **/
public class teat2 {




        public static void main(String[] args) {
            /*
             * 第9题: 有100个人围成一个圈，从1开始报数，报到14的这个人就要退出。
             * 然后其他人重新开始，从1报数，到14退出。问：最后剩下的是100人中的第几个人？ 分析: * 应用List集合,将100个数放入其中 *
             * 建一个int类型标识,当为14时清零 当List集合剩余最后一个不再比较 应用listIterator迭代元素,并对集合进行删除操作
             */

//            List<Integer> list = new ArrayList<Integer>();
//            for (int i = 1; i <= 100; i++) {
//                list.add(0);
//            }
//            int[] arr=new int[100/4];
//            int index=0;
//            int number=4;
//            int num;
//            int flag = 0;//放到最外层循环外面，因为人数小于14人需要下一轮共同计数
//            while (list.size() > 1) {// >1表示最后2个人报数到14最终剩1人 但要是>0只有1个人报数14后去除列表为空了会出异常
//                ListIterator<Integer> it = list.listIterator();
//                while (it.hasNext()) {
//                    num = it.next();
//                    flag++;
//                    if (flag == 4) {
//                        it.remove();
//                        flag = 0;
//                        arr[index++]=number;
//                    }
//                }
//                number+=4;
//               // System.out.println(list.size());
//            }

            System.out.println(test10());

        }

        static int test10() {
            int[] nums = new int[100];
            int cur = 0, i=0;
            int n=26;
            int flag = 0;

            while (cur<n) {
                i %= 100;
                if (nums[i] == 0 && ++flag % 4 == 0) {
                    nums[i] = 1;
                    cur++;
                }
                i++;

            }
            return i ;


        }
}
