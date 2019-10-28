import java.util.*;

public class asimily {

    public static void main(String[] args) {

        //PATH QUESTION FOR HITHESH
        vertexNode origin = new vertexNode (0, 0, 0);
        vertexNode dest = new vertexNode(3, 3, 3);
        System.out.println(findAllPaths(dest, origin));

        //PERMUTATION QUESTION FOR SHANKAR
        ArrayList<String> allPermutations = permutate("abc");
        for (String s: allPermutations) System.out.println(s);

    }

    //Method to Find all paths from origin (0, 0, 0) to Destination 3d Node
    //Works by generating all valid children node from origin, and following
    //The valid node paths until a child node evenutally is equal to the goal node.
    private static int findAllPaths(vertexNode dest, vertexNode curr) {
        //Base case
        if (curr.x == dest.x && curr.y == dest.y && curr.z == dest.z) return 1;

        int sum = 0;
        //Generate legal children and call method on children
        vertexNode[] children = new vertexNode[3];
        children[0] = new vertexNode(curr.x + 1, curr.y, curr.z);
        children[1] = new vertexNode(curr.x, curr.y + 1, curr.z);
        children[2] = new vertexNode(curr.x, curr.y, curr.z + 1);
        for (int i = 0; i < children.length; i++) {
            if (children[i].x <= dest.x && children[i].y <= dest.y && children[i].z <= dest.z) sum += findAllPaths(dest, children[i]);
        }
        return sum;
    }

    //FIND ALL PERMUTATIONS OF A STRING 
    private static ArrayList<String> permutate(String s) {
        ArrayList<String> perms = new ArrayList<String>();
        if (s.length() == 1) perms.add(s);
        else {
            String start = s.substring(0, s.length() - 1);
            String last = s.substring(s.length() - 1);
            //Where recursive calls are made. Combine list of 
            //Permutations on string without last char to form new
            //List of permutations.
            perms = helper(permutate(start), last);
        }

        return perms;
    }

    //Helper method for permutate method. Combines list of permutations
    //with the lastChar parameter inserted at each position to make a new
    //List of permutations
    private static ArrayList<String> helper(ArrayList<String> list, String lastChar) {
        ArrayList<String> perms = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //At each string of given list, insert last char to each position and add to list
            for (int j = 0; j <=list.get(i).length(); ++j) {
                String newP = new StringBuffer(list.get(i)).insert(j, lastChar).toString();
                perms.add(newP);
            }
        }
        return perms;
    }

}





