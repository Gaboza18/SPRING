package usual_case;

public class TvUser {

	public static void main(String[] args) {
		
		/*
		 * ���յ��� ���� �ڹ� ���α׷�(���������� �����)
		 */
		
		/*
		SamsungTV stv = new SamsungTV(); // �Ｚ TV ��ü ����
		
		stv.powerOn(); 
		stv.volumeUp();
		stv.volumeDown();
		stv.powerOff();
		*/
		
		/*
		 * TV ��ü�� �Ｚ -> ���� �ٲ� ���
		 * ��ü ������ �޼ҵ� ȣ���� ��� ���α׷��Ӱ� �����ؾ� �Ѵ�
		 */
		
		LgTV ltv = new LgTV();
		
		ltv.turnOff();
		ltv.turnOn();
		ltv.soundUp();
		ltv.soundDown();
	}

}
