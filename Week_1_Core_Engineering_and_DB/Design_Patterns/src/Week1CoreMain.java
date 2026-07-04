import com.structures.DataEngine;
import java.util.Arrays;
public class Week1CoreMain {
    public static void main(String[] args) {
        System.out.println("=== WEEK 1: CORE JAVA DATA STRUCTURES ===\n");
        int[] numbers = {12, 24, 35, 47, 58, 69, 80};
        int target = 47;
        int result = DataEngine.binarySearch(numbers, target);
        System.out.println("Array: " + Arrays.toString(numbers));
        System.out.println("Searching for " + target + ". Found at index: " + result + "\n");
        DataEngine.Node head = new DataEngine.Node(10);
        head.next = new DataEngine.Node(20);
        head.next.next = new DataEngine.Node(30);
        System.out.print("Custom Linked List: ");
        DataEngine.printList(head);
    }
}
