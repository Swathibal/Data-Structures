import java.util.*;

public class BinarySearchTree {
    Node root;
    boolean flag;
    class Node   // Node class
    {
        int data;
        Node left;
        Node right;

        public Node(int value)
        {
            this.data = value;
            this.left=this.right = null;
        }
    }

    public void addNodes(int value)   // method to add nodes in BST
    {
          root = addTreeNodes(root,value);
    }

    private Node addTreeNodes(Node n,int value)
    {
        if(n == null)
            return new Node(value);

        if(n.data > value)
           n.left = addTreeNodes(n.left, value);
        else if(n.data < value)
           n.right = addTreeNodes(n.right, value);

        return n;
    }

    public void removeNode(int value)  // method to remove node in BST
    {
        Node parent = null;
        removeTreeNodes(root,value,parent);
    }

    private void findSuccessor(Node node,int value)  // method to find the successor with largest value of the node to be deleted in BST if it contains                                               
    {                                                // both left and right child ,so that swapping the node to be deleted with the successor results in BST property
        Node dltRight = node.right;
        Node parent = null;
        Node successor = node;
        while(dltRight!=null)
        {
            parent = successor;
            successor = dltRight;
            dltRight = dltRight.right;
        }
        
        node.data = successor.data;
        successor.data = value;
        removeTreeNodes(successor, value, parent);
    }

    public void removeTreeNodes(Node node,int value,Node parent)
    {
        if(node == null)
          return;
        else
        {
            
            if(node.data == value)
            {
            
                if(node.left == null && node.right == null) // if the node to be deleted has no left and right child
                {
                    
                    if(node == root)  // if the node to be deleted is the root
                        root = null;
                    else if(parent.left == node){  
                        parent.left = null;
                    }
                    else
                        parent.right = null;
                }
                else if(node.left == null && node.right != null)  // if the node to be deleted has right child only
                {
                    
                    if(parent.left == node)
                       parent.left = node.right;
                    else
                       parent.right = node.right;
                }
                else if(node.left != null && node.right == null)  // if the node to be deleted has right child only
                {
        
                    if(parent.left == node)
                        parent.left = node.left;
                    else
                        parent.right = node.left;
                }
                else if(node.left!=null && node.right !=null)  // // if the node to be deleted has both right and left child 
                {
                    findSuccessor(node,value);
                }
            }
            else if(node.data>value)  // recursively find whether the node to be deleted present in the left subtree 
            {
                parent = node;
                removeTreeNodes(node.left,value,parent);
            }
            else if(node.data<value)  // recursively find whether the node to be deleted present in the right subtree 
            {
                parent = node;
                removeTreeNodes(node.right,value,parent);
            }
                
        }
    }

    public void inorder(Node n) // method to find the inorder of the BST
    {
        if(n!=null)
        {
            inorder(n.left);
            System.out.print(n.data+" ");
            inorder(n.right);
        }
        
    }

    public void postorder(Node n) // method to find the postorder of the BST
    {
        if(n!=null)
        {
            postorder(n.left);
            postorder(n.right);
            System.out.print(n.data+" ");
        }
        
    }

    public void preorder(Node n)  // method to find the preorder of the BST
    {
        if(n!=null)
        {
            
            System.out.print(n.data+" ");
            preorder(n.left);
            preorder(n.right);
        }
        
    }

    public boolean searchNode(Node node,int value)  // method to find the node of the BST
    {
        if(node!=null)
        {
            if(node.data == value){
                flag = true;
             }
            else if(node.data > value)
                searchNode(node.left,value);
             else if(node.data < value)
                searchNode(node.right,value);
        }
        
        
        return flag;
    }

    public int countNodes(Node node)  // method to count the nodes in the BST
    {
        int l=0;
        if(node != null){
            l = 1;
            l+=countNodes(node.left);
            l+=countNodes(node.right);
            
        }
        return l;
        
    }

    public int minimumValue(Node node) // method to find the minimum value of the BST
    {
        Node last = null;
        while(node!=null)
        {
            last = node;
            node = node.left;
        }
        return last.data;
    }

    public int maximumValue(Node node) // method to find the maximum value of the BST
    {
        Node last = null;
        while(node!=null)
        {
            last = node;
            node = node.right;
        }
        return last.data;
    }
    public static void main(String args[])
    {
        BinarySearchTree b = new BinarySearchTree();

        Scanner s = new Scanner(System.in);
        System.out.println("enter no of nodes:");
        int num = s.nextInt();
        while(num-- > 0)
        {
            b.addNodes(s.nextInt());  // add nodes in a BST

        }

        b.removeNode(20); // deleting the node in BST

        System.out.println("Minimum value : "+b.minimumValue(b.root));  // find the minimum value in BST
        System.out.println("Maximum value : "+b.maximumValue(b.root));  // find the maximum value in BST

        System.out.println("Inorder :");
        b.inorder(b.root);  // Inorder traversal

        System.out.println("\nPreorder :");
        b.preorder(b.root);  // Prorder traversal

        System.out.println("\nPostorder :");
        b.postorder(b.root);  // Postorder traversal

        if(b.searchNode(b.root,19))   // search a particular node present in BST or not
           System.out.println("\nfound");
        else
           System.out.println("\nnot found");

        System.out.println("No of Nodes:"+b.countNodes(b.root));  // count the number of nodes in BST
    }
}
