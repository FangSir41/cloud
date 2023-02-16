import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class TestStream {
    List<Employee> employeeList= Arrays.asList(
            new Employee("小强",100,5000),
            new Employee("小胡",77,8000),
            new Employee("小李",35,3000),
            new Employee("小白龙",66,3000),
            new Employee("小白龙",66,3000),
            new Employee("小白龙",66,3000),
            new Employee("大白龙",33,3000));
    @Test
    public void Test1(){
        //1.通过collection提供的stream()或paralleStream()
        List<String> list=new ArrayList<>();
        Stream<String> stream1=list.stream();

        //2.通过Arrays中的静态方法获取stream流
        String[] str={};
        Stream<String> stream2=Arrays.stream(str);

        //3.通过Stream中的静态方法of()获取stream流
        Stream<String> stream3=Stream.of("11","33","22");

        //4.创建无限流
        Stream<Integer> stream4=Stream.iterate(0,(x)->x+2);
        stream4.limit(10).forEach(System.out::println);

        Stream.generate(()-> Math.random())
                .limit(5)
                .forEach(System.out::println);

        employeeList.stream().filter((x)->x.age<77).distinct().forEach(System.out::println);

        employeeList.stream().map(Employee::getSalary).forEach(System.out::println);

        List<Date> dates=Arrays.asList(new Date(),new Date());
        dates.stream().map(Date::getTime).forEach(System.out::println);
    }
    @Test
    public void Test2(){
        List<List<String>> listAll=new ArrayList<>();
        List<String> list1=Arrays.asList("111","222","333");
        List<String> list2=Arrays.asList("444","555","666");
        listAll.add(list1);
        listAll.add(list2);

        listAll.stream().map(TestStream::getStream).forEach(System.out::println);
    }
    @Test
    public void Test3(){
        employeeList.stream().sorted((x,y)->x.getAge()-y.getAge()).forEach(System.out::println);
    }
    public static Stream<String> getStream(List<String> list){
        return list.stream();
    }
    @Test
    public void Test13(){
        Predicate<Integer> predicate=(number)->{
            System.out.println("我有一个参数，有返回值，返回值为boolean类型");
            return number>20;
        };
        System.out.println(predicate.test(20));
    }
class Person{
    String name;
    int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

    @Test
    public void Test() {
        Instant start=Instant.now(); //开始时间
        LongStream.rangeClosed(0,100000000000L)
                .sequential()//串行流
                .reduce(0,Long::sum);
        Instant end=Instant.now(); //结束时间
        //查看执行时间
        System.out.println(Duration.between(start,end).toMillis());

    }
    @Test
    public void timeTest(){
        ZonedDateTime dateTime2=ZonedDateTime.now();
        System.out.println("sub");
        System.out.println(dateTime2);
    }
}