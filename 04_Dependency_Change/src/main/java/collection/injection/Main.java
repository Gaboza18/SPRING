package collection.injection;

import java.util.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		// �����̳� ����
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		CollectionBean bean = (CollectionBean) container.getBean("CollectionBean");

		// List ����� ������ ���� ��
		List<String> addrList = bean.getAddressList();

		for (String addr : addrList) {
			System.out.println(addr);
		}

		// set Ÿ�� ��� ������ ���� ��
		Set<String> movies = bean.getMovieList();
		System.out.println();

		for (String movie : movies) {
			System.out.println(movie);
		}

		// Map Ÿ�� ��� ������ ���� ��
		Map<String, String> scoreMap = bean.getPrefList();

		Set<String> keys = scoreMap.keySet();
		System.out.println();

		for (String key : keys) {
			System.out.println(key + ":" + scoreMap.get(key));
		}

		// Property Ÿ�� ������ ���� ��
		Properties props = bean.getPropList();
		Set<String> items = (Set) props.keySet();
		System.out.println();

		for (String item : items) {
			System.out.println(item + ":" + props.getProperty(item));
		}
	}

}
