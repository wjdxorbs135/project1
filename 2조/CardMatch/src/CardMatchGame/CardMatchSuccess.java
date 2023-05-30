package CardMatchGame;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import CardMatchDAO.CardMatchDAO;

public class CardMatchSuccess extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private CardMatchDAO cmd;

	public CardMatchSuccess(boolean easy, int minutes, int second) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String time = "기록 : " + minutes + "분 " + second + "초";
		JLabel lblNewLabel = new JLabel(time);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(206, 96, 97, 15);
		contentPane.add(lblNewLabel);

		JButton btnSave = new JButton("저장하기");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					cmd = new CardMatchDAO();
				} catch (Exception error) {
					error.printStackTrace();
				}
				String name = JOptionPane.showInputDialog("이름을 입력하세요");
				if (name != null) {					
					if (easy)
						cmd.insert(name, minutes, second);
					else
						cmd.insert2(name, minutes, second);
					dispose();
				}
			}
		});

		btnSave.setBounds(128, 201, 97, 23);
		contentPane.add(btnSave);

		JButton btnClose = new JButton("닫기");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(275, 201, 97, 23);
		contentPane.add(btnClose);

		JLabel lblTime = new JLabel("축하합니다.");
		lblTime.setFont(new Font("굴림", Font.BOLD, 13));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(159, 68, 188, 23);
		contentPane.add(lblTime);

		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 450, 300);
		ImageIcon icon = new ImageIcon(CardMatchSuccess.class.getResource("/imgs/omg_smile.jpg"));
		Image img = icon.getImage();
		img = img.getScaledInstance(450, 350, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		lblBackground.setIcon(icon);
		contentPane.add(lblBackground);
	}
}
