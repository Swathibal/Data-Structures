import java.util.*;



public class Binarytree {
    Node root;
    boolean flag;
    Queue<Node> q = new LinkedList<>();
    class Node
    {
        int data;
        Node left;
        Node right;

        public Node(int value)
        {
            this.data = value;
            this.left=this.right = null;
        }

        @Override
        public String toString(){
           return String.valueOf(data);
        }
    }

    public void addNodes(int value)
    {
          root = addTreeNodes(root,value);
    }

    private Node addTreeNodes(Node n,int value)
    {
        if(n == null)
            return new Node(value);

        else{
            if(n.right == null )
               n.right = addTreeNodes(n.right, value);
            else 
              n.left = addTreeNodes(n.left, value);
               
            
        }
        return n;
    }

    public void levelOrderInsertion(int value)
    {
        addNodesLevelOrder(value);
    }
    private void addNodesLevelOrder(int value)
    {
        Node n = new Node(value);
        if(root==null)
           root = n;
        else
        {
            if(q.peek().left == null){
               q.peek().left = n;
            }
            else{
               q.peek().right = n;
               q.remove();
            }
        }
        q.add(n);
        
    }

    public void inorder(Node n)
    {
        if(n!=null)
        {
            inorder(n.left);
            System.out.print(n.data+" ");
            inorder(n.right);
        }
        
    }

    public void postorder(Node n)
    {
        if(n!=null)
        {
            postorder(n.left);
            postorder(n.right);
            System.out.print(n.data+" ");
        }
        
    }

    public void preorder(Node n)
    {
        if(n!=null)
        {
            
            System.out.print(n.data+" ");
            preorder(n.left);
            preorder(n.right);
        }
        
    }

    public boolean searchNode(Node node,int value)
    {
        
        if(node.data == value){
           flag = true;
        }
        if(node.left != null)
           searchNode(node.left,value);
        if(node.right != null)
           searchNode(node.right,value);
        
        return flag;
    }

    public void levelOrdertraversal(Node node)
    {
        if(node == null)
           return;
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while(!q.isEmpty())
        {
            System.out.print(q.peek()+" ");
            if(q.peek().left!=null)
               q.add(q.peek().left);
            if(q.peek().right!=null)
               q.add(q.peek().right);
            
            q.remove();
        }
    }
    public int countNodes(Node node)
    {
        int l=0;
        if(node != null){
            l = 1;
            l+=countNodes(node.left);
            l+=countNodes(node.right);
            
        }
        return l;
        
    }

    public int findHeight(Node node)
    {
        int lHeight = 0,rHeight = 0;
        if(node!=null)
        {
            lHeight = rHeight = 1;
            lHeight+=findHeight(node.left);
            rHeight+=findHeight(node.right);
        }
        return Math.max(lHeight,rHeight);
    }

    public int findLevel(int value,Node node)
    {
        return getLevel(value,node,1);
    }

    private int getLevel(int value,Node node,int level)
    {
        if(node == null)
           return 0;
        if(node.data == value)
           return level;
        int fLevel = getLevel(value,node.left,level+1);
        if(fLevel!=0)
           return fLevel;
        fLevel = getLevel(value, node.right,level+1);
        return fLevel;
        
    }
    public static void main(String args[])
    {
        Binarytree b = new Binarytree();

        for(int i =0;i<5;i++)
        {
            b.addNodes(i);  // method to add nodes in a binary tree
            // b.levelOrderInsertion(i);  method to add nodes in a binary tree by level by level

        }


        System.out.println("Inorder :");
        b.inorder(b.root);  // Inorder traversal

        System.out.println("\nPreorder :");
        b.preorder(b.root);   // Preorder Traversal

        System.out.println("\nPostorder :");
        b.postorder(b.root);  //Postorder Travesal

        System.out.println("Level order Traversal:");
        b.levelOrdertraversal(b.root);  // level order traversal or BFS

        if(b.searchNode(b.root,9))    // method to search whether the node is present or not in a binary tree
           System.out.println("\nfound");
        else
           System.out.println("\nnot found");

        System.out.println("No of Nodes:"+b.countNodes(b.root)); // method to count the nodes in the binary tree

        System.out.println("Height of the tree:" + b.findHeight(b.root));  //method to find the height of the binary tree

        System.out.println("\nLevel of a given node: "+ b.findLevel(0,b.root));  // find the level of the binary tree

    }
}
