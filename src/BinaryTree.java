import java.util.HashMap;
import java.util.Stack;

class BinaryTree {
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Boolean> processedNodes = new HashMap<>();
        Stack<TreeNode> nodes = new Stack<>();

        int po = 0;
        int io = 0;

        TreeNode root = null;
        if (preorder.length != 0) {
            root  = new TreeNode(preorder[po++]);
            nodes.push(root);
            processedNodes.put(preorder[0], true);
        }

        TreeNode node = root;

        while (!nodes.isEmpty()) {
            while (po < preorder.length && node.val != inorder[io]) {
                processedNodes.putIfAbsent(preorder[po], true);
                node.left = new TreeNode(preorder[po++]);
                nodes.push(node.left);
                node = node.left;
            }

            if (po < preorder.length) {
                int tempIo = io + 1;
                while (tempIo < inorder.length && processedNodes.getOrDefault(inorder[tempIo++], false)) {
                    if (nodes.size() > 1) nodes.pop();
                }
                TreeNode parent = nodes.pop();
                node = new TreeNode(preorder[po++]);
                parent.right = node;
                nodes.push(node);

                while (io < inorder.length && processedNodes.getOrDefault(inorder[io], false)) {
                    ++io;
                }

                processedNodes.putIfAbsent(preorder[po - 1], true);

            } else return root;
        }

        return root;
    }

    public static class TreeNode {
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

    public static void main(String[] args) {
        int[] preorder = new int[] /*{1, 2, 3}*/{3, 9, 4, 6, 2, 1, 20, 15, 7};
        int[] inorder = new int[] /*{1, 2, 3}*/{4, 9, 2, 6, 1, 3, 15, 20, 7};

        TreeNode root = BinaryTree.buildTree(preorder, inorder);
        System.out.println(root.right.val);
    }
}
