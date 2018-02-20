public class OST {

    private static Node root;

    public static Node getRoot() {
        return root;
    }

    public static void setRoot(Node root) {
        OST.root = root;
    }

    public OST() {
        this.root = null;
    }
//    public Node getParent(int data) {
//        Node tmp = root;
//        while (tmp != null) {
//            if (tmp.data == data) {
//                return tmp.P;
//            } else {
//                if (tmp.data > data) {
//                    tmp = tmp.left;
//                } else {
//                    tmp = tmp.right;
//                }
//            }
//        }
//        return null;
//    }

    private int size(int data) {
        Node tmp = root;
        while (tmp != null) {
            if (tmp.getData() == data) {
                return size(tmp);
            } else if (tmp.getData() > data) {
                tmp = tmp.getLeft();
            } else {
                tmp = tmp.getRight();
            }
        }
        return -1;
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        else {
            return (size(node.getLeft()) + size(node.getRight())) + 1;
        }
    }

    public boolean find(int data) {
        Node current = root;
        while (current != null) {
            if (current.getData() == data) {
                return true;
            } else if (current.getData() > data) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return false;
    }

    private void updateSize(Node current) {
        while (current.getParent() != null) {
            current = current.getParent();
            current.setSize(current.getSize() - 1);
        }
    }

    public boolean delete(int data) {
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;
        while (current.getData() != data) {
            parent = current;
            if (current.getData() > data) {
                isLeftChild = true;
                current = current.getLeft();
            } else {
                isLeftChild = false;
                current = current.getRight();
            }
            if (current == null) {
                return false;
            }
        }
        updateSize(current);
        if (current.getLeft() == null && current.getRight() == null) {
            if (current == root) {
                root = null;

            }
            if (isLeftChild == true) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if (current.getRight() == null) {
            if (current == root) {
                root = current.getLeft();
            } else if (isLeftChild) {
                parent.setLeft(current.getLeft());
            } else {
                parent.setRight(current.getLeft());
            }
        } else if (current.getLeft() == null) {
            if (current == root) {
                root = current.getRight();
            } else if (isLeftChild) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());

            }
        } else if (current.getLeft() != null && current.getRight() != null) {

            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.setLeft(successor);
            } else {
                parent.setRight(successor);
            }
            successor.setLeft(current.getLeft());
        }
        current.setParent(null);
        return true;
    }

    public Node getSuccessor(Node deleleNode) {
        Node successsor = null;
        Node successsorParent = null;
        Node current = deleleNode.getRight();
        while (current != null) {
            successsorParent = successsor;
            successsor = current;
            current = current.getLeft();
        }
        if (successsor != deleleNode.getRight()) {
            successsorParent.setLeft(successsor.getRight());
            successsor.setRight(deleleNode.getRight());
        }
        return successsor;
    }

    public void insert(int data) {
        Node newNode = new Node(data);
        newNode.setParent(null);
        newNode.setSize(0);
        if (root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        Node parent = root;
        while (current != null) {
            parent = current;
            parent.setSize(parent.getSize() + 1);
            if (data < current.getData()) {
                current = current.getLeft();

//                if (current == null) {
//                    parent.left = newNode;
//                    return;
//                }
            } else {
                current = current.getRight();
//                if (current == null) {
//                    parent.right = newNode;
//                    return;
//                }
            }

        }
        if (data < parent.getData()) {
            parent.setLeft(newNode);
            newNode.setParent(parent);
        } else {

            parent.setRight(newNode);
            newNode.setParent(parent);
        }
    }

    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.getLeft());
            System.out.print(" " + root.getData() + " size is " + size(root.getData()));
            System.out.println("");
            inOrder(root.getRight());
        }
    }
}