package cn.gobyte.apply;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplyApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Test
    public void fun01() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        // 1. 传统
        for (Object n : list) {
            System.out.println(n);
        }
        System.err.println("----第一个");

        // 2. 拉姆达
        list.forEach(n -> System.out.println(n));

        System.err.println("----第二个");

        // 3. 终极
        list.forEach(System.out::println);

        System.err.println("----最后一个");

    }

}
