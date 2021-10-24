package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames=ac.getBeanDefinitionNames();
        for(String beanDefinationName : beanDefinitionNames){
            Object bean=ac.getBean(beanDefinationName);
            System.out.println("name = "+beanDefinationName+" object = "+bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames=ac.getBeanDefinitionNames();
        for(String beanDefinationName : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinationName);

            //내가 개발을 위해 등록한 빈들 혹은 외부라이브러리애들
            if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION){
                Object bean=ac.getBean(beanDefinationName);
                System.out.println("name = "+beanDefinationName+" object = "+bean);
            }
        }
    }
}
