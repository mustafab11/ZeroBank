package com.zerobank.pages;

import java.util.Arrays;

public class ExtraPractice {

    public static void main(String[] args) {
        String a = new String("hi");
        String b = "hi";
        String c = "hi";
        System.out.println("a==b => " + (a == b));
        System.out.println("a.equals(b) => " + a.equals(b));

        System.out.println("c==b => " + (c == b));
        System.out.println("c.equals(b) => " + c.equals(b));

        System.out.println("-------------");
        int[] arr1 = {1,3,2};
        int[] arr2 = {1,2,3};
        int[] arr3 = new int[arr1.length + arr2.length];

        System.out.println("(arr1 == arr2) = " + (arr1 == arr2));
        System.out.println("arr1.equals(arr2) = " + arr1.equals(arr2));
        System.out.println("arr1.toString().equals(arr2.toString()) = " + arr1.toString().equals(arr2.toString()));
        System.out.println("Arrays.equals(arr1,arr2) = " + Arrays.equals(arr1, arr2));

        for (int i = 0; i <arr3.length ; i++) {
            arr3[i]=arr1[i];
        }





    }
}
