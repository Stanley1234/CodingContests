class Solution {
    public int leastBricks(List<List<Integer>> wall) {
      Set<Integer> cutPoints = new HashSet<>();
      for (List<Integer> row : wall) {
         for (int i = 0;i < row.size();i ++) {
            if (i >= 1) {
               row.set(i, row.get(i) + row.get(i - 1));
            }
            cutPoints.add(row.get(i));
         }
         cutPoints.remove(row.get(row.size() - 1)); // width of a row
      }

      int heightOfWall = wall.size();
      int minBricks = heightOfWall;

      for (int point : cutPoints) {
         int cut = 0;

         for (int i = 0;i < heightOfWall;i ++) {
            if (wall.get(i).contains(point)) {
               cut ++;
            }
         }
         minBricks = Math.min(minBricks, heightOfWall - cut);
      }

      return minBricks;
   }
}
