import java.util.*;
public class Sep14 {

    // 1. Leetcode 829. Consecutive Numbers Sum
    // https://leetcode.com/problems/consecutive-numbers-sum/

    // See how formula is made in copy ( KX = N - ((K-1)*K)/2 )
    // Previous approach is faster
    public int consecutiveNumbersSum(int N) {
        
        int ans = 0;
        
                // range is (underRoot 2N)
        for(int k = 1; k * k <= N*2 ; k++) {
            
             // add only if no is proper divisible by k bcz we cannot 
            // start consecutive +ve integer from decimal point number
            if( ((N - ( (k-1)*k ) / 2) % k ) == 0 ) { 
                
                // % k works as proper dividing by k
                // (ie. no is proper divisible by k or not)
                ans++;
            } 
        }
        return ans;        
    }

    // FASTER APPROACH
    public int consecutiveNumbersSum2(int N) {
        
        int ans = 0;
        int k = 1;
        
        long range = N*2; // range is (underRoot 2N)
        range = (int)Math.sqrt(range);
        
        
        while(k <= range) {
            
             // add only if no is proper divisible by k bcz we cannot 
            // start consecutive +ve integer from decimal point number
            if( ((N - ( (k-1)*k ) / 2) % k ) == 0 ) { 
                
                // % k works as proper dividing by k
                // (ie. no is proper divisible by k or not)
               
                ans++;
            } 
            k++;
        }
        return ans;        
    }

    // 2. Leetcode 881. Boats to Save People
    // https://leetcode.com/problems/boats-to-save-people/

    // first sort the persons and now start filling the boats
    // according to their weights
    public int numRescueBoats(int[] people, int limit) {
        
        Arrays.sort(people);
        
        int n = people.length;
        int l = 0, r = n - 1;
        
        if(people[r] > limit) return -1;
        // we cannot carry person with weight exceeding the limit
        
        int boats = 0;
        while( l <= r ) {
            
            if( people[l] + people[r] <= limit) {
                boats++;
                l++;
                r--;
                // both persons can be transferred
                
            } else {
                
                boats++;
                r--;
                // only single person can be transferred 
                // ie. heavy one
                
            } 
        }
        return boats;
    }


    // 3. Leetcode 763. Partition Labels
    // https://leetcode.com/problems/partition-labels/

    //     Input: S = "ababcbacadefegdehijhklij"
    //     Output: [9,7,8]
    // Partition this string into as many parts as possible so that each letter appears in 
    // at most one part, and return a list of integers representing the size of these parts.

    public List<Integer> partitionLabels(String S) {
        
        int n = S.length();
        int loc[] = new int[26];
        // ending location of characters
        
        for(int i = 0 ; i < n ; i++) {
            
            char ch = S.charAt(i);
            int idx = ch - 'a';
            
            loc[idx] = i; // fill the recent location
        }
        
        List<Integer> ans = new ArrayList <> ();
        int currPartIdx = 0; 
        // current partitioning index which is
        // max index of elts from i idx to current idx
        
        int i = 0, j = 0;
        while(i < n) {
            
            int idx = S.charAt(j) - 'a';
            currPartIdx = Math.max(currPartIdx, loc[idx]);
            
            if(currPartIdx == j) {
                ans.add( j - i + 1);
                i = j + 1; 
            }
            j++;
        }
        return ans;
    }

    // 4. Leetcode 1007. Minimum Domino Rotations For Equal Row
    // https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/

    // (Elavorative one approach )
    // Previously submitted approach was optimized one ( Check that too)
    
