import java.util.Scanner;

public class TestClass {
    private int square;
    public TestClass(){
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        this.square = num*num;
    }
    public int getSquare(){
        return this.square;
    }
}
