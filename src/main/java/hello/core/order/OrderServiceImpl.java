package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor   //final 키워드가 붙어있는 애들을 가지고 생성자를 만들어준다
public class OrderServiceImpl implements OrderService{
    //클래스에 의존한다!
    //private final MemberRepository memberRepository=new MemoryMemberRepository();

    //클래스에 의존하지 않고 추상화인 인터페이스에만 의존한다
    private final MemberRepository memberRepository;

    //클래스에 의존한다!
    // private final DiscountPolicy discountPolicy=new FixDiscountPolicy();

    //클래스에 의존하지 않고 추상화인 인터페이스에 의존한다.
    private final DiscountPolicy discountPolicy;

    //롬복의 @RequiredArgsConstructor에 의해 더 이상 필요없다!
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
            Member member=memberRepository.findById(memberId);
            int discountPrice=discountPolicy.discount(member,itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
