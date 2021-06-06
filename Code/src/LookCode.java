import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;



import java.util.*;
import java.util.Timer;
import java.io.*;
import java.net.*;
import java.sql.ClientInfoStatus;

public class LookCode extends JFrame {

	TetrisPanel TP = new TetrisPanel();
	//TetrisPanel2 TP2 = new TetrisPanel2();
	JLabel scoreInt = new JLabel();
	JLabel scoreString = new JLabel();
	TetrisThread tThread;
	Client tclient = new Client();
	 private Image backgroundImage;
	 JOptionPane end = new JOptionPane();
	static int blocksize = 20;
	 Timer timer = new Timer();

	int End = 0;
	int random = 0, random2 = (int) (Math.random() * 7);

	int score = 0;

	int wid = 100;
	int hgt = 0;
	int rotation = 0;

	boolean limit = false;

	// 블록들의 좌표 저장
	int curX[] = new int[4], curY[] = new int[4];
  
	int blocks[][][][] = { {
			//■
			//■■■
			{ { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
			{ { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
			{ { 1, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } },
			{ { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } } },

			{

					//  ■
					//■■■
					{ { 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 1, 0, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } } },

			{
					//  ■■
					//  ■■
					{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } } },

			{
					// ■■■■
					{ { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } } },

			{
					//■
					//■■■
					{ { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } } },

			{
					//  ■■
					//   ■■
					{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } } },

			{
					//    ■■
					//  ■■
					{ { 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } } } };

	public static int[][] gameboardB = { { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },

			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	LookCode() {
		setTitle("테트리스");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setVisible(true);
		
		

		tThread = new TetrisThread();

		// 패널 사이즈
		TP.setSize(1280, 720);
		TP.setBackground(Color.CYAN);
//		TP2.setSize(640, 720);
//		TP2.setBackground(Color.green);
		add(TP);
//		add(TP2);
//		TP2.setLocation(640, 0);
		 
		 

		scoreInt.setFont(new Font("arial", Font.PLAIN, 30));
		scoreString.setFont(new Font("arial", Font.PLAIN, 30));

		// 키 입력
		TP.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				int keyCode = e.getKeyCode();

				if (keyCode == KeyEvent.VK_UP) {
					TP.moveUp();
				}

				if (keyCode == KeyEvent.VK_DOWN) {
					TP.moveDown();
				}

				if (keyCode == KeyEvent.VK_LEFT) {
					TP.moveLeft();
				}

				if (keyCode == KeyEvent.VK_RIGHT) {
					TP.moveRight();
				}

			}
		});

		// 패널이 여러개 일 경우, 키 입력을 먼저 받도록 설정
		TP.requestFocus(true);

		// 스레드 시작
		tThread.start();

		//클라이언트 시작
		tclient.run();
		//tclient.paintComponent(null);

	}

	class TetrisPanel extends JPanel {
		public void paintComponent(Graphics g) {

			int cnt = 0, cnt2 = 0;
			TP.requestFocus(true);
			super.paintComponent(g);

			scoreInt.setLocation(525, 300);
			scoreString.setLocation(480, 250);

			TP.add(scoreInt);
			TP.add(scoreString);

			scoreInt.setText(Integer.toString(score * 100));
			scoreString.setText("SCORE");

			g.setColor(Color.ORANGE); // 새로 떨어지는 블럭,미리보기  블럭 색깔
			
			try {
				backgroundImage = ImageIO.read(new File("bg.jpg"));
				 g.drawImage(backgroundImage, 0, 0, null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			

			// 다음 나올 도형 출력
			blockLookAhead(g);

			// 벽이 천장에 닿으면 게임 오버
			gameOverCheck();

			// 한 행이 모두 블록으로 채워진 경우 블록들 제거(채워지지않은 경우 블록 떨어지도록)
			removeBlock(cnt, cnt2, g);

			// 블록이 벽에 착지하면 블록->벽으로 변환(떨어지는 블록 초기화)
			blockToWall();

			// 벽들 생성
			makeBlock(g);

			// 테두리 생성
			makeBorder(g);

			if (End == 1) {
				random2 = (int) (Math.random() * 7);
				End = 0;
			}
		}

		// 다음 나올 도형 출력
		public void blockLookAhead(Graphics g) {
			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					if (blocks[random2][0][a][b] == 1) {
						g.fill3DRect(b * blocksize + 500, a * blocksize + 130, blocksize, blocksize, true);
					}
				}
			}
		}

