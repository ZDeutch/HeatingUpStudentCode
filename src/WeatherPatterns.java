import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author Zander Deutch
 */

public class WeatherPatterns {


    /**
     * Longest Warming Trend
     *
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures) {
        int n = temperatures.length;

        //Base case with empty array
        if (n == 0) {
            return 0;
        }

        // Create Adjacency list to store which days can come next in your warming trend
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        // Use array to know how many days point to this temperature
        int[] inDegrees = new int[n];

        // Initialize your adjacency list
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Go through every direct pair
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // If first day is cooler than the other
                if (temperatures[i] < temperatures[j]) {
                    // add the hotter day as a possibility for the temperature in the adjacency list
                    graph.get(i).add(j);
                    // The hotter day is now warmer than the current one
                    inDegrees[j]++;
                }
            }
        }

        // Call for the shortest path after traversing through all temperatures
        return LongestGraph(graph, inDegrees, n);
    }

    // Method to find the lpngest path of the temperature graph
    public static int LongestGraph(ArrayList<ArrayList<Integer>> graph, int[] inDegrees, int n) {
        // Create an array to track the longest warming trend ending at each day
        int[] dp = new int[n];

        // Starts at 1 because at the start each trend is just that day
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Use a queue and add all the elements that have no incoming arrows
        // In other words, identify the coolest days first because they are the best starting points for warming trends
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        // Remove the first element in queue
        while (!queue.isEmpty()) {
            int node = queue.remove();

            // Go through each next potential step for your given temp
            for (int i = 0; i < graph.get(node).size(); i++) {
                int neighbor = graph.get(node).get(i);
                // Keep whichever path is longer, the current path (first option) or the new path with adding in neighbor
                dp[neighbor] = Math.max(dp[neighbor], dp[node] + 1);
                // Mark that the edge pointing to neighbor has been processed
                inDegrees[neighbor]--;
                // If all arrows to the neighbor are processed, then add it to the queue
                if (inDegrees[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Look through all of the days
        int maxLength = 0;
        for (int i = 0; i < dp.length; i++) {
            // Find which has the longest warming trend
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }
}
