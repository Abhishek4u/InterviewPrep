// See Notes also
// Screenshots No from (7843 - 8046)

import java.util.*;
public class Sep12 {
    
    // 1. Leetcode 795. Number of Subarrays with Bounded Maximum
    // https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/

    // Q. You have to calculate the number of (contiguous, non-empty) subarrays such that
    //    the value of the maximum array element in that subarray is at least L and at most R.

    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int n = A.length;
        
        int prevValidCount = 0; // previous valid calculated count
        int ans = 0;
        
        int j = 0;
        for(int i = 0; i < n ;i++) {
            int val = A[i];
            
            if(val >= L && val <= R) {
                
                ans += ( i - j + 1); 
                // current val can combined with previous subset as here we 
                // have to take only continous set or it can make its own
                // new subset
                
                prevValidCount = ( i - j + 1); // updating for next elts
                
            } else if(val < L) {
                
                // this value cannot make its own subset but can
                // make subset with previous valid subset so add prevValidSubset value
                // bcz same no of subsets will be generated if current
                // elt is combined with repvious ones
                
                ans += prevValidCount;
                
            } else if(val > R) {
                
                // greater value cannot be added and it will also restrict leftside
                // eltsto join with rightside elts so we have to move j pointer to
                // i+1 place bcz that point could be valid
                
                j = i+1;
                
                prevValidCount = 0; 
                // because no leftside contiguos subset could made due to
                // bigger elt came b/w
            }
        }
        
