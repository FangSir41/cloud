import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 类::实例引用  即 第一个参数为类的实例并且调用实例本身的方法，后面的参数均为实例方法中的参数
 * */
public class TestLambda2 {
    public static String getValue(){
        return "你真可以啊";
    }
    List<Employee> list= Arrays.asList(
            new Employee("小强",100,5000),
            new Employee("小胡",77,8000),
            new Employee("小李",77,3000),
            new Employee("小白龙",77,3000),
            new Employee("大白龙",33,3000));
    @Test
    public void test1(){
        Consumer<String> consumer=System.out::println;
        consumer.accept("你好呀");
        Employee employee=new Employee("小强",22,33);
        Supplier<String> supplier=TestLambda2::getValue;
        System.out.println(supplier.get());


        Function<Integer,String[]> function=String[]::new;
        System.out.println(function.apply(50).length);

        ClassCallInterface1 cc1=ClassCall::getStr;
        cc1.call(new ClassCall("小强"));
        ClassCallInterface2 cc2=ClassCall::getStr;
        cc2.call(new ClassCall("小强"),"小虎","dd");

        MyInterface myInterface=Employee::getEmployeeName;
        myInterface.printName(new Employee("大白龙",33,3000),new Employee("小白龙",33,3000));
    }
    interface MyInterface{
        public void printName(Employee employee1,Employee employee2);
    }
    class ClassCall{
        public ClassCall(String name) {
            this.name = name;
        }


        String name;
        public void getStr(){
            System.out.println("【类::实例】无参数调用");
        }

        public void getStr(String str,String str3){
            System.out.println("【类::实例】多参数调用");
        }
    }
    interface ClassCallInterface1{
        public void call(ClassCall str1);
    }
    interface ClassCallInterface2{
        public void call(ClassCall str1,String str2,String str3);
    }
}

