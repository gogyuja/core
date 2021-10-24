package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        //System.out.println("memberService = " + memberService);
        //System.out.println("memberService = " + memberService.getClass());
        //MemberServiceImpl의 인스턴스면 성공
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        //System.out.println("memberService = " + memberService);
        //System.out.println("memberService = " + memberService.getClass());
        //MemberServiceImpl의 인스턴스면 성공
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        //System.out.println("memberService = " + memberService);
        //System.out.println("memberService = " + memberService.getClass());
        //MemberServiceImpl의 인스턴스면 성공
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByNameX(){
        //등록되지도 않은 빈을 조회해보겠다.
        //ac.getBean("xxxxxx", MemberService.class);

       //junit.jupiter꺼다
       //오른쪽에 있는 ac.getBean을 실행시켜 에러를 터트렸을때 NoSuchBean이 반응해야한다다
       assertThrows(NoSuchBeanDefinitionException.class,
                ()->ac.getBean("xxxxxx", MemberService.class));
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        //System.out.println("memberService = " + memberService);
        //System.out.println("memberService = " + memberService.getClass());
        //MemberServiceImpl의 인스턴스면 성공
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
}
