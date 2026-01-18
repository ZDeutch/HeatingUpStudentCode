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
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures) {
        // TODO: Write your code here!

        int n = temperatures.length;

        //Base case with empty array
        if(n == 0) {
            return 0;
        }

        //dp[i] = length of longest warming trend ending at index i
        // dp = array of size n initialized ot 1

        // Each day by itselff is a warming trend of length 1
        // for i from 0 to n - 1
        // dp[i] = 1

        // for each day, look back at previous days
        //for i from 1 to n - 1
            // for j from 0 to i - 1
        // if previous day was cooler, you can extend the trend
            //if temp[j] < temp[i]:
                //dp[i] = maxTemp(dp[i], dp[i] + 1_

        // The answer is the maximumn value in dp
        // maxLength = 0
        // for i from 0 to n -1;
            //maxLength = maxTemp(maxLength, dp[i])
        // return maxLength

        return 0;
    }
}