		// 벽이 천장에 닿으면 게임 오버
		public void gameOverCheck() {
			for (int x = 1; x < 12; x++) {
				if (gameboardB[2][x] == 1) {
					limit = true;
					//gameOver();
				}
			}

		}
		
		public void gameOver() {
			end.showMessageDialog(null, "게임 끝");
			
		}

		// 한 행이 모두 블록으로 채워진 경우 블록들 제거(채워지지않은 경우 블록 떨어지도록)
		public void removeBlock(int cnt, int cnt2, Graphics g) {
			for (int y = 0; y < 29; y++) {
				for (int x = 1; x < 20; x++) {
					if (gameboardB[y][x] == 1) {
						cnt2++;
					}
				}
				if (cnt2 == 19) {
					for (int i = y; i > 1; i--)
						for (int j = 1; j < 21; j++) {
							gameboardB[i][j] = 0;
							gameboardB[i][j] = gameboardB[i - 1][j];
						}
					score++;

					// 상대편에 깨지 못하는 블럭 한줄 생성
					// 어디다 어떻게 생성 할 지 정해야 함

				} else {
					blockDown(cnt, g); // 한 행이 모두 블록으로 채워지지 않을 때만 블록이 내려가도록 함
				}
				cnt2 = 0;
			}
		}

		// 떨어 진 블록
		public void makeBlock(Graphics g) {
			g.setColor(Color.GRAY);
			for (int y = 0; y < 29; y++) {
				for (int x = 1; x < 20; x++) {
					if (gameboardB[y][x] == 1) {
						Client.array=gameboardB; 
						g.fill3DRect(x * blocksize + 20, y * blocksize + 60, blocksize, blocksize, true);
					
					}
				}
			}
			
			g.setColor(Color.GRAY);
			for (int y = 0; y < 29; y++) {
				for (int x = 1; x < 20; x++) {
					if (Client.arrayB[y][x] == 1) {
						g.fill3DRect(640+(x * blocksize + 20), y * blocksize + 60, blocksize, blocksize, true);
					}
				}
			}
		}

