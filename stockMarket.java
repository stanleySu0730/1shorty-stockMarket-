import java.util.ArrayList;
import java.util.List;

public class stockMarket {
    public static List<Integer> stockMarket(int[] arr) {
        return stockMarketHelper(arr, 0, arr.length - 1);
    }

    public static List<Integer> stockMarketHelper(int[] arr, int left, int right) {
        if (left == right) {
            List<Integer> streak = new ArrayList<>();
            streak.add(arr[left]);
            return streak;
        } else {
            int mid = (left + right) / 2;
            List<Integer> leftStreak = stockMarketHelper(arr, left, mid);
            List<Integer> rightStreak = stockMarketHelper(arr, mid + 1, right);
            List<Integer> midStreak = midStreak(arr, left, mid, right);
            List<Integer> longestStreak;
            if (leftStreak[0] >= rightStreak[0] && leftStreak[0] >= midStreak[0]) {
                longestStreak = leftStreak;
            } else if (rightStreak[0] >= leftStreak[0] && rightStreak[0] >= midStreak[0]) {
                longestStreak = rightStreak;
            } else {
                longestStreak = midStreak;
            }
            return longestStreak;
        }
    }

    public static List<Integer> midStreak(int[] arr, int left, int mid, int right) {
        int leftMax = arr[mid];
        int leftCount = 0;
        int leftIndex = mid;
        for (int i = mid; i >= left; i--) {
            if (arr[i] <= leftMax) {
                leftMax = arr[i];
                leftCount++;
                leftIndex = i;
            } else {
                break;
            }
        }

        int rightMin = arr[mid + 1];
        int rightCount = 0;
        int rightIndex = mid + 1;
        for (int i = mid + 1; i <= right; i++) {
            if (arr[i] >= rightMin) {
                rightMin = arr[i];
                rightCount++;
                rightIndex = i;
            } else {
                break;
            }
        }

        int totalCount = leftCount + rightCount;
        List<Integer> elements = new ArrayList<Integer>();
        for (int i = leftIndex; i <= rightIndex; i++) {
            elements.add(arr[i]);
        }

        List<Integer> res = new ArrayList<Integer>();
        res.add(totalCount);
        res.add(leftIndex+1);
        for(int i = 0; i < elements.size(); i++){
            res.add(elements.get(i));
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {};
        List<Integer> longestStreak = stockMarket(arr);
        System.out.println(longestStreak);
    }
}
