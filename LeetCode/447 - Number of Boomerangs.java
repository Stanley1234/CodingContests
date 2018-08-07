class Solution {
    private class Coord {
		private int x, y;
		public Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private Map<Integer, List<Coord>> computeDistTo(int relX, int relY, int[][] points) {
		Map<Integer, List<Coord>> distMap = new HashMap<>();
        for (int[] coord : points) {
        	int distSqr = (coord[0] - relX) * (coord[0] - relX) + (coord[1] - relY) * (coord[1] - relY);
        	if (distMap.containsKey(distSqr)) {
        		distMap.get(distSqr).add(new Coord(coord[0], coord[1]));
        	} else {
        		List<Coord> coords = new ArrayList<>();
        		coords.add(new Coord(coord[0], coord[1]));
        		distMap.put(distSqr, coords);
        	}
        }
        return distMap;
	}
	
	public int numberOfBoomerangs(int[][] points) {
		int totalNum = 0;
        for (int i = 0;i < points.length;i ++) {
        	Map<Integer, List<Coord>> distMap = computeDistTo(points[i][0], points[i][1], points);
        	for (int distSqr : distMap.keySet()) {
        		int curSize = distMap.get(distSqr).size();
        		totalNum += curSize * (curSize - 1);
        	}
        }
        return totalNum;
    }
}
