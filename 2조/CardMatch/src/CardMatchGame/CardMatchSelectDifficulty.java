package CardMatchGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import CardMatchMain.CardMatchMain;

@SuppressWarnings("serial")
public class CardMatchSelectDifficulty extends JFrame {

	private JPanel contentPane;
	
	private static CardMatchGame cmg;
	
	public void startEasy(CardMatchMain c) {
		c.dispose();
		dispose();
		cmg = new CardMatchGame(4, 4); 
		cmg.setVisible(true);
	}
	
	public void startHard(CardMatchMain c) {
		c.dispose();
		dispose();
		cmg = new CardMatchGame(6, 6); 
		cmg.setVisible(true);
	}
	
	public static CardMatchGame getInstance() {
		return cmg;
	}
	
	public CardMatchSelectDifficulty() {}

	public CardMatchSelectDifficulty(CardMatchMain c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 300, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JButton btnEasy = new JButton("EASY");
		btnEasy.setBounds(50, 100, 80, 30);
		btnEasy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				startEasy(c);
			}
		});
		contentPane.add(btnEasy);
		
		JButton btnHard = new JButton("HARD");
		btnHard.setBounds(170, 100, 80, 30);
		btnHard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				startHard(c);
			}
		});
		contentPane.add(btnHard);
		
		JButton btnExit = new JButton();
		btnExit.setIcon(new ImageIcon(CardMatchSelectDifficulty.class.getResource("/imgs/Exit.png")));
		btnExit.setBounds(258, 8, 32, 32);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		contentPane.add(btnExit);
		
		JLabel lblDifficulty = new JLabel("난이도");
		lblDifficulty.setHorizontalAlignment(SwingConstants.CENTER);
		lblDifficulty.setFont(new Font("굴림", Font.BOLD, 25));
		lblDifficulty.setBounds(100, 25, 100, 40);
		contentPane.add(lblDifficulty);
	}
}
