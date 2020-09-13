import java.util.*;

public class Sep11 {

    // SEE COPY NOTES ALSO AND SCREENSHOTS ( 7611 - 7642 )

    
    // 1. Leetcode 769. Max Chunks To Make Sorted
    // https://leetcode.com/problems/max-chunks-to-make-sorted/

    // Approach :->
    //  Here numbers given in array are whole numbers
    //  So we are taking max in current chunk and including numbers till the index(max_number is as index)
    //  when reached at that index(means maxValue and index matches) then we can make a chunk
    //  See copy for diagram
    public int maxChunksToSorted(int[] arr) {
        
        int maxIdx = -1;
        int ans = 0; // count of minimum no of cuts
        
        for(int i = 0; i < arr.length; i++) {
            
            // take max of maxelt and idx that means one chunk should 
            // include elts till max of elt or maxIdx
            
            maxIdx = Math.max(maxIdx, arr[i]);
            
            if(maxIdx == i) {
                // reached maxPartition index
                ans += 1;
            }
        }
        
        return ans;
    }

    // 2. Leecode 768. Max Chunks To Make Sorted II
    // https://leetcode.com/problems/max-chunks-to-make-sorted-ii/

    // Approach :->
    //  Here we are calculating leftMax[] and rightMin[] array 
    //  Now for any index check whether if min on right is smaller than left max that means
    //  we have to include that element as we will merge these chunks after sorting so we 
    //  have to include that element too and when right min is greater then we can make
    //  a chunck as there is no smaller element on rhs of current index
    //  At last include one more chunk for leftout elements in array
    public int maxChunksToSorted2(int[] arr) {
        int n = arr.length;
        
        int ans = 0; // no of chunks
        
        int leftMax[] = new int[n];
        int rightMin[] = new int[n];
        
        for(int i = 0;i < n;i++) {
            
            // for 0th element max elt will be its own value
            if(i == 0) leftMax[i] = arr[i];
            
            else leftMax[i] = Math.max(leftMax[i-1], arr[i]);
        }
        for(int j = n-1; j >= 0 ;j--) {
            
            // for (n-1)th element min elt will be its own value
            if(j == n-1) rightMin[j] = arr[j];
            
            else rightMin[j] = Math.min(rightMin[j+1],arr[j]);
        }
        
        // for saving extra condition for last elt
        for(int i = 0; i < n - 1; i++ ){
            
            if(leftMax[i] <= rightMin[i+1]) {
                // no minimum elt is there on rhs that means
                // we can divide current point into chunk
                
                ans++;
            }
        }
        // for last index ie. last left out elts will form one new chunk
        ans +=1;
        
        return ans;
    }

    // 3. Leetcode 238. Product of Array Except Self ( without using division sign)
    // https://leetcode.com/problems/product-of-array-except-self/

    // Approach :-> 
    //  2 solutions are there with o(n) space and o(1) space 
    //  See approach below 


    public int[] productExceptSelf(int[] nums) {
        
        // return productExceptSelf_1(nums);
        return productExceptSelf_2(nums);
    }
    

    // Approach2 :->
    //  Optimized one ( Using 0(1) space )
    //  Here we are using ans[] array as prefix product [] 
    //  For suffix we will use one elment and calculate while forming answer
    //  ie. for answer we will fill array from right to left bcz to caculate suffix value too
    //  When moving multiply suffix value with arr[i] value and use that value
    // In this way we will save the memory
    
    public int[] productExceptSelf_2( int[] arr) {
        
        int n = arr.length;
        
        int[] ans = new int[n]; // will store prefix product value in this array
        int suffix = 1; // will use this elt for suffix storing
        
        // prefix storing in answer array
        for(int i = 0 ; i < n ; i++) {
            
            if(i == 0) ans[i] = arr[i];
            
            else ans[i] = arr[i] * ans[i-1];
        }
        
        // calculating product and suffix also
        // for calculating suffix you just have to multiply arr[i] value and keep 
        // updating when going right to left
        for(int i = n-1; i >= 0; i--) {
            
            int suffixValue = suffix;
            int prefixValue = (i == 0) ? 1 : ans[i-1];
            
            suffix = suffix * arr[i]; // suffix updation for next elts
            
            ans[i] = prefixValue * suffixValue;
            
        }
        return ans;
    }
    
