package CardMatchGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class CardMatchRestart extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	public CardMatchRestart(CardMatchGame c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 300, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JButton btnConfirm = new JButton("확인");
		btnConfirm.setBounds(50, 100, 80, 30);
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				c.restart();
			}
		});
		contentPane.add(btnConfirm);

		JButton btnCancel = new JButton("취소");
		btnCancel.setBounds(170, 100, 80, 30);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		contentPane.add(btnCancel);

		JLabel lblDifficulty = new JLabel("다시 시작 하시겠습니까?");
		lblDifficulty.setHorizontalAlignment(SwingConstants.CENTER);
		lblDifficulty.setFont(new Font("굴림", Font.BOLD, 12));
		lblDifficulty.setBounds(50, 25, 200, 40);
		contentPane.add(lblDifficulty);
	}

}