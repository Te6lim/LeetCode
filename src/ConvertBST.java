public class ConvertBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode convertBST(TreeNode root) {
        return doStuff(root, 0);
    }

    int largestLeftValue = 0;

    private TreeNode doStuff(TreeNode root, int parent) {
        if (root == null) {
            return null;
        }
        doStuff(root.right, parent);
        if (root.right != null) {
            if (largestLeftValue == 0) {
                root.val += root.right.val;
            } else {
                root.val += largestLeftValue;
                largestLeftValue = 0;
            }
        } else {
            root.val += parent;
        }
        doStuff(root.left, root.val);
        if (root.left == null) {
            largestLeftValue = root.val;
        }
        return root;
    }
}
