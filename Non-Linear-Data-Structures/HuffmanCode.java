import java.util.Comparator;
import java.util.PriorityQueue;

class HuffmanComparator implements Comparator<HuffmanNode>  // class which extends the comparator interface 
{
    public int compare(HuffmanNode a,HuffmanNode b)  // It compares the two minimum frequencies of the character in a string
    {
        return a.fre - b.fre;
    }
}
    
class HuffmanNode  // Node class which stores the character and their frequency
{
        int fre;
        char letter;
        HuffmanNode left,right;

        public HuffmanNode(int item,char c)
        {
            this.fre = item;
            this.letter = c;
            this.left = this.right = null;
        }
}

public class HuffmanCode{


    public static void print(HuffmanNode root,String c)  // print the decoding codes for each character 
    {
        if (root.left == null && root.right == null && Character.isLetter(root.letter)) {

            System.out.println(root.letter + "   |  " + c);
      
            return;
          }
          print(root.left, c + "0");
          print(root.right, c + "1");
    }
    
    public static void main(String args[])
    {
        char[] charArray = { 'A', 'B', 'C', 'D' };
        int[] charfreq = { 5, 1, 6, 3 };

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(charArray.length,new HuffmanComparator());  // priority queue to check for the characters which has to be consider
                                                                                                        //   in making a tree
        for(int i=0;i<charArray.length;i++)
        {
            HuffmanNode h = new HuffmanNode(charfreq[i],charArray[i]);  // node for the tree
            pq.add(h);  // add these nodes in priority queue
        }
        HuffmanNode root = null;  // intialise the root of the tree as null
        while(pq.size()>1)
        {
            HuffmanNode x = pq.peek();   // removing first two least frequencies of character
            pq.poll();

            HuffmanNode y = pq.peek();
            pq.poll();
 
            int freq = x.fre + y.fre;   // add the two least frequencies and create a new node for it
            HuffmanNode z = new HuffmanNode(freq, '-');
            z.left = x;  // assign the left and right of created node
            z.right = y;

            root = z;  // assign the new node as root
            pq.add(z);  // add the new node into the priority queue.
        }
        
        print(root,"");  // print the huffman tree
 
    }
}
