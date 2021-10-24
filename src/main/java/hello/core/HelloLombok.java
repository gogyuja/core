package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args){
        HelloLombok helloLomobok=new HelloLombok();
        helloLomobok.setName("asdfa");

        String name=helloLomobok.getName();
        System.out.println("name = " + name);

        System.out.println("helloLomobok = " + helloLomobok);
    }
}
