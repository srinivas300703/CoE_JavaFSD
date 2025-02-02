import java.util.*;

public class BinaryTreeSerializer {

    // TreeNode class to represent the binary tree nodes
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // Serialize the binary tree into a string
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    // Helper method to perform pre-order traversal and build the serialized string
    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");  // Use "null" to represent null nodes
            return;
        }
        sb.append(node.val).append(",");  // Append the node value
        serializeHelper(node.left, sb);   // Recursively serialize the left subtree
        serializeHelper(node.right, sb);  // Recursively serialize the right subtree
    }

    // Deserialize the string to rebuild the binary tree
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        Queue<String> nodeQueue = new LinkedList<>(Arrays.asList(nodes)); // This line fixes the issue
        return deserializeHelper(nodeQueue);
    }

    // Helper method to deserialize the string and reconstruct the tree
    private TreeNode deserializeHelper(Queue<String> nodeQueue) {
        String val = nodeQueue.poll();
        if (val.equals("null")) {
            return null;  // If it's "null", return a null node
        }

        TreeNode node = new TreeNode(Integer.parseInt(val));  // Create the node with the value
        node.left = deserializeHelper(nodeQueue);  // Recursively build the left subtree
        node.right = deserializeHelper(nodeQueue);  // Recursively build the right subtree
        return node;
    }

    // Method to print the tree (for testing purposes)
    public void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }

    public static void main(String[] args) {
        BinaryTreeSerializer serializer = new BinaryTreeSerializer();

        // Example Tree:
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Serialize the tree
        String serializedTree = serializer.serialize(root);
        System.out.println("Serialized Tree: " + serializedTree);

        // Deserialize the string back to the tree
        TreeNode deserializedTree = serializer.deserialize(serializedTree);
        System.out.println("Deserialized Tree (pre-order traversal):");
        serializer.printTree(deserializedTree);  // Output tree structure
    }
}
