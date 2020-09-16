import java.util.*;

public static class Sep16 {

    // 1. Leetcode 632. Smallest Range Covering Elements from K Lists
    // https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
    
    // You have k lists of sorted integers in non-decreasing order. Find the
    // smallest range that includes at least one number from each of the k lists.
    // We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a
    // < c if b - a == d - c
    public int[] smallestRange(List<List<Integer>> nums) {

        int k = nums.size();

        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (a, b) -> a[0] - b[0]);
        // Lambda expression of Comparator
        int startVal = (int) 1e8, max = -(int) 1e8; // startVal and endVal of range
        int ansLen = (int) 1e8;
        int ans[] = new int[2];

        for (int i = 0; i < k; i++) {

            List<Integer> currList = nums.get(i);
            int val = currList.get(0);

            // update max endVal so that we can include every list elt
            max = Math.max(max, val);

            pq.add(new int[] { val, i, 0 });
            // value, listNumber, index in list

        }

        boolean oneListReachedEnd = false;
        // run loop until not reached in end of any list
        while (oneListReachedEnd == false) {

            int[] arr = pq.remove();
            startVal = arr[0];
            // min idx now
            int currLen = max - startVal + 1;

            if (ansLen > currLen) {
                // if range is smaller then update
                ansLen = Math.min(ansLen, currLen);
                ans[0] = startVal;
                ans[1] = max;
            }

            for (int i = 0; i < k; i++) {

                if (arr[1] == i) {
                    int idx = arr[2] + 1;
                    List<Integer> currList = nums.get(i);

                    if (idx != currList.size()) {

                        int val = currList.get(idx);
                        max = Math.max(max, val);
                        pq.add(new int[] { val, i, idx });

                    } else {
                        oneListReachedEnd = true;
                        break;
                        // no elt left in current list and now we cannot get
                        // range which covers all lists elt
                    }
                    break;
                    // elt added in the list
                    // so no need to check other lists
                }
            }
        }
        return ans;
    }

    // 2. Leetcode 345. Reverse Vowels of a String

    // Write a function that takes a string as input and reverse only the vowels of
    // a string.

    public String reverseVowels(String s) {

        char[] arr = s.toCharArray();
        int n = arr.length;

        HashSet<Character> hs = new HashSet<>();

        char vowels[] = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };
        for (char vowel : vowels) {
            hs.add(vowel);
        }

        int i = 0, j = n - 1;

        while (i < j) {

            while (i < j && !hs.contains(arr[i])) {
                i++;
                // move left pointer to vowel index
            }

            while (i < j && !hs.contains(arr[j])) {
                j--;
                // move right pointer to vowel idx
            }
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
            // move the pointers after swapping
        }

        return String.valueOf(arr);
    }

    // 5. Leetcode 838. Push Dominoes
    // https://leetcode.com/problems/push-dominoes/


    public String pushDominoes(String S) {
        
        // Add boundaries so we do not need to handle corner cases
        char[] arr = ('L' + S + 'R').toCharArray();
        
        int n = arr.length;
        int start = 0 , end = 0;
        
        while(end < n) {
            
            char curr = arr[end];
            
            if(curr != '.') {
                
                convert(arr,start,end);
                start = end;
                // for next window of dominoes
            }
            end++;
        }
        // remove boundaries when returning answer
        return String.valueOf(arr).substring(1, n - 1);
    }
    
    public void convert(char[] arr, int l, int r) {
        
        char left = arr[l];
        char right = arr[r];
        
        if( left == 'L' && right == 'L' ) {

            while(l != r) {
                
                arr[l++] = 'L';
            }

        } else if( left == 'R' && right == 'R' ) {

            while(l != r) {
                arr[r--] = 'R';
            }

        } else if( left == 'L' && right == 'R') {

            return;
            // nothing to do as no falling domino will make 
            // others inside the range to fall as l will fall 
            // left side and r will right side of range

        } else if( left == 'R' && right == 'L' ) {

            if( r - l % 2 == 0) {
                // even case (ie. all the dominoes will be changed (like RRRLLL))
                while(l < r) {
                    arr[l++] = 'R';
                    arr[r--] = 'L';
                }

            } else {
            // even case (ie. dominoes will be changed except middleOne (like RRR.LLL))
                
                while(l < r) {
                    // in odd we do not have to move center domino ans that too is 
                    // is handled without <= condition as we do not want to change that
                    
                    arr[l++] = 'R';
                    arr[r--] = 'L';
                }
            }
        }
    }

    
}