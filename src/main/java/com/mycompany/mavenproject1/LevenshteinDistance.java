package com.mycompany.mavenproject1;

public class LevenshteinDistance {
    public static int levenshteinDistance2(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[2][n + 1];
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            dp[i % 2][0] = i;
            for (int j = 1; j <= n; j++) {
                int substitutionCost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i % 2][j] = Math.min(dp[i % 2][j - 1] + 1, Math.min(dp[(i - 1) % 2][j] + 1, dp[(i - 1) % 2][j - 1] + substitutionCost));
            }
        }
        return dp[m % 2][n];
    }

    //TODO: implement from stackoverflow
    public static int levenshteinDistance(String s1, String s2) {
        return calculate3(s1.toCharArray(), s2.toCharArray());
    }

    public static int calculate3(char[] s1, char[] s2) {

        // memoize only previous line of distance matrix
        int[] prev = new int[s2.length + 1];

        for (int j = 0; j < s2.length + 1; j++) {
            prev[j] = j;
        }

        for (int i = 1; i < s1.length + 1; i++) {

            // calculate current line of distance matrix
            int[] curr = new int[s2.length + 1];
            curr[0] = i;

            for (int j = 1; j < s2.length + 1; j++) {
                int d1 = prev[j] + 1;
                int d2 = curr[j - 1] + 1;
                int d3 = prev[j - 1];
                if (s1[i - 1] != s2[j - 1]) {
                    d3 += 1;
                }
                curr[j] = Math.min(Math.min(d1, d2), d3);
            }

            // define current line of distance matrix as previous
            prev = curr;
        }
        return prev[s2.length];
    }
}