    public int minDominoRotations(int[] A, int[] B) {
        
        int A_A0 = A[0]; // A of A[0] ( giving chance to a0 elt to be as max elt in a[])
        int B_B0 = B[0]; // B of B[0] ( giving chance to b0 elt to be as max elt in b[])
        
        int A_B0 = B[0]; // A of B[0] ( giving chance to b0 elt to be as max elt in a[])
        int B_A0 = A[0]; // B of A[0] ( giving chance to a0 elt to be as max elt in b[])
    
        int count1 = 0,count2 = 0,count3 = 0,count4 = 0;
        // corresponding count values for above 4
        
        int n = A.length;
        for(int i = 0 ; i < n ; i++) {
            
            int num1 = A[i];
            int num2 = B[i];
            // A[0] to become max elt to fill in A[] 
            if(count1 != Integer.MAX_VALUE) {
                
                if(num1 != A_A0 && num2 == A_A0) {
                    count1++;
                    // we can swap the elts so increase count for swapping 
                    
                } else if(num1 != A_A0 && num2 != A_A0) {

                    count1 = Integer.MAX_VALUE;
                    // A_A0 number is not present on both arrays
                    // at this location so this number cannot be used to make one array 
                    // fill with same number so make count infinity
                }
            }
            // B[0] to become max elt to fill in B[] 
            if(count2 != Integer.MAX_VALUE) {
                
                
                // when checking b[] we have to check for num2 not for num1 
                // bcz we are checking b[]              
                if(num2 != B_B0 && num1 == B_B0) {
                    count2++;
                    
                } else if(num2 != B_B0 && num1 != B_B0) {

                    count2 = Integer.MAX_VALUE;
                    // A_A0 number (rest is same explanation as above one)
                }
            
            }
            // B[0] to become max elt to fill in A[] 
            if( count3 != Integer.MAX_VALUE) {
                
                if(num1 != A_B0 && num2 == A_B0) {
                    count3++;
                    
                } else if(num1 != A_B0 && num2 != A_B0) {

                    count3 = Integer.MAX_VALUE;
                    // A_B0 number (rest is same explanation as above one)
                }
            }
            
            // A[0] to become max elt to fill in B[] 
            if( count4 != Integer.MAX_VALUE) {
                
                if(num2 != B_A0 && num1 == B_A0) {
                    count4++;
                    
                } else if(num2 != B_A0 && num1 != B_A0) {

                    count4 = Integer.MAX_VALUE;
                    // B_A0 number (rest is same explanation as above one)
                }
            }
         }
        
        int ans = Math.min( Math.min( count1, count2) , Math.min( count3, count4 )); 
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }

    // (Optimized one)

    public int getNoOfSwapsCount(int curr, int num1, int num2, int count) {
        // Here current is used for A_A0/B_B0/A_B0/B_A0
        if(num1 != curr && num2 == curr) {
            count++;
            // we can swap the elts so increase count for swapping 
        } else if(num1 != curr && num2 != curr) {

            count = Integer.MAX_VALUE;
            // A_A0/B_B0/A_B0/B_A0 number is not present on both arrays
            // at this location so this number cannot be used to make one array 
            // fill with same number so return infinity
        }
        
        return count;
    }

    public int minDominoRotations2(int[] A, int[] B) {
        
        int A_A0 = A[0]; // A of A[0] ( giving chance to a0 elt to be as max elt in a[])
        int B_B0 = B[0]; // B of B[0] ( giving chance to b0 elt to be as max elt in b[])
        
        int A_B0 = B[0]; // A of B[0] ( giving chance to b0 elt to be as max elt in a[])
        int B_A0 = A[0]; // B of A[0] ( giving chance to a0 elt to be as max elt in b[])
    
        int count1 = 0,count2 = 0,count3 = 0,count4 = 0;
        // corresponding count values for above 4
        
        int n = A.length;
        for(int i = 0 ; i < n ; i++) {
            
            int num1 = A[i];
            int num2 = B[i];
            // A[0] to become max elt to fill in A[] 
            if(count1 != Integer.MAX_VALUE) {
                
                count1 = getNoOfSwapsCount(A_A0, num1, num2, count1);
            }
            // B[0] to become max elt to fill in B[] 
            if(count2 != Integer.MAX_VALUE) {
                
                count2 = getNoOfSwapsCount(B_B0, num2, num1, count2);
            // when checking b[] we have to send num2 first bcz in fxn checked
            // according to num1 so simply send num2 from here as num1 and num1 as num2
            
            }
            // B[0] to become max elt to fill in A[] 
            if( count3 != Integer.MAX_VALUE) {
                
                count3 = getNoOfSwapsCount(A_B0, num1, num2, count3);
            }
            // A[0] to become max elt to fill in B[] 
            if( count4 != Integer.MAX_VALUE) {
                
                count4 = getNoOfSwapsCount(B_A0, num2, num1, count4);
            // when checking b[] we have to send num2 first bcz in fxn checked
            // according to num1 so simply send num2 from here as num1 and num1 as num2
           
            }
         }
        int ans = Math.min( Math.min( count1, count2) , Math.min( count3, count4 ));
        
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }

    // 5. Min no. of platform GFG(Minimum Platforms )
    // https://practice.geeksforgeeks.org/problems/minimum-platforms/0

