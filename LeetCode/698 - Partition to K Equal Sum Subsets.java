class Solution {
    
    // note:
    // Memorization cannot be achieved by a boolean array
    // since you cannot know if a state is visited before by only two values: true, false

    private enum Memo { True, False; }

    // DFS + Memorization
    private Memo dfs(int each, int used, int todo, int[] nums, Memo[] visited) {
        if (visited[used] != null) {
            return visited[used];
        }

        int need = (todo - 1) % each + 1;   // equiv to   todo % k == 0 ? k : todo;
        visited[used] = Memo.False;

        for (int i = 0;i < nums.length;i ++) {
            if (nums[i] <= need && (used & (1 << i)) == 0) {
                if (dfs(each, used | (1 << i), todo - nums[i], nums, visited) == Memo.True) {
                    visited[used] = Memo.True;
                    break;
                }
            }
        }
        return visited[used];
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) { return false; }

        Memo[] visited = new Memo[1 << nums.length];
        visited[(1 << nums.length) - 1] = Memo.True; // the final state could be achieved

        return dfs(sum / k, 0, sum, nums, visited) == Memo.True;
    }
}
