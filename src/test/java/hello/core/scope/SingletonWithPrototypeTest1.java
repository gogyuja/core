package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1=ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonCoinetUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1= clientBean1.logic();

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2=clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean{
        //private final PrototypeBean prototypeBean;//생성시점에 이미 주입. 계속 같은걸 사용. 프로토타입이 지속되는경우
        //프로토타입이 지속되는경우
        /*  @Autowired
        public ClientBean(PrototypeBean prototypeBean){
            this.prototypeBean=prototypeBean;
        }*/
        
        //프로토타입이 지속되는경우
        /*public int logic(){
            prototypeBean.addCount();
            int count= prototypeBean.getCount();
            return count;
        }*/

        
        //싱글톤내에서도 프로토타입빈이 그때그때 생성될수있도록 찾는다. DL을 간단하게. 근데 스프링에 의존적 Provider에 비해 더 많은 기능 제공
        /*@Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;//ObjectProvide가 프로토타입빈을 찾아준다.

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            int count=prototypeBean.getCount();
            return count;
        }*/

        //라이브러리를 임포트해서 사용하는 방법. 스프링에 의존적이지않다. 단순하다.
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;//ObjectProvide가 프로토타입빈을 찾아준다.

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count=prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count=0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init = " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