        return ans;
    }

    // 2. GFG - Segregate 01 ( Binary Array Sorting )
    // https://practice.geeksforgeeks.org/problems/binary-array-sorting/0

    public static void segregate01(int arr[],int n) {
	    
	    int i = 0,j = 0;
	    // i points to 1st 1
	    //  j points to unknown elt
	    
	    while( j != n) {
	        
	        if(arr[j] == 1) {
	            j++;
	            
	        } else if(arr[j] == 0) {
	            
	            int temp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = temp;
	            
	            i++;
	            j++;
	        }
	    }
	    return;
    }
    
    // 3. Leetcode 75. Sort Colors/ GFG - Sort an array of 0s, 1s and 2s 
    // https://leetcode.com/problems/sort-colors/
    // https://practice.geeksforgeeks.org/problems/sort-an-array-of-0s-1s-and-2s/0

    public static void segregate012(int arr[],int n) {
	    
	    int i = 0,j = 0, k = n-1;
	    // i points to 1st 1
	    // j points to unknown elt
	    // k points to unknown from right side( k-1 points to 1st 2 )
	    
	    while( j <= k) {
	        // <= k bcz above k elts are sorted(ie. all elt value above k is 2 )
	        if(arr[j] == 1) {
	            j++;
	            
	        } else if(arr[j] == 0) {
	            
	            int temp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = temp;
	            
	            i++;
	            j++;
	            
	       } else if(arr[j] == 2) {
	           
	            int temp = arr[j];
	            arr[j] = arr[k];
	            arr[k] = temp;
	            
	            k--; // starts pointing to next unknown elt 
	       }
	        
	    }
    }
    
    // 4. Leetcode 905. Sort Array By Parity (sort even and odd elts)
    // https://leetcode.com/problems/sort-array-by-parity/
    // Same as 01 sort
    public int[] sortArrayByParity(int[] A) {
        
        int i = 0,j = 0;
        // i points to 1st odd elt
        // j points to 1st unknown
        
        while( j < A.length) {
            
            if(A[j] %2 != 0) {
                j++;
                
            } else if(A[j] %2 == 0) {
                
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                
                i++;
                j++;
            }
        }
        
        return A;
    } 


    // 5. Leetcode 670. Maximum Swap
    // https://leetcode.com/problems/maximum-swap/

    // Given a non-negative integer, you could swap two digits at most once to get 
    // the maximum valued number. Return the maximum valued number you could get

    // See copy for explanation
        
    public int findNumber(int idxMapping[], int val, int i) {
        
        int idx = idxMapping.length-1;
            
        for(; idx >= 0 && idx > val; idx--) {
            
            if(idxMapping[idx] != -1 && idxMapping[idx] > i) {
                return idx;
            }
        }
        return -1;
    }

    public int maximumSwap(int num) {
        
        char nums[] = (num+"").toCharArray();
        
        int n = nums.length;
        
        int idxMapping[] = new int[10];
        Arrays.fill(idxMapping,-1);
        
        for(int i = 0; i < n;i++) {

            int val = nums[i] - '0';
            
            idxMapping[val] = i;
        }
        
        for(int i = 0 ; i < n;i++) {
            
            int val = nums[i] - '0';
            
            if(val == 9) continue;
            
            int numToBeSwapped = findNumber(idxMapping,val,i);
            
            if(numToBeSwapped != -1) {
                
                int numToBeSwappedIdx = idxMapping[numToBeSwapped];

                nums[i] = (char) (numToBeSwapped + '0');
                nums[numToBeSwappedIdx] = (char) (val + '0'); // current value
                
                break;
            } 
        }
        int ans = 0;
        for(char ch: nums) {
            
            ans = ans * 10 + ch - '0';
        }
        return ans;
    }

    // 6. Sieve of Eratosthenes
    // https://practice.geeksforgeeks.org/problems/sieve-of-eratosthenes/0

    // Given a number N, calculate the prime numbers upto N using Sieve of Eratosthenes.  
    public static void sieveOfEratosthenes(int n) {
	    
	    boolean arr[] = new boolean[n+1];
	    
	    Arrays.fill(arr,true);
	    
	    // start from 2 because 0  and 1 are not prime numbers
	    for(int i = 2; i*i <= n; i++) {
	        
	        if(arr[i] == true) {
	            
	            // start making its multiple false now
	            // ie.start from next multiple 
	            for(int j = i * i ; j <= n ;j += i ) {
	                
	                arr[j] = false;
	            }
	        }
	    }
	    
	    for(int i = 2; i <= n ;i++) {
	        
	        if(arr[i] == true) 
	            System.out.print(i + " ");
	    }
	    System.out.println();
    }
    
    // 7. Segmented Sieve ( PRIME1 - Prime Generator - SPOJ )
    // https://www.spoj.com/problems/PRIME1/cstart=10
    
    // Not correct (Gives wrong answer) (See next approach)
    public static void sieveInRange(int m, int n) {
		
		List<Integer> primes = getPrimeNoUptoRootM(n);
		
		int absSize = n - m + 1; // absolute size
		
		boolean[] arr = new boolean[absSize];
		Arrays.fill(arr,true);
		
		for(int i = 0; i < primes.size(); i++) {
			
			int prime = primes.get(i);
			int idx =  (int)(Math.ceil(((float)m / (float)prime)));
 
            idx *= prime;
			idx = idx - m; // 0 based index for traversing in array
 
            // making idx +ve if idx is -ve
            while(idx < 0) idx+= prime;
 
            for(int j = idx; j < absSize; j += prime ) {
                arr[j] = false;
            }
		}
    
        for(int i = 0 ; i < primes.size();i++) {
            if( m <= primes.get(i) ) 
                System.out.println(primes.get(i));
        }
        
		for(int i = 0 ; i < arr.length;i++) {
			if(i + m == 1) continue;
			if(arr[i] == true) {
				System.out.println(i + m);
			}
		}
		
	}
	
	public static List<Integer> getPrimeNoUptoRootM( int m) {
		// 2 to root(m) prime numbers
		int size = (int)Math.sqrt(m);
		
		boolean arr[] = new boolean[size + 1];
		Arrays.fill(arr,true);
		
		
		for(int i = 2; i*i <= size; i++) {
	        
	        if(arr[i] == true) {    
	            for(int j = i * i ; j <= size;j += i ) {
	                
	                arr[j] = false;
	            }
	        }
	    }
		
		List<Integer> ans = new ArrayList<> ();
		
		for(int i = 2; i <= size; i++) {
			if(arr[i] == true) {
				ans.add(i);
			}
		}
		return ans;
	}
    
    // Submitted Approach (Not Mine)

    public static void primeGenerator () {
        Scanner scn = new Scanner(System.in);
        int[] primes = new int[4000];
        int numprimes = 0;

        primes[numprimes++] = 2;
        for (int i = 3; i <= 32000; i+=2) {
            boolean isprime = true;
            double cap = Math.sqrt(i) + 1.0;

                for (int j = 0; j < numprimes; j++) {
                if (j >= cap) break;
                if (i % primes[j] == 0) {
                    isprime = false;
                    break;
                }
            }
            if (isprime) primes[numprimes++] = i;
        }


        int T,N,M;

        T = scn.nextInt();

        for (int t = 0; t < T; t++) {
            if (t > 0) System.out.println("");

            M = scn.nextInt();
            N = scn.nextInt();

            if (M < 2) M = 2;

            boolean[] isprime = new boolean[100001];
            for (int j = 0; j < 100001; j++) {
                isprime[j] = true;
            }

            for (int i = 0; i < numprimes; i++) {
                int p = primes[i];
                int start;

                if (p >= M) start = p*2;
                else start = M + ((p - M % p)%p);

                    for (int j = start; j <= N; j += p) {
                    isprime[j - M] = false;
                }
            }

            for (int i = M; i <= N; i++) {
                if (isprime[i-M]) System.out.println(i);
            }
        }
    }

    // 8. Two Sum ( whether a pair in array is equal to given value or not )
    // https://practice.geeksforgeeks.org/problems/key-pair/0
    
    // Given an array A[] and a number x, check for pair in A[] with sum as x
    // sort array and use 2 pointer on both ends
    boolean hasArrayTwoCandidates(int arr[], int n, int x) {
        // code here
        Arrays.sort(arr);
        int i = 0, j = n - 1;
        
        while( i < j) {
            
            if(arr[i] + arr[j] == x) {
                return true;
                
            } else if(arr[i] + arr[j] > x) {
                // to decrease value move right pointer to left side
                j--;
                
            } else if(arr[i] + arr[j] < x) {
                // to increase value move left pointer to right side
                i++;
            }
        }
        
        return false;
        // no element found with this sum
    }

    // 9. Find a pair with the given difference 
    // https://practice.geeksforgeeks.org/problems/find-pair-given-difference/0

    // Given an unsorted array Arr[] and a number N. You need to write a program to 
    // find if there exists a pair of elements in the array whose difference is N.

    // Start both pointers from 0 and 1 index as it is easy to implement
    public static int twoDifference(int arr[], int n, int x) {
	    
	    Arrays.sort(arr);
	    
	    int i = 0, j = 1;
	    
	    while(i < n && j < n) {
	        
	        if(arr[j] - arr[i] == x) {
	            return 1;
	            
	        } else if(arr[j] - arr[i] > x) {
	            // move left pointer to decrease value
	            i++;
	            
	        } else if(arr[j] - arr[i] < x) {
	            // move right pointer to increase value
	            j++;
	        }
	    }
	    
	    return -1; // not found
    }
    
}
