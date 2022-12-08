import java.util.*;

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

    public static TreeNode trimBST(TreeNode root, int low, int high) {
        return find(root, null, low, high, false);
    }

    private static TreeNode find(TreeNode root, TreeNode parent, int lo, int hi, Boolean isLeft) {
        if (root == null) return null;
        if (root.val < lo) {
            if (isLeft) {
                if (parent != null) parent.left = root.right;
                else return find(root.right, null, lo, hi, false);
            } else {
                if (parent != null) parent.right = root.right;
                else return find(root.right, null, lo, hi, false);
            }
            find(parent.left, parent, lo, hi, true);
            find(parent.right, parent, lo, hi, false);
            return parent;
        } else {
            if (root.val > hi) {
                if (isLeft) {
                    if (parent != null) parent.left = root.left;
                    else return find(root.left, null, lo, hi, false);
                } else {
                    if (parent != null) parent.right = root.left;
                    else return find(root.left, null, lo, hi, false);
                }
                find(parent.left, parent, lo, hi, true);
                find(parent.right, parent, lo, hi, false);
                return parent;
            } else {
                find(root.left, root, lo, hi, true);
                find(root.right, root, lo, hi, false);
                return root;
            }
        }
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

    private static TreeNode buildRandomBinaryTree() {
        Random rand = new Random();
        ArrayList<Integer> values = new ArrayList<>();
        int sizeOfTree = rand.nextInt(0, 10);
        while (sizeOfTree == 0) sizeOfTree = rand.nextInt(0, 10);
        values.add(rand.nextInt(0, sizeOfTree * 2));
        for (int x = 1; x < sizeOfTree; ++x) {
            int v = rand.nextInt(0, sizeOfTree * 2);
            while (values.contains(v)) v = rand.nextInt(0, sizeOfTree);
            values.add(v);
        }
        Collections.sort(values);

        for (int i : values) System.out.print(" " + i);
        System.out.println();

        return build(values, 0, values.size() - 1);
    }

    private static TreeNode build(ArrayList<Integer> values, int lo, int hi) {
        if (lo >= hi) return null;
        int mid = (lo + hi) / 2;
        TreeNode node = new TreeNode(values.get(mid), null, null);
        node.left = build(values, lo, mid);
        node.right = build(values, mid + 1, hi);
        return node;
    }

    public static void main(String[] args) {
        /*int[] preorder = new int[] *//*{1, 2, 3}*//*{3, 9, 4, 6, 2, 1, 20, 15, 7};
        int[] inorder = new int[] *//*{1, 2, 3}*//*{4, 9, 2, 6, 1, 3, 15, 20, 7};

        TreeNode root = BinaryTree.buildTree(preorder, inorder);
        System.out.println(root.right.val);*/

        TreeNode root = buildRandomBinaryTree();
        System.out.println(root.val);
        trimBST(root, 4, 15);
    }
}
