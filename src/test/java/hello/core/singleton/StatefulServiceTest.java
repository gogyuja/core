package hello.core.singleton;

import hello.core.beanfind.ApplicationContextExtendsFindTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac =new AnnotationConfigApplicationContext(Testconfig.class);
        StatefulService statefulService1= ac.getBean(StatefulService.class);
        StatefulService statefulService2= ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        statefulService1.order("userA",10000);
        //ThreadB : B사용자 20000원 주문
        statefulService2.order("userB",20000);

        //이거 블로그 정리 하면서 강의 한번더봐라 멀티스레드... 싱글톤방식의 주의점
        //ThreadA : 사용자A 주문 금액 조회
        int price=statefulService1.getPrice();
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class Testconfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}