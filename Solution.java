class Solution {
    private static final int MOD = 1_000_000_007;

    public int numWays(String[] words, String target) {
        int m = target.length();
        int n = words[0].length();

        // Step 1: Precompute the frequency of each character at each position in words
        int[][] freq = new int[26][n];
        for (String word : words) {
            for (int j = 0; j < n; j++) {
                freq[word.charAt(j) - 'a'][j]++;
            }
        }

        // Step 2: Dynamic programming table
        // dp[i][j] = Number of ways to form target[0..i-1] using the first j columns of words
        int[][] dp = new int[m + 1][n + 1];
        
        // Base case: There is one way to form an empty target
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 1;
        }

        // Step 3: Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Option 1: Skip the j-th column
                dp[i][j] = dp[i][j - 1];

                // Option 2: Use the j-th column if it matches target[i-1]
                char targetChar = target.charAt(i - 1);
                int charFreq = freq[targetChar - 'a'][j - 1];
                if (charFreq > 0) {
                    dp[i][j] = (dp[i][j] + (int)((long)dp[i - 1][j - 1] * charFreq % MOD)) % MOD;
                }
            }
        }

        return dp[m][n];
    }
}