    // Approach 1 :->
    //  Not optimized (Using 0(2n) space)
    //  Here we are taking prefix product array and suffix product array
    //  Now for index i the product will be pre[i-1] * succ[i+1]
    //  ie. do not include own value
    public int[] productExceptSelf_1( int[] arr) {
        
        int n = arr.length;
        int[] preProd = new int[n]; // prefix product array
        int[] suffProd = new int[n]; // suffix product array
        
        for(int i = 0; i < n; i++) {

            if(i == 0) preProd[i] = arr[i];
            
            else preProd[i] = arr[i] * preProd[i-1];
        }
        for(int i = n-1; i >= 0; i--) {
            
            if(i == n-1) suffProd[i] = arr[i];
            
            else suffProd[i] = arr[i] * suffProd[i+1];
        }
        
        int[] ans = new int[n];
        
        for(int i = 0; i < n;i++) {
            
            int prefixVal = (i == 0) ? 1 : preProd[i-1];
            int suffixVal = (i == n-1) ? 1 : suffProd[i+1];
            
            ans[i] = prefixVal * suffixVal;
        }
        return ans;
    }

    // 4. Leetcode 169. Majority Element ( BOYER MOORE VOTING ALGO {LITTLE-BIT SAME AS LEVEL-UP})
    // https://leetcode.com/problems/majority-element/

    // Approach :->
    //  Think like making pairs of distinct elts and the leftout elt is majority elt
    //  also verify the element to check whether this elt is majority or not
    //  (not required in this ques)
    public int majorityElement(int[] arr) {
        
        int n = arr.length;
         int val = arr[0];
         int count = 1;
         
         for( int i = 1 ; i < n; i++ ) {
             
             if(arr[i] != val ) {
                 
                 if(count != 0) {
                     // make a pair (ie.(a,b)) of distinct elt
                     count--;
                     // decrease the count as we made a pair of val elt with current elt
                     
                 } else {
                     // this elt can be majority one
                     val = arr[i];
                     count = 1;
                 }
             } else {
                 
                 count++;
                 // same number came again so increase the count
             }
         }
         
         return val;
    }

    // 5. Leetcode 229. Majority Element II
    // https://leetcode.com/problems/majority-element-ii/

    // Approach :->
    //  Same as above question but here now you have to take 2 vals,and count 
    //  and make distinct pairs like(a,b,c), if same elt then increment the counter
    //  
    
    public List<Integer> majorityElement2(int[] arr) {
        
        // if(arr)
        if(arr.length == 0) {
            
            return new ArrayList<> ();
        }
        if(arr.length == 1) {
            
            ArrayList<Integer> ans = new ArrayList<> ();
            ans.add(arr[0]);
            return ans;
        } 
        
        int n = arr.length;
        
        int val1 = arr[0];
        int count1 = 1;
        
        int val2 = (int) -1e8;
        int count2 = 0;
        
        // start from 1 as we already have selected 1 elt
        // NOTE : DO NOT START FROM 2 AND INITIALIZE VAL2 IT CAN GIVE WRONG ANSWER
        // BCZ IF THERE ARE 3 ELTS ONLY (1,1,1) THEN BOTH VAL1,VAL2 WILL POINT TO 1
        // AND WHEN VERIFYING BOTH WILL BE ADDED TO LIST WHICH IS WRONG
        for(int i = 1; i < n; i++) {
            
            if(arr[i] == val1){ 
                // same elt as val1 so increase count1
                count1++;
                
            } else if(arr[i] == val2) {
                // same elt as val2 so increase count2
                count2++;
                
            } else if(count1 == 0) {
                // then make arr[i] as val1
                val1 = arr[i];
                count1 = 1;
                
            } else if(count2 == 0) {
                // then make arr[i] as val1
                val2 = arr[i];
                count2 = 1;
                
            } else {
                // made a pair of(3 elts) so decrease the count of both values
                count1--;
                count2--;
            }
        }
        
        // to verify the elts
        count1 = 0;
        count2 = 0;
        
        for(int i = 0; i < n; i++) {
            
            if(val1 == arr[i]) count1++;
            
            if(val2 == arr[i]) count2++;
        }
        ArrayList<Integer> ans = new ArrayList<> ();
        
        if(count1 > n/3 ) {
            
            ans.add(val1);
            
        } 
        if(count2 > n/3 ) {
            
            ans.add(val2);
        }
        
        
        return ans; 
    }

    // 6. Majority Element General (for any value k)

