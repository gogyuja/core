package hello.core.singleton;

public class SingletonService {

    //자기 자신안에 자신을 선언한다. 이걸로 인해 요거 하나만 만들어지게 된다.
    //이렇게 하면 자바가 실행될때 자기자신을 하나 만들어둬서얘를 넣어둔다.
    private static final SingletonService instance=new SingletonService();

    private SingletonService(){
    }

    //그리고 조회할때 얘를쓴다.
    public static SingletonService getInstance(){
        return instance;
    }
}
