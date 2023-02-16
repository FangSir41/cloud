import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class TestLambda1 {
    /**
     * 语法1：无参数，无返回值
     * */
    @Test
    public void test1(){
        Runnable runnable=() -> System.out.println(1);
        runnable.run();
    }
    /**
     * 语法2：一个参数，无返回值
     * 一个参数小括号可以不写
     */
    @Test
    public void test2(){
        Consumer<String> con=x-> System.out.println(x);
        con.accept("靠");
    }

    /**
     * 语法3：多个参数，有返回值
     *
     */
    @Test
    public void test3(){
        Comparator<Integer> com=(x,y)->{
            System.out.println(x);
            System.out.println(y);
            return x+y;
        };
        Integer i=com.compare(1,5);
        System.out.println(i);
    }
    /**
     * 语法4：多个参数，有返回值
     * 只有一条语句，大括号和return都可以不写
     */
    @Test
    public void test4(){
        Comparator<Integer> com=(x,y)->x-y;
        System.out.println(com.compare(100,99));
    }

    @Test
    public void test5(){
        List<Employee> list= Arrays.asList(
                new Employee("小强",100,5000),
                new Employee("小胡",77,8000),
                new Employee("小李",77,3000),
                new Employee("小白龙",77,3000),
                new Employee("大白龙",33,3000));
//        Collections.sort(list,(e1,e2) -> e1.age>e2.age?1:e1.age==e2.age?e1.name.compareTo(e2.name):-1);
        Collections.sort(list,(e1,e2)->Integer.compare(e1.age,e2.age)==0?e1.name.compareTo(e2.name):Integer.compare(e1.age,e2.age));
        for (Employee employee : list) {
            System.out.println(employee.toString());
        }
    }
    @Test
    public void test6(){
        TestLambda test=new TestLambda();
        System.out.println(test.getUpperCase("abc你大爷的 你真是更可以爱的高发ggg",str->str.toUpperCase().substring(1,4)));
    }
    class TestLambda{

        public String getUpperCase(String str, TestInterface test){
            return test.getValue(str);
        }
    }
    interface TestInterface{
        public String getValue(String str);
    }

    @Test
    public void test7(){
        System.out.println(getLongNoCal(25,30,(num1,num2)->num1+num2));
        System.out.println(getLongNoCal(25,30,(num1,num2)->num1*num2));
    }
    public long getLongNoCal(long num1,long num2,Test2Interface test){
        return test.getValue(num1,num2);
    }
    interface Test2Interface<T,R>{
        public long getValue(long num1,long num2);
    }
    @Test
    public void Test8(){
        Employee employee=new Employee("小强",20,5000);
        Consumer<Employee> consumer=e->e.name="小虎";
        consumer.accept(employee);
        System.out.println(employee);
        Supplier supplier=()->new Employee("小呆货",55,3333);
        System.out.println(supplier.get());
        Function<Employee,Employee> function=(obj)->{obj.name="小李";return obj;};
        System.out.println(function.apply(employee));
        Predicate<Integer> predicate=(number)->number>20;
        System.out.println(predicate.test(20));
    }
}
