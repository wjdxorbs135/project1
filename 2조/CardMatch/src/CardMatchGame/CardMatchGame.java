package CardMatchGame;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CardMatchGame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static CardMatchGame c;
	
	private JPanel contentPane;
	private JButton[][] arrayBtn;
	private DecimalFormat fmt = new DecimalFormat("00");
	javax.swing.Timer playTimer;
	ImageIcon cube = new ImageIcon(CardMatchGame.class.getResource("/imgs/iconbox.png"));
	private JLabel lblTimer;
	
	private int btnAmount;
	private int successBtnAmount;
	private int second = 0;
	private int minute = 0;
	
	private int wSize;
	private int hSize;
	
	private boolean swt1st = false;
	private boolean swt2nd = false;
	private boolean gameStartSwitch = false;
	
	private List<ImageIcon> imageList = new ArrayList<>();
	private Map<String, ImageIcon> imageMap = new HashMap<>();
	private List<JButton> buttonList = new ArrayList<>();
	
	public static CardMatchGame getInstance() {
		return c;
	}
	
	private void gameSuccess() {
		if (wSize == 4)
			new CardMatchSuccess(true, minute, second).setVisible(true);
		else
			new CardMatchSuccess(false, minute, second).setVisible(true);
	}
	
	// 고른 그림이 같은 그림인지 확인
	private void checkSame() {
		
		int img1 = Integer.parseInt(imageMap.get(buttonList.get(0).getActionCommand()).getDescription());
		int img2 = Integer.parseInt(imageMap.get(buttonList.get(1).getActionCommand()).getDescription());
		
		if (img1 == img2) {
			swt1st = false;
			swt2nd = false;
			buttonList.get(0).setEnabled(false);
			buttonList.get(1).setEnabled(false);
			successBtnAmount += 2;
			buttonList.clear();
			if (btnAmount == successBtnAmount) {
				playTimer.stop();
				gameSuccess();
			}
		} else {
			java.util.Timer timer = new java.util.Timer();
			TimerTask tTask = new TimerTask() {
				@Override
				public void run() {
					swt1st = false;
					swt2nd = false;
					buttonList.get(0).setIcon(cube);
					buttonList.get(1).setIcon(cube);
					buttonList.clear();
				}
			};
			timer.schedule(tTask, 1000);
		}
	}
	
	// 클릭한 버튼의 이미지 변경
	private void changeImg(JButton b) {
		if (second > 0) {
			int y = Integer.parseInt(b.getActionCommand().substring(0, 1));
			int x = Integer.parseInt(b.getActionCommand().substring(1, 2));
			String key = "" + y + x;
			b.setIcon(imageMap.get(key));
			b.updateUI();
		}
	}
	
	// 초기 버튼 세팅
	private void buttonSet(int hSize, int wSize) {
		
		arrayBtn = new JButton[wSize][hSize];
		if (hSize == 6 && wSize == 6) {
			for (int i = 0; i < wSize; i++) {
				for (int j = 0; j < hSize; j++) {
					arrayBtn[i][j] = new JButton();
					arrayBtn[i][j].setActionCommand(String.valueOf(j) + i);
					arrayBtn[i][j].setBounds(70 * (i + 1), 70 * (j + 1), 64, 64);
					
					contentPane.add(arrayBtn[i][j]);
				}
			}
		} else if (hSize == 4 && wSize == 4) {
			for (int i = 0; i < wSize; i++) {
				for (int j = 0; j < hSize; j++) {
					arrayBtn[i][j] = new JButton();
					arrayBtn[i][j].setActionCommand(String.valueOf(j) + i);
					arrayBtn[i][j].setBounds((70 * (i + 1)) + 90, (70 * (j + 1)) + 90, 64, 64);
					contentPane.add(arrayBtn[i][j]);
				}
			}
		}
	}
	
	// 초기 버튼 아이콘 변경
	private void changeImgAllBtn(int hSize, int wSize) {
		for (int i = 0; i < wSize; i++) {
			for (int j = 0; j < hSize; j++) {
				arrayBtn[i][j].setIcon(cube);
			}
		}
	}
	
	// 초기 버튼의 이벤트 세팅
	private void buttonClickEventSet(JButton[][] buttonArray, int wSize) {
		for (int i = 0; i < wSize; i++) {
			for(JButton b : buttonArray[i]) {
				b.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if (swt1st == false && swt2nd == false && b.isEnabled() && second > 0) {
							changeImg(b);
							swt1st = true;
							buttonList.add(b);
						} else if (swt1st == true && swt2nd == false && b.isEnabled() && !(b.equals(buttonList.get(0))) && second > 0) {
							changeImg(b);
							swt2nd = true;
							buttonList.add(b);
							checkSame();
						}
					}
				});
			}
		}
	}
	
	// imageMap에 추가
	private void createImageArray(int hSize, int wSize) {
		for (int i = 0; i < wSize; i++) {
			for (int j = 0; j < hSize; j++) {
				imageMap.put("" + i + j, imageList.get(0));
				imageList.remove(0);
			}
		}
	}
	
	private ImageIcon resizeImage(ImageIcon imgIcon, int description) {
		Image image = imgIcon.getImage();
		return new ImageIcon(image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH), String.valueOf(description));
	}
	
	// 
	private void addImageToList(int wSize, int hSize) {
		
		List<ImageIcon> list = new ArrayList<>();
		list.add(resizeImage(new ImageIcon(CardMatchGame.class.getResource("/imgs/img_dog.jpg")), 1));
		list.add(resizeImage(new ImageIcon(CardMatchGame.class.getResource("/imgs/img_dolphin.jpg")), 2));
		list.add(resizeImage(new ImageIcon(CardMatchGame.class.getResource("/imgs/img_eagle.jpg")), 3));
		list.add(resizeImage(new ImageIcon(CardMatchGame.class.getResource("/imgs/img_giraffe.jpg")), 4));
		list.add(resizeImage(new ImageIcon(CardMatchGame.class.getResource("/imgs/img_hedgehod.jpg")), 5));
		list.add(resizeImage(new ImageIcon(CardMatchGame.class.getResource("/imgs/img_kitty.jpg")), 6));
		list.add(resizeImage(new ImageIcon(CardMatchGame.class.getResource("/imgs/img_lamb.jpg")), 7));
		list.add(resizeImage(new ImageIcon(CardMatchGame.class.getResource("/imgs/img_lion.jpg")), 8));
		list.add(resizeImage(new ImageIcon(CardMatchGame.class.getResource("/imgs/img_raccoun.jpg")), 9));
		list.add(resizeImage(new ImageIcon(CardMatchGame.class.getResource("/imgs/img_sheep.jpg")), 10));
		list.add(resizeImage(new ImageIcon(CardMatchGame.class.getResource("/imgs/img_swan.jpg")), 11));
		
		List<ImageIcon> tempList = new ArrayList<>();
		tempList.addAll(list);
		
		if (imageList.size() > 0)
			imageList.clear();
		
		btnAmount = wSize * hSize;
		
		if ((btnAmount / 2) % list.size() == 0) {
			for (int i = 0; i < (btnAmount / 2) / list.size(); i++) {
				for (ImageIcon icon : list) {
					imageList.add(icon);
				}
			}
		} else {
			int halfBtnAmount = btnAmount / 2;
			for (int i = 0; i < halfBtnAmount / list.size(); i++) {
				for (int j = 0; j < list.size(); j++)
					imageList.add(list.get(j));
			}
			for (int i = 0; i < halfBtnAmount % list.size(); i++) {
				int randomInt = (int) (Math.random() * tempList.size());
				imageList.add(tempList.get(randomInt));
				tempList.remove(randomInt);
			}
		}
		
		tempList.clear();
		imageList.addAll(imageList);
		
		Collections.shuffle(imageList);
		
		createImageArray(wSize, hSize);
	}
	
	// 플레이 시간
	private void tickTock() {
		second++;
		if (second == 60) {
			minute++;
			second = 0;
		}
		lblTimer.setText(fmt.format(minute) + ":" + fmt.format(second));
	}
	
	// 처음 잠시 그림 보이기
	private void showAllImage(int hSize, int wSize) {
		for (int i = 0; i < wSize; i++) {
			for (int j = 0; j < hSize; j++) {
				arrayBtn[i][j].setIcon(imageMap.get(String.valueOf(j) + i));
			}
		}
		java.util.Timer timer = new java.util.Timer();
		TimerTask tTask = new TimerTask() {
			@Override
			public void run() {
				changeImgAllBtn(hSize, wSize);
				gameStartSwitch = true;
				playTimer.start();
			}
		};
		timer.schedule(tTask, 3000);
	}
	
	private void defineTimer(JLabel lblTimer) { //타이머
		playTimer = new javax.swing.Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameStartSwitch)
					tickTock();
			}
		});
		playTimer.setRepeats(true);
		playTimer.setCoalesce(true);
		playTimer.setInitialDelay(0);
		playTimer.start();
	}
	
	private void changeToEnable() {
		for (int i = 0; i < wSize; i++) {
			for (int j = 0; j < hSize; j++) {
				arrayBtn[i][j].setEnabled(true);
			}
		}
	}
	
	private void start() {
		buttonSet(hSize, wSize);
		buttonClickEventSet(arrayBtn, wSize);
		addImageToList(hSize, wSize);
		showAllImage(hSize, wSize);
	}
	
	public void restart() {
		this.gameStartSwitch = false;
		this.successBtnAmount = 0;
		this.second = 0;
		this.minute = 0;
		lblTimer.setText(fmt.format(minute) + ":" + fmt.format(second));
		imageMap.clear();
		changeToEnable();
		addImageToList(hSize, wSize);
		showAllImage(hSize, wSize);
	}
	
	public CardMatchGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	public CardMatchGame(int wSize, int hSize) {
		this.wSize = wSize;
		this.hSize = hSize;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		
		JButton btnMainMenu = new JButton();
		btnMainMenu.setText("메인 화면");
		btnMainMenu.setBounds(560, 230, 120, 70);
		btnMainMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CardMatchSelectMain cmsm = new CardMatchSelectMain(CardMatchSelectDifficulty.getInstance());
				cmsm.setResizable(false);
				cmsm.setUndecorated(true);
				cmsm.setVisible(true);
			}
		});
		contentPane.add(btnMainMenu);
		
		JButton btnRestart = new JButton();
		btnRestart.setText("다시 시작");
		btnRestart.setBounds(560, 320, 120, 70);
		btnRestart.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CardMatchRestart cmr = new CardMatchRestart(CardMatchSelectDifficulty.getInstance());
				cmr.setResizable(false);
				cmr.setUndecorated(true);
				cmr.setVisible(true);
			}
		});
		contentPane.add(btnRestart);
		
		JLabel lblTimerIcon = new JLabel();
		lblTimerIcon.setIcon(new ImageIcon(CardMatchGame.class.getResource("/imgs/icontimer.png")));
		lblTimerIcon.setBounds(540, 130, 64, 64);
		contentPane.add(lblTimerIcon);
		
		lblTimer = new JLabel(fmt.format(minute) + ":" + fmt.format(second));
		lblTimer.setBounds(620, 130, 128, 64);
		lblTimer.setFont(new Font("굴림", Font.BOLD, 24));
		contentPane.add(lblTimer);
		
		
		
		defineTimer(lblTimer);
		
		start();
	}
}
