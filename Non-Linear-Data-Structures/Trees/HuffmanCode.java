import java.util.Comparator;
import java.util.PriorityQueue;

class HuffmanComparator implements Comparator<HuffmanNode>
{
    public int compare(HuffmanNode a,HuffmanNode b)
    {
        return a.fre - b.fre;
    }
}
    
class HuffmanNode
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
    public static void print(HuffmanNode root,String c)
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

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(charArray.length,new HuffmanComparator());
        
        for(int i=0;i<charArray.length;i++)
        {
            HuffmanNode h = new HuffmanNode(charfreq[i],charArray[i]);
            pq.add(h);
        }
        HuffmanNode root = null;
        while(pq.size()>1)
        {
            HuffmanNode x = pq.peek();
            pq.poll();

            HuffmanNode y = pq.peek();
            pq.poll();
 
            int freq = x.fre + y.fre;
            HuffmanNode z = new HuffmanNode(freq, '-');
            z.left = x;
            z.right = y;

            root = z;
            pq.add(z);
        }
        
        print(root,"");

    }
}
