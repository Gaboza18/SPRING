import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.green.ex1.Operation;

public class Ex1Client {

	public static void main(String[] args) {
		
		// 컨테이너 선언
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		// Operation 클래스 타입의 변수 op = (오브젝트) 컨테이너.get(<bean>)
		Operation op = (Operation) container.getBean("opBean");

		System.out.println("msg 호출 >>> ");
		op.message();

		System.out.println("m 호출 >>> ");
		op.m();

		System.out.println("k 호출 >>> ");
		op.k();
	}

}
