import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.green.ex1.Operation;

public class Ex1Client {

	public static void main(String[] args) {
		
		// �����̳� ����
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		// Operation Ŭ���� Ÿ���� ���� op = (������Ʈ) �����̳�.get(<bean>)
		Operation op = (Operation) container.getBean("opBean");

		System.out.println("msg ȣ�� >>> ");
		op.message();

		System.out.println("m ȣ�� >>> ");
		op.m();

		System.out.println("k ȣ�� >>> ");
		op.k();
	}

}
