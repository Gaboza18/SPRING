import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 *  TCP 클라이언트 프로그램
 */
 
public class TcpClient {

	public static void main(String[] args) {

		String serverIp = "127.0.0.1"; // 내 컴퓨터(localhost)의 IP 주소
		System.out.println("서버에 연결 중입니다. 서버Ip: " + serverIp);

		try {
			// 서버에 연결 요청: 서버의 IP주소와 포트번호를 지정
			Socket socket = new Socket(serverIp, 49152);

			// 데이터를 수신할 입력 스트림을 얻어온다
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);

			// 서버에서 보낸 메세지 수신
			System.out.println("서버에서 받은 메시지: " + dis.readUTF());
			dis.close(); // 입력 스트림 close
			socket.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
