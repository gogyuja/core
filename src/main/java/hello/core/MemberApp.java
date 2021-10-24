package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        //기존에는 직접 생성해서 만들었다.
        //MemberService memberService=new MemberServiceImpl();

        //AppConfig로 부터 주입받게 된다.(스프링을 사용하고 잇지는 않다.)
        //AppConfig appConfig=new AppConfig();
        //MemberService memberService=appConfig.memberService();

        //이제 스프링으로 해보겠다. 매개변수로 AppConfig 클래스를 주면 AppConfig에 있는 환경설정 정보를 가지고 @Bean을 스프링컨테이너에서 관리하겠다.
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService=applicationContext.getBean("memberService",MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
