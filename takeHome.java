/* INSTRUCTIONS TO COMPILE
1. To compile this java file, type the following into a terminal (while in 
same directory as the file)
    
javac takeHome.java

2. To run the program you can then type the following again into the terminal

java takeHome 

MORE NOTES
-I have only included the given test case to execute my algorithm, additional
matricies can be specified in the main method body below. The program could
be made more portable for use in other programs but I wanted to keep everything
(algorithm, test execution/printing, notes on compilation/Complexity) in a single
file. 

-From my interpretation of the question prompt a region can only be connected
by indicies left/right and above/below. If diagonal indicies were also allowed
I would just need to add some additional method calls to indicies in the 
calculateArea method around line 78-81.

*/




public class takeHome {

    public static void main(String[] args) {
        

        //Given test case
        int[][] given = {
            {1, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 1, 1},
            {1, 0, 0, 0}
        };

        System.out.println(largestRegion(given));

    }

    /**
     * @param matrix a 2-Dimensional array if int values (0 or 1) representing
     *               regions
     * @return The largest region in the array as an int
     */
    public static int largestRegion(int[][] matrix) {

        if (matrix == null || matrix.length == 0) return 0;

        int maxRegion = 0;

        /* Iterate through matrix, if part of region found, Calculate the area
        of the entire region and if it's the largest found yet, set it's area to maxRegion */
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 1) maxRegion = Math.max(calculateArea(matrix, row, col), maxRegion);
            }
        }
        
        return maxRegion;
    }
     
    /* Helper method to above largestRegion, calculate area of given index's region */
    private static int calculateArea(int[][] matrix, int row, int col) {
        
        /* If the given matrix index is out of the matrix bounds or the index's value
        is 0 meaning it is not a region, simply return 0 */
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length || matrix[row][col] == 0) return 0;

        //Set index's value to 0 so that it isn't counted twice 
        matrix[row][col] = 0;

        //Look at adjacent indicies to calculate total area
        return 1 + calculateArea(matrix, row - 1, col)
            + calculateArea(matrix, row + 1, col) 
            + calculateArea(matrix, row, col - 1) 
            + calculateArea(matrix, row, col + 1);
    }

}

/* COMPLEXITY ANALYSIS

Time Complexity
    Given is a matrix of size m x n. In total there will v elements where
    v = m x n. Since I am using a nested for loop to iterate through the entire matrix,
    everyone node will be visited once. At each element, if
    matrix[row][col] == 1, the calculateArea function is called. Recursively this method
    has the ability to visit every other element in the matrix- again taking v time. Therefore
    in total the algorithm is bound by O(v^2) time. Since nodes are set to 0 after being visited, 
    in many cases the calculateArea function will not take v time. This means on average the algorithm
    will take much less time than its O(v^2). For example, if the whole matrix is a region (all 1's),
    the first element will make a call to calculateArea and all elements will be visited. The rest of the iterations
    over the matrix will make no calls to calculateArea and total time would only take v + v 
    (v from the nested for loop and v from the first call to calculateArea).


Space Complexity 
    The algorithm only declares a single variable (maxRegion) and doesn't declare any new 
    space dynamically. However, the helper method makes recursive calls which will take space
    on the stack. At an element, 4 method calls could be made, and more recursivly upon the 
    method called on the adjecent elements. Since nodes are marked to be 0 after they're visited,
    no calls can be made on nodes after their first visit. This mean that the calls are bound
    by an O(v) space complexity.
*/

