package students.task.solution.tree;

public class CustomTreeExample {

    private Node root;

    public static class Node {
        int value;

        public Node(int value) {
            this.value = value;
        }

        Node left;
        Node right;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }



    public void insert(int inValue) {
        if(root == null){
            this.root = new Node(inValue);
            return;
        }
        Node current = root;

        while (true) {
            if (inValue == current.value) {
                return;
            } else if (inValue > root.value) {
                if (current.right == null) {
                    current.right = new Node(inValue);
                    return;
                } else {
                    current = current.right;
                }
            } else {
                if (current.left == null) {
                    current.left = new Node(inValue);
                    return;
                } else {
                    current = current.left;
                }
            }
        }
    }

    //todo: implement
    public boolean checkIfExist(int value) {
        return false;
    }






}
