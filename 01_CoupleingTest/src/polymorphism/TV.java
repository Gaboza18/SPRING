package polymorphism;

/*
 * 인터페이스: 동일한 목적하에 동일한 기능을 수행하게 강제하는것(자바의 '다형성' 극대화)
 * TV 인터페이스: TV가 동작하는 모든 행동들을 모아둠
 */
public interface TV { 
	
	public void powerOn(); // 전원 킨다
	public void powerOff(); // 전원 끈다
	public void volumeUp(); // 볼륨을 올린다 
	public void volumeDown(); // 볼륨을 내린다
	
}
