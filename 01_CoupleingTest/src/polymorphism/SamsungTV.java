package polymorphism;

public class SamsungTV implements TV {

	@Override
	public void powerOn() {
		System.out.println("»ï¼º TV -- Àü¿øÀ» ÄÒ´Ù");
	}

	@Override
	public void powerOff() {
		System.out.println("»ï¼º TV -- Àü¿øÀ» ²ö´Ù");
	}

	@Override
	public void volumeUp() {
		System.out.println("»ï¼º TV -- º¼·ıÀ» ¿Ã¸°´Ù");
	}

	@Override
	public void volumeDown() {
		System.out.println("»ï¼º TV -- º¼·ıÀ» ³»¸°´Ù");
	}

}
