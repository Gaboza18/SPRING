package collection.injection;

import java.util.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		// 컨테이너 선언
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		CollectionBean bean = (CollectionBean) container.getBean("CollectionBean");

		// List 멤버의 의존성 주입 예
		List<String> addrList = bean.getAddressList();

		for (String addr : addrList) {
			System.out.println(addr);
		}

		// set 타입 멤버 의존성 주입 예
		Set<String> movies = bean.getMovieList();
		System.out.println();

		for (String movie : movies) {
			System.out.println(movie);
		}

		// Map 타입 멤버 의존성 주입 예
		Map<String, String> scoreMap = bean.getPrefList();

		Set<String> keys = scoreMap.keySet();
		System.out.println();

		for (String key : keys) {
			System.out.println(key + ":" + scoreMap.get(key));
		}

		// Property 타입 의존성 주입 예
		Properties props = bean.getPropList();
		Set<String> items = (Set) props.keySet();
		System.out.println();

		for (String item : items) {
			System.out.println(item + ":" + props.getProperty(item));
		}
	}

}
