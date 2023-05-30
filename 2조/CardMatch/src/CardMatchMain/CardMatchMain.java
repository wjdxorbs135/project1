package CardMatchMain;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CardMatchGame.CardMatchSelectDifficulty;
import CardMatchGame.CardMatchSuccess;
import CardMatchRank.CardMatchRank;

@SuppressWarnings("serial")
public class CardMatchMain extends JFrame {
	
	private static CardMatchSelectDifficulty cmsd;
	private static CardMatchMain frame;
	private static CardMatchRank cmr;
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new CardMatchMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CardMatchMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("같은 그림 찾기");
		setBounds(0, 0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblGameName = new JLabel();
		lblGameName.setIcon(new ImageIcon(CardMatchMain.class.getResource("/imgs/title.png")));
		lblGameName.setBounds(150, 100, 500, 100);
		contentPane.add(lblGameName);
		
		JButton btnGameStart = new JButton("게임 시작");
		btnGameStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cmsd = new CardMatchSelectDifficulty(frame);
				cmsd.setUndecorated(true);
				cmsd.setLocationRelativeTo(frame);
				cmsd.setVisible(true);
			}
		});
		btnGameStart.setBounds(325, 280, 150, 40);
		contentPane.add(btnGameStart);
		
		JButton btnLeaderBoard = new JButton("순위표");
		btnLeaderBoard.setBounds(325, 350, 150, 40);
		btnLeaderBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
				cmr = new CardMatchRank(frame);
				cmr.setResizable(false);
				cmr.setLocationRelativeTo(null);
				cmr.setVisible(true);
			}
		});
		contentPane.add(btnLeaderBoard);
		
		JButton btnGameExit = new JButton("게임 종료");
		btnGameExit.setBounds(325, 420, 150, 40);
		btnGameExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		contentPane.add(btnGameExit);
		
		JLabel lblBackground = new JLabel();
		lblBackground.setBounds(0, 0, 800, 600);
		ImageIcon icon = new ImageIcon(CardMatchSuccess.class.getResource("/imgs/img_mainbackground.jpg"));
		Image img = icon.getImage();
		img = img.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		lblBackground.setIcon(icon);
		contentPane.add(lblBackground);
	}
}