    public static int minPlatform(int arr[] ,int dept[], int n) {
	    
	    Arrays.sort(arr);
	    Arrays.sort(dept);
	    
	    int i = 0; // i points to arrival[]
	    int j = 0; // j points to departure[]
	    
	    int totalPlat = 0; // total platform
	    int cPlat = 0; // current platform
	    
	    // we need to check i only bcz more platforms are required only
	    // when trains are arriving not departing
	    while(i < n) {
	        
	        // equaltoo case for when 1 train is coming and another is going
	        //  we require 2 different platforms (depends on constraint of question)
	        if(arr[i] <= dept[j] ) {
	            // i train arrived
	            cPlat++;
	            i++;
	            totalPlat = Math.max(totalPlat, cPlat);
	            
	        } else if(dept[j] < arr[i]) {
	            // j train is departing
	            
	            cPlat--;
	            j++;
	        }
	    }
	    
	    return totalPlat;
	}
    

    // 6. Lincode 508. Wiggle Sort
    // https://www.lintcode.com/problem/wiggle-sort/description

    public void wiggleSort(int[] nums) {
        // write your code here
        
        int n = nums.length;
        
        // even indexed elts should be smaller from adjacent elts
        // odd indexed elts should be greater from adjacent elts
        
        // stop loop at n-1 bcz we are using i+1 in loop and it will take care for last elt
        for(int i = 0 ; i < n - 1 ;i++) {
            
            if( i % 2 == 0) {
                
                if(nums[i+1] <= nums[i]) {
                    
                    int temp = nums[i+1];
                    nums[i+1] = nums[i];
                    nums[i] = temp;
                }
                
            } else if(i % 2 != 0) {
                
                if(nums[i+1] >= nums[i]) {
                    
                    int temp = nums[i+1];
                    nums[i+1] = nums[i];
                    nums[i] = temp;
                }
            }
        }
    }

    // GFG Wave Array (Same Wiggle Sort) (Not Submitted)
    // https://practice.geeksforgeeks.org/problems/wave-array/0
    public static void wiggleSort2(int[] nums) {
        
        int n = nums.length;
        
        // even indexed elts should be greater from adjacent elts
        // odd indexed elts should be smaller from adjacent elts
        
        // stop loop at n-1 bcz we are using i+1 in loop and it will take care for last elt
        for(int i = 0 ; i < n - 1 ;i++) {
            
            if( i % 2 == 0) {
                
                if(nums[i+1] >= nums[i]) {
                    
                    int temp = nums[i+1];
                    nums[i+1] = nums[i];
                    nums[i] = temp;
                }
                
            } else if(i % 2 != 0) {
                
                if(nums[i+1] <= nums[i]) {
                    
                    int temp = nums[i+1];
                    nums[i+1] = nums[i];
                    nums[i] = temp;
                }
            }
        }
    }

    // 7. Leetcode 48. Rotate Image
    // https://leetcode.com/problems/rotate-image/

    // INTERVIEW PREP (SEP - 14)
    public void rotate(int[][] matrix) {
        
        int n = matrix.length;
        
        transpose(matrix,n);
        reverseRowElts(matrix,n);
    }
    
    public void reverseRowElts(int[][] arr,int n) {
        
        for(int i = 0 ; i < n;i++) { 
            for(int l = 0, r = n-1; l < r; l++, r-- ) {
                
                int temp = arr[i][l];
                arr[i][l] = arr[i][r];
                arr[i][r] = temp;
            }
        }
    }
    
    public void transpose(int[][] arr,int n) {
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j< i; j++) {
                
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
    }

    // 8. Leetcode 915. Partition Array into Disjoint Intervals
    // https://leetcode.com/problems/partition-array-into-disjoint-intervals/

    // Given an array A, partition it into two (contiguous) subarrays left and right so that:
        // 1. Every element in left[] is less than or equal to every element in right[].
        // 2. left[] has the smallest possible size.
    // Return length of left[] after such a partitioning. 

    public int partitionDisjoint(int[] arr) {
        
        int leftMax = arr[0]; // max elt in left subarray
        int nextMax = arr[0]; // next maximum elt ahead of left subarray
        
        int ansIdx = 0;
        
        int i = 1;
        
        while( i < arr.length) {
            
            if(arr[i] >= leftMax) {
                // no need to extend leftsubArray as elt on rhs is bigger 
            
                nextMax = Math.max( nextMax, arr[i]);
                // updating nextMax so if any smaller elt found on rhs
                // we can update leftMax directly in 0(1)
                
            } else  {
                // we have to include this smaller elt too
                
                ansIdx = i;
                
                leftMax = nextMax;
                // update leftmax with largest max elt on lhs
                // ie. we have stored largest elt in nextElt for this case
            }
            i++;
        }
        
        return ansIdx+1;
        // as we have to return size of left[] and size is ansIdx+1 
    }
    
}