class Solution {
   private boolean compareWhenWithinBound(Map.Entry<String, Integer>[] arr, int largest, int subChild) {
      return arr[largest].getValue() < arr[subChild].getValue() ||
         (arr[largest].getValue() == arr[subChild].getValue()
            && arr[largest].getKey().compareTo(arr[subChild].getKey()) > 0);
   }

   private void pushDown(Map.Entry<String, Integer>[] arr, int n, int i) {
      int cur = i;
      while (true) {
         int largest = cur;
         int left = cur * 2 + 1, right = cur * 2 + 2;

         if (left < n && compareWhenWithinBound(arr, largest, left)){
            largest = left;
         }

         if (right < n && compareWhenWithinBound(arr, largest, right)) {
            largest = right;
         }
         if (largest == cur) {
            break;
         }
         Map.Entry<String, Integer> swapVar = arr[cur];
         arr[cur] = arr[largest];
         arr[largest] = swapVar;

         cur = largest;
      }
   }

   private void heapify(Map.Entry<String, Integer>[] arr) {
      int len = arr.length;
      for (int i = len / 2;i >= 0;i --) {
         pushDown(arr, len, i);
      }
   }

   public List<String> topKFrequent(String[] words, int k) {
      // bucketing
      Map<String, Integer> buckets = new HashMap<>();
      for (String word : words) {
         buckets.put(word, buckets.getOrDefault(word, 0) + 1);
      }

      // linearify
      Map.Entry<String, Integer>[] linearBuckets = new Map.Entry[buckets.size()];
      int totalNum = 0;
      for (Map.Entry<String, Integer> entry : buckets.entrySet()) {
         linearBuckets[totalNum] = entry;
         totalNum ++;
      }

      // heapify
      heapify(linearBuckets);

      // top K frequent words
      List<String> topFrequentWords = new ArrayList<>(k);
      for (int i = 1;i <= k;i ++) {
         topFrequentWords.add(linearBuckets[0].getKey());

         linearBuckets[0] = linearBuckets[totalNum - i];
         pushDown(linearBuckets, totalNum - i, 0);
      }

      return topFrequentWords;
   }
}