    // for any k value you can use hashmap to store the occurence of elts
    // and if they are greater than k then add in list and return
    public static List<Integer> majorityGeneral(int arr[], int k) {

        int n = arr.length;
        HashMap<Integer,Integer> hm = new HashMap<>();

        for(int i = 0; i < n ; i++) {

            int val = arr[i];

            if(hm.containsKey(val) ) {
                int count = hm.get(val);
                hm.put(val,count+1);

            } else {
                hm.put(val,1);
            }
        }
        List<Integer> ans = new ArrayList<>();

        // for traversing over hashMap
        hm.forEach((key,val) -> {
            if(val > k ) {

                ans.add(key);
            }
        });
        return ans;
    }

    // 7. Leetcode 628. Maximum Product of Three Numbers
    // https://leetcode.com/problems/maximum-product-of-three-numbers/


    public int maximumProduct(int[] arr) {
        
        // max product of 3 elts could be largest 3 elts
        // or product of 2 smallest elts and 1 largest elt 
        // ( bcz smallest elts can be -ve and if you product 2 
        // it becomes +ve and then multiply with largest value,so this value could be max)
        
        int min1 = (int) 1e8, min2 = (int) 1e8;
        int max1 = (int) -1e8, max2 = (int) -1e8, max3 = (int) -1e8;
        
        for (int n: arr) {
            
            if (n < min1) {
                
                min2 = min1;
                min1 = n;
                
            } else if (n < min2) {     // n lies between min1 and min2
                min2 = n;
            }
            
            if (n > max1) {            // n is greater than max1, max2 and max3
                
                max3 = max2;
                max2 = max1;
                max1 = n;
                
            } else if (n > max2) {     // n lies betweeen max1 and max2
               
                max3 = max2;
                max2 = n;
                
            } else if (n > max3) {     // n lies betwen max2 and max3
                max3 = n;
            }
        }

        int val1 = max1 * max2 * max3;
        int val2 = min1 * min2 * max1;
        
        return Math.max( val1, val2); // return maximum of both
    }

    // 8. Leetcode 747. Largest Number At Least Twice of Others
    // https://leetcode.com/problems/largest-number-at-least-twice-of-others/

    public int dominantIndex(int[] arr) {
        
        if(arr.length == 1) return 0; // only value follows this 
        
        // for largest number atleast twice of other elts
        // we just need to find second largest number and check if its twice
        // is lesser than largest elt that means it is largest for all
        // bcz all smaller elts will be smaller than 2nd largest and they 
        // will definitely follow this condition if 2nd largest follows
        
        // otherwise every elt does not follows the condition 
        
        int max1 = (int) -1e8, max2 = (int) -1e8;
        int idx = -1;
        
        for(int i = 0 ; i < arr.length; i++) {
            
            if(max1 < arr[i]) {
                
                max2 = max1;
                max1 = arr[i];
                idx = i;
            
            } else if( max2 < arr[i]) {
                
                max2 = arr[i];
            }
        }
        
        if(  max1 >= max2 * 2 ) {
            
            return idx;
            
        } else {
            return -1;
        }
    }

    // 9. Lintcode 912. Best Meeting Point
    // https://www.lintcode.com/problem/best-meeting-point/description

    // You have to find minimum distance to travel to meet

    public int minTotalDistance(int[][] grid) {
        // Write your code here
        
        ArrayList<Integer> rowVals = new ArrayList <> ();
        ArrayList<Integer> colVals = new ArrayList <> ();
        
        // fill both rows and cols separately
        // ie. for rows move in matrix as row wise
        for(int i = 0 ; i < grid.length; i++) {
            for(int j = 0 ; j < grid[0].length; j++) {
                
                if(grid[i][j] == 1) {
                    rowVals.add(i);
                }
            }
        }
        // for cols move in matrix as column wise
        for(int j = 0 ; j < grid[0].length; j++) {
            for(int i = 0 ; i < grid.length; i++) {
                
                if(grid[i][j] == 1) {
                    
                    colVals.add(j);
                }
            }
        }
        
        // for even length list you can take any median bcz answer will be same
        int medianIdxForRows = rowVals.size() / 2;
        int medianIdxForCols = colVals.size() / 2;
        
        int medianRow = rowVals.get(medianIdxForRows);
        int medianCol = colVals.get(medianIdxForCols);
        
        int minDist = 0;
        
        for(int i = 0 ; i < rowVals.size(); i++) {
            
            int r = rowVals.get(i);
            int c = colVals.get(i);

            if(r == medianRow && c == medianCol) continue;
            // distance for same point is 0 so save time
            
            int dist = Math.abs(r - medianRow) + Math.abs(c - medianCol); // distance formula
            minDist += dist;
        }
        
        return minDist;
    }
}
