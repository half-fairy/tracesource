package com.wang.tracesource.controller;

import java.util.Scanner;

/**
 * @ClassName Test
 * @Description TODO
 * @Author WY
 * @Date 2021/3/30 17:16
 * Version 1.0
 **/
public class Test {
    public static void sort(int[] arr)
    {
        for(int i=0;i<arr.length-1;i++)
        {
            for(int j=0;j<arr.length-i-1;j++)
            {
                if(arr[j+1]>arr[j])
                {
                    int tmp=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=tmp;
                }
            }
        }

    }
    public static int f(int a,int b)
    {
        int c;
        do{
            c=a^b;
            b=a&b<<1;
            a=c;
        }while(b!=0);
            return c;

    }
    public static int maxSum(int[][] nums) {
        if(nums==null || nums.length==0 || nums[0].length==0) {
            return 0;
        }

        int max=Integer.MIN_VALUE;
        int cur=0;
        int[] arr=null;

        for(int i=0;i!=nums.length;i++) {
            arr=new int[nums[0].length];
            for(int j=i;j!=nums.length;j++) {
                cur=0;
                for(int k=0;k!=arr.length;k++) {
                    arr[k]+=nums[j][k];
                    cur+=arr[k];
                    max=Math.max(max,cur);
                    cur=cur<0?0:cur;
                }
            }
        }

        return max;
    }
    public static int answerSheet(String s,String t)
    {
        int number=0;
        for(int i=0;i<t.length();i++)
        {
            int flag=0;
            char tmep=t.charAt(i);
            int num=-1;
            switch (tmep)
            {
                case 'A':num=1;break;
                case 'B':num=2;break;
                case 'C':num=3;break;
                case 'D':num=4;break;
            }
            for(int j=4*i;j<4*(i+1);j++)
            {
                if(num-1==j%4 )//
                {
                    if( s.charAt(j)!='1')
                    {
                        flag=1;
                    }
                }else{
                    if(s.charAt(j)!='0')
                    {
                        flag=1;
                    }
                }
            }
            if(flag==0)
            {
                number++;
            }
        }
        return number;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        str.contains("asd");
//        BigDecimal a=new BigDecimal("100");
//        a.add(new BigDecimal("100"));
//        System.out.println(a);
       int a= answerSheet("100001000101","ABC");
        System.out.println(a);
//        int[][] nums= {
//                {0,-2,-7,0},
//                {9,2,-6,2},
//                {-4,1,-4,1},
//                {-1,8,0,-2},
//        };
       // System.out.println(f(47,-42));

       // int result =maxSum(nums);

       // System.out.println("RESULT = "+result);
//        int [] arr={3,5,1,7,9,0,12};
//        sort(arr);
//        System.out.println(Arrays.toString(arr));



    }
}
