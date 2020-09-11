public class Sep10 {

    // 1. LONG PRESSED NAME (Leetcode 925)
    // https://leetcode.com/problems/long-pressed-name/

    public boolean isLongPressedName(String name, String typed) {

        if (typed.length() < name.length())
            return false; // full name is not typed

        return isLongPressedName_(name, typed);
    }

    public boolean isLongPressedName_(String name, String typed) {

        int i = 0, j = 0;

        while (i != name.length() && j != typed.length()) {

            char a = name.charAt(i);
            char b = typed.charAt(j);

            if (a == b) {
                i++;
                j++;

            } else if (j != 0 && b == typed.charAt(j - 1)) {
                // same character typed
                j++;

            } else {
                // character do not match with name string's character
                // or 1st character is different
                // or previous character of typed string does not match

                return false;
            }
        }

        while (j != typed.length()) {
            // check for left out characters in typed string and if they
            // do not match with previous character means extra character is added

            char b = typed.charAt(j);

            if (b != typed.charAt(j - 1)) {

                return false;

            } else
                j++;
        }
        if (i != name.length())
            return false;
        // some characters are not typed in so return false

        return true;
    }

    // 2. Range Addition
    // https://www.lintcode.com/problem/range-addition/description

    public int[] getModifiedArray(int length, int[][] updates) {
        // Write your code here

        int[] pre = new int[length];
        // prefix sum array

        for (int i = 0; i < updates.length; i++) {

            int lb = updates[i][0]; // left bound
            int rb = updates[i][1] + 1; // right bound (here we will use prefix sum array so we have
                                        // to take rightbound inclusive so place this value after rb + 1)

            pre[lb] += updates[i][2];

            // check rb should not point to pre.length and if so then we donot have to make
            // (-ve)right bound for preArr
            if (rb < pre.length) {

                pre[rb] -= updates[i][2];
                // here using minus because we donot want to infect elts above the rightbound
                // index in prefix array
            }
        }

        for (int i = 0; i < pre.length; i++) {

            if (i == 0)
                continue;

            pre[i] = pre[i - 1] + pre[i]; // prefix sum (own idx val + prev idx val)
        }

        return pre;
    }

    // 3. Container with most water (Leetcode 11)
    // https://leetcode.com/problems/container-with-most-water/

    public int maxArea(int[] height) {

        return maxArea_(height);
    }

    public int maxArea_(int[] height) {

        int i = 0, j = height.length - 1;

        int ans = -(int) 1e8;

        while (i != j) {

            int val = (j - i) * Math.min(height[i], height[j]);
            ans = Math.max(ans, val);

            if (height[i] <= height[j]) {

                i++;
            } else if (height[j] < height[i]) {

                j--;
            }
        }
        return ans;
    }

    // 4. Rotate Array (Leetcode 189)
    // https://leetcode.com/problems/rotate-array/

    public void rotate(int[] nums, int k) {

        int n = nums.length;

        k %= nums.length; // for making k in range

        // reverse first n-k elts
        reverseElts(nums, 0, n - k - 1);
        // reverse last k elts
        reverseElts(nums, n - k, n - 1);
        // reverse whole array
        reverseElts(nums, 0, n - 1);
    }

    public void reverseElts(int[] arr, int i, int j) {

        while (i <= j) {

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }
    }

    // 5. Squares of a Sorted Array (Leetcode 977)
    // https://leetcode.com/problems/squares-of-a-sorted-array/

    public int[] sortedSquares(int[] A) {

        return sortedSquares_(A);
    }

    public int[] sortedSquares_(int[] arr) {

        int n = arr.length;
        int i = 0, j = n - 1;

        int ans[] = new int[n];
        int k = n - 1;

        while (i <= j) {
            int val1 = arr[i] * arr[i];
            int val2 = arr[j] * arr[j];

            if (val1 >= val2) {

                ans[k--] = val1;
                i++;

            } else if (val2 > val1) {

                ans[k--] = val2;
                j--;
            }
        }
        return ans;
    }

    // 6. Next Greater Element III (Leetcode 556)
    // https://leetcode.com/problems/next-greater-element-iii/

    public int nextGreaterElement(int n) {

        if ((n + "").length() == 10)
            return -1;

        return nextGreaterElement_(n);
    }

    public void reverseElts(char[] arr, int i, int j) {

        while (i <= j) {

            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }
    }

    public int nextGreaterElement_(int n) {

        String str = n + "";
        char[] arr = str.toCharArray();

        int i = arr.length - 1;

        // for finding the dip (ie.next smallest number on rhs) (ie. (i-1)th elt)
        while ((i > 0) && arr[i - 1] - '0' >= arr[i] - '0') {

            i--;
        }

        if (i == 0) {
            
            return -1; // no dip found so no greater elt can be formed
        } 
        else {
            // now put this elt in its location(ie. rhs elts >= elt < lhs elts)
            int j = arr.length - 1;

            while (arr[j] - '0' <= arr[i - 1] - '0') {
                j--;
            }

            // now swap both the elts
            char temp = arr[i - 1];
            arr[i - 1] = arr[j];
            arr[j] = temp;

            // now reverse the elts from i to len to get next greater elt
            // (or you can again do above process if not want to reverse )
            reverseElts(arr, i, arr.length - 1);
        }

        int num = 0;
        for (char ch : arr) {
            num = num * 10 + (ch - '0');
        }

        return num;
    }

}