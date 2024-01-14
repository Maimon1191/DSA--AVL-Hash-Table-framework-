import java.util.ArrayList;
import java.util.List;


public class AVLTree {

    private Node root; //  holds a pointer to the root of the AVL tree
    private int size; //holds the number of spells stored in the AVL tree
    private final String category; //a category that the AVL tree represents

    // private Node class for the AVL Tree nodes
    private class Node {
        private final Spell spell;
        private Node left; //pointer to left son
        private Node right; //pointer to the right son
        private int height; //height of current node

        private Node(Spell spell) { // initalized tree
            this.spell = spell;
            left = null;
            right = null;
            height = 0;
        }
        public boolean isLeaf(){
            return left == null && right == null;
        }

    }

    // Constructor, getters, setters
    public AVLTree(Spell spell) {
        // Arg > class
        root = new Node(spell);
        category = spell.getCategory();
        size = 1; // back to this section check if it is right
    }

    public int getTreeHeight(){
        return root.height;
    }


    public int getSize(){
        return size;
    }

    public String getCategory() {
        return category;
    }
    public Spell search(String spellName, int powerLevel) {
        Node current = root;
        while (current != null) {
            if (current.spell.getName().equals(spellName) && current.spell.getPowerLevel() == powerLevel){
                return current.spell;
            }
            // bst left & right
            current = current.spell.getPowerLevel() < powerLevel ? current.right : current.left;
        }
        return null; // nothing found
    }

    public void insert(Spell spell) {
        root = recursiveInsert(root, spell);
        this.size ++;
    }

    private Node recursiveInsert(Node node , Spell spell){
        if(spell.getPowerLevel() < node.spell.getPowerLevel()){
            if(node.left == null){
                node.left = new Node(spell);
                calcuteHeight(node);
                return node;
            }else{
                node.left = recursiveInsert(node.left, spell);
            }
        }else{
            if (node.right == null){
                node.right = new Node(spell);
                calcuteHeight(node);
                return node;
            }else{
                node.right = recursiveInsert(node.right, spell);

            }
        }
        node = Getbalance(node);
        calcuteHeight(node);
        return node;
    }


    private Node LeftLeftRotatation(Node x2, Node x1) {
        x2.left = x1.right;
        x1.right = x2;
        calcuteHeight(x1);
        calcuteHeight(x2);
        return x1;
    }

    private Node LeftRightRotation(Node x2, Node x1, Node x3){
        RightRightRotation(x1,x3);
        LeftLeftRotatation(x2,x3);
        return x3;
    }


    private Node RightRightRotation(Node x2, Node x1){
        x2.right = x1.left;
        x1.left = x2;
        calcuteHeight(x1);
        calcuteHeight(x2);
        return x1;
    }


    private Node RightLeftRotation(Node k2, Node k1, Node k3){
        LeftLeftRotatation(k1,k3);
        RightRightRotation(k2,k3);
        return k3;
    }

    private void calcuteHeight(Node node){
        int leftHeight = 0;
        int rightHeight = 0;
        if (node.isLeaf()){
            node.height = 0;
            return;
        }
        if (node.left != null)
            leftHeight = node.left.height;
        if (node.right != null)
            rightHeight = node.right.height;
        node.height =  Math.max(leftHeight,rightHeight) +1;
    }

    private int balanceCheck(Node node){
        int leftHeight = 0;
        int rightHeight = 0;
        if (node.left != null)
            leftHeight = node.left.height + 1;
        if (node.right != null)
            rightHeight = node.right.height + 1;
        return rightHeight - leftHeight;
    }

    private Node Getbalance(Node node){
        Node returnNode = node;
        int balanceindecator = balanceCheck(node);

        // Case1 = left subtree too tall
        if(balanceindecator < -1){
            if(balanceCheck(node.left) <= 0)
                returnNode = LeftLeftRotatation(node,node.left);
            else
                returnNode = LeftRightRotation(node,node.left,node.left.right);
        }
        // Case2 = left subtree too tall
        if(balanceindecator > 1){
            if(balanceCheck(node.right) >= 0)
                returnNode = RightRightRotation(node,node.right);
            else
                returnNode = RightLeftRotation(node,node.right,node.right.left);
        }
        return returnNode;
    }




    public List<Spell> getTopK(int k) {
        if(k<0) return null;
        List<Spell> listToReturn = new ArrayList<>();
        recursiveGetTopK(root, listToReturn, k);
        return listToReturn;
    }

    private void recursiveGetTopK(Node node,List<Spell> list, int k){
        if(node.right != null)
            recursiveGetTopK(node.right, list, k);
        if(list.size() >= k)
            return;
        list.add(node.spell);
        if(node.left != null)
            recursiveGetTopK(node.left, list, k);
    }


}