		// 떨어지는 블록
		public void blockDown(int cnt, Graphics g) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					if (blocks[random][rotation][j][k] == 1) {
						curX[cnt] = ((k * blocksize) + wid) / blocksize;
						curY[cnt] = ((j * blocksize) + hgt) / blocksize;//curX,Y[0][1][2][3]에 좌표 4개 저장

						// 위치 수정

						g.fill3DRect(curX[cnt] * blocksize + 20, curY[cnt] * blocksize + 60, blocksize, blocksize,
								true);

						cnt++;
					}
				}
			}
		}

		// 블록이 벽에 착지하면 블록->벽으로 변환(떨어지는 블록 초기화)
		// 떨어지던 블록이 벽이 되는지 검사
		// 벽이 되면 wid=120, hgt=0 으로 블록 초기화, rotation도 초기화 
		public void blockToWall() {
			try {
				for (int z = 0; z < 4; z++)
					if (gameboardB[curY[z] + 1][curX[z]] == 1)
						for (int j = 0; j < 4; j++) {

							gameboardB[curY[j]][curX[j]] = 1;
							wid = 100;
							hgt = 0;
							End = 1;
							rotation = 0;
							random = random2;
						}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Error");

				for (int i = 0; i < 4; i++) {
					System.out.print("(" + curY[i] + "," + curX[i] + ")");
				}

				System.out.println();
			}

		}

		// 왼쪽 벽에 충돌하면 못움직이도록
		public int collision_LEFT() {
			for (int i = 0; i < 4; i++) {
				if (gameboardB[curY[i]][curX[i] - 1] == 1) // 충돌시 1 반환
					return 1;
			}
			return 0; // 충돌하지 않으면 0 반환
		}

		// 오른쪽 벽에 충돌하면 못 움직이도록
		public int collision_RIGHT() {

			for (int i = 0; i < 4; i++) {
				if (gameboardB[curY[i]][curX[i] + 1] == 1) // 충돌시 1반환
					return 1;
			}
			return 0; // 충돌하지 않으면 0반환
		}

		// curX,Y에 다음 회전 도형의 절대좌표를 모두 기록해두고, 만약 오른쪽이나 왼쪽 X좌표1,2칸 안에 벽이 있으면 그만큼 오른쪽 혹은 왼쪽으로 밀어서 배치
		public void rotationCheck() {
			// curX,Y에 다음 회전 도형의 절대좌표를 모두 기록해두고, 밑에 구문에서 그 절대좌표의 값이 벽에 닿는지 판단
			int cnt2 = 0;
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					int rotation2 = (rotation % 4) + 1;
					if (rotation2 == 4)
						rotation2 = 0;
					if (blocks[random][rotation2][j][k] == 1) {
						curX[cnt2] = ((k * blocksize) + wid) / blocksize;
						curY[cnt2] = ((j * blocksize) + hgt) / blocksize;
						cnt2++;
					}
				}
			}

			// curX,Y에 저장된 좌표를 이용
			int chk = 0;
			int blank = 0;
			int error = 0;

			// 왼쪽 벽
			if (gameboardB[curY[0]][curX[0]] == 1 || (random == 6 && gameboardB[curY[2]][curX[2]] == 1)
					|| (random == 1 && gameboardB[curY[1]][curX[1]] == 1)) {
				chk = 1; // 만약 다음 회전한 도형의 위치가 벽과 겹친다면 chk=1로 표시함           
				error++;
				System.out.println("chk1");

				if (random == 3) { // 일자막대의 경우 회전할 여유가 있는 공백이 없으면 회전막음
					for (int i = 1; i < 5; i++) {
						if (gameboardB[curY[0]][curX[0] + i] == 0) {
							blank++;
						}
					}

					if (blank < 4) {
						chk = 4;
					}

					System.out.println(blank);
				} else { // 그 외의 경우 회전할 여유가 없는 공백이 없으면 회전 막음
					for (int i = 1; i < 4; i++)
						if (gameboardB[curY[0]][curX[0] + i] == 0)
							blank++;
					if (blank < 3)
						chk = 4;
					System.out.println("blank2");
					System.out.println(blank);
				}

			}

			//오른쪽 벽
			else if (gameboardB[curY[2]][curX[2]] == 1) {
				error++;
				chk = 2; // 만약 다음 회전한 도형의 위치가 벽과 겹친다면 chk=2로 표시함  
				System.out.println("chk2");

				for (int i = 1; i < 5; i++)
					if (gameboardB[curY[2]][curX[2] - i] == 0)
						blank++;
				if (blank < 4)
					chk = 4;
				System.out.println("blank2");
				System.out.println(blank);

			} else if (gameboardB[curY[3]][curX[3]] == 1) {
				error++;
				chk = 3; // 만약 다음 회전한 도형의 위치가 벽과 겹친다면 chk=3로 표시함    
				System.out.println("chk3");
				for (int i = 0; i < 5; i++)
					if (gameboardB[curY[3]][curX[3] - i] == 0)
						blank++;
				if (blank < 4)
					chk = 4;
				System.out.println(blank);

			}

			if (chk == 1) {
				// chk = 1(다음 회전한 도형의 위치가 벽과 중복되면)면 wid(가로)로 30이동
				wid += blocksize;
				rotation++;
				rotation = rotation % 4;
			} else if (chk == 2) {
				wid -= blocksize * 2;
				rotation++;
				rotation = rotation % 4;
			} else if (chk == 3) {
				wid -= blocksize;
				rotation++;
				rotation = rotation % 4;
			} else if (chk == 4) {
				System.out.println("ban");
			} else {
				rotation++;
				rotation = rotation % 4;
			}

		}

		// board 테두리 
		public void makeBorder(Graphics g) {
			g.setColor(Color.GRAY);

			g.draw3DRect(30, 100, 5, 555, true); // 왼쪽기둥
			g.draw3DRect(425, 100, 5, 555, true); // 오른쪽기둥

			//(x, y, 길이, 굵기)
			g.draw3DRect(30, 650, 400, 5, true); // 바닥
			g.draw3DRect(30, 100, 400, 5, true); // 천장
			
			g.setColor(Color.blue);

			g.draw3DRect(670, 100, 5, 555, true); // 왼쪽기둥
			g.draw3DRect(1065, 100, 5, 555, true); // 오른쪽기둥

			//(x, y, 길이, 굵기)
			g.draw3DRect(670, 650, 400, 5, true); // 바닥
			g.draw3DRect(670, 100, 400, 5, true); // 천장
		}

		void down() {
			hgt += blocksize;
			TP.repaint();
		}

		void moveUp() {
			rotationCheck();
			if (limit == false) {
				repaint();
			}
		}

		void moveDown() {
			if (limit == false) {
				hgt += blocksize;
				TP.repaint();
			}
		}

		void moveLeft() {
			int sel = collision_LEFT();

			// sel이 1이면 충돌, 0이면 충돌X
			if (sel == 0 && limit == false) {
				wid -= blocksize;
				TP.repaint();
			}
		}

		void moveRight() {
			int sel = collision_RIGHT();

			// sel이 1이면 충돌, 0이면 충돌X
			if (sel == 0 && limit == false) {
				wid += blocksize;
				TP.repaint();
			}
		}
	}

	class TetrisPanel2 extends JPanel {

		JTextField text;
		
		public void paintComponent(Graphics g) {
			//super.paintComponent(g);
			//g.setColor(Color.blue);
			makeBorder2(g);
			makeBlock2(g);

		}

		public void makeBorder2(Graphics g) {
			g.setColor(Color.blue);

			g.draw3DRect(30, 100, 5, 555, true); // 왼쪽기둥
			g.draw3DRect(425, 100, 5, 555, true); // 오른쪽기둥

			//(x, y, 길이, 굵기)
			g.draw3DRect(30, 650, 400, 5, true); // 바닥
			g.draw3DRect(30, 100, 400, 5, true); // 천장

		}

		public void makeBlock2(Graphics g) {
			g.setColor(Color.GRAY);
			for (int y = 0; y < 29; y++) {
				for (int x = 1; x < 20; x++) {
					if (Client.arrayB[y][x] == 1) {
						g.fill3DRect((x * blocksize + 20), y * blocksize + 60, blocksize, blocksize, true);
					}
				}
			}
		}

	}

	class TetrisThread extends Thread {
		 int speed =500;
		TetrisPanel TP = new TetrisPanel();
		//TetrisPanel2 TP2 = new TetrisPanel2();
		
		public void run() {
			 
			  TimerTask task1 = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						   speed = 400;
						
					}
					
				};
				
				TimerTask task2 = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						 speed = 300;
					}
					
				};
				TimerTask task3 = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						 speed = 220;
					}
					
				};
				TimerTask task4 = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						 speed = 140;
					}
					
				};
				TimerTask task5 = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						 speed = 140;
					}
					
				};
				
				timer.schedule(task1, 5000);
				timer.schedule(task2, 10000);
				timer.schedule(task3, 15000);
				timer.schedule(task4, 20000);
				timer.schedule(task5, 25000);
			while (true) {
				try {
					while(Client.portnum==null) {
						 tThread.interrupt();
						 
						 if(Client.portnum!=null) {
								tThread.run();
							}
					}
					

					sleep(speed);
					if (limit == false) // limit이 false일 경우에만 작동. true가 되면 테트리스 작동중지
						TP.down();
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	////
	static class Client extends Thread {

		String IP ="";
		Socket s = null;
		
		static String  portnum;
		ObjectOutputStream os = null;//ㅇ
   
		ObjectInputStream is = null;

		//static int[][] arrayB = ServerLookCode.gameboardA;
		
		static int[][]array = gameboardB;
		static int[][]arrayB = new int [29][20];
	
		int port;
		@Override
		public void run() {

			try {
				IP = JOptionPane.showInputDialog("ip를 입력하세요");
				portnum = JOptionPane.showInputDialog("포트번호를 입력하세요");
			
				
				
				 port = Integer.parseInt(portnum);

			
				s = new Socket(IP, port);
			
				while (true) {
					//System.out.println(Arrays.deepToString(array));
					os = new ObjectOutputStream(s.getOutputStream());
					os.writeObject(array); //출-b - 클라
					
					
					
					
					is = new ObjectInputStream(s.getInputStream());
					arrayB = (int[][]) is.readObject(); //입 -a  -서버
					
					

					
				}

			} catch (Exception e) {

			}
		}
	}

	////

	public static void main(String[] args) {
		new LookCode();

	}
}