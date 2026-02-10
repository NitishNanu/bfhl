package com.chitkara.bfhl.util;

import java.util.*;

public class MathUtils {

    public static List<Integer> fibonacci(int n) {
        List<Integer> res = new ArrayList<>();
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            res.add(a);
            int c = a + b;
            a = b;
            b = c;
        }
        return res;
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) return false;
        return true;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int hcf(int[] arr) {
        int res = arr[0];
        for (int i = 1; i < arr.length; i++)
            res = gcd(res, arr[i]);
        return res;
    }

    public static int lcm(int[] arr) {
        int res = arr[0];
        for (int i = 1; i < arr.length; i++)
            res = res * arr[i] / gcd(res, arr[i]);
        return res;
    }
}
