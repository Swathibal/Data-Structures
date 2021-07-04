import java.util.Arrays;

public class Heap {

    public void buildMaxHeap(int arr[],int N)   // method to build max-heap
    {
        for(int i = (N/2)-1;i>=0;i--)
            max_heapify(arr,i,N);
    }

    public void buildMinHeap(int arr[],int N)   // method to build min-heap
    {
        for(int i = (N/2)-1;i>=0;i--)
        {
            min_heapify(arr,i,N);
        }
    }
    public void max_heapify(int arr[],int i,int N)  // method to max-heapify the array
    {
        int lChild = 2*i + 1;
        int rChild = 2*i + 2;
        int largest = 0;

        if(lChild < N && arr[lChild] > arr[i])
           largest = lChild;
        else
           largest = i;
        
        if(rChild < N && arr[rChild]>arr[largest])
           largest = rChild;

        if(largest!=i)
        {
           swap(i,largest,arr);
           max_heapify(arr, largest, N);

        }

    }

    public void min_heapify(int arr[],int i,int N)   // method to max-heapify the array
    {
        int lChild = 2*i + 1;
        int rChild = 2*i + 2;
        int smallest = 0;

        if(lChild<N && arr[lChild] < arr[i])
           smallest = lChild;
        else
           smallest = i;
        
        if(rChild<N && arr[rChild]<arr[smallest])
           smallest = rChild;

        if(smallest != i)
        {
            swap(i,smallest,arr);
            min_heapify(arr, smallest, N);
        }


    }

    private void swap(int i,int j,int arr[])  // swapping the array elements
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void HeapSort(int arr[],int N)   // heap sort
    {
        buildMaxHeap(arr, N);
        for(int i = N-1;i>0;i--)
        {
            swap(0,i,arr);
            N--;
            max_heapify(arr, 0, N);
        }
    }

    public static void main(String args[])
    {

        Heap h =  new Heap();
        int arr[] = {1,2,3,4,5};
        int N = arr.length;

        System.out.println("Before Max heapify:"+Arrays.toString(arr));
        h.buildMaxHeap(arr,N);
        System.out.println("After Max heapify:"+Arrays.toString(arr));

        System.out.println("\nHeap sort:");
        h.HeapSort(arr,N);
        System.out.println(Arrays.toString(arr));

        System.out.println("Before Min heapify:"+Arrays.toString(arr));
        h.buildMinHeap(arr,N);
        System.out.println("After Min heapify:"+Arrays.toString(arr));
    

    }
}
