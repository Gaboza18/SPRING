package polymorphism;

public class LgTv implements TV {

	@Override
	public void powerOn() {
		System.out.println("¿¤Áö TV -- Àü¿øÀ» ÄÒ´Ù");
	}

	@Override
	public void powerOff() {
		System.out.println("¿¤Áö TV -- Àü¿øÀ» ²ö´Ù");
	}

	@Override
	public void volumeUp() {
		System.out.println("¿¤Áö TV -- º¼·ıÀ» ¿Ã¸°´Ù");
	}

	@Override
	public void volumeDown() {
		System.out.println("¿¤Áö TV -- º¼·ıÀ» ³»¸°´Ù");
	}

}
