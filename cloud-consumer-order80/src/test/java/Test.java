import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    public static void main(String[] args){
        AtomicInteger atomicInteger=new AtomicInteger(10);
        Scanner scanner=new Scanner(System.in);
        int current,next,inputValue;
        for (;;) {
            System.out.println("请输入一个数字");
            inputValue= Integer.parseInt(scanner.nextLine());
            current=atomicInteger.get();
            next=current>= Integer.MAX_VALUE?0:current+1;
            if(atomicInteger.compareAndSet(inputValue,next))
                break;
            System.out.println("这个数对不上，请重新输入");
        }
        System.out.println("atomic目前数字为："+atomicInteger.get());
    }
}
