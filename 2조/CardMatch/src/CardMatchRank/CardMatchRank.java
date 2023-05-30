package CardMatchRank;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import CardMatchDBConn.CardMatchDBConn;
import CardMatchMain.CardMatchMain;

@SuppressWarnings("serial")
public class  CardMatchRank extends JFrame{

	private JTable table;    
	private JScrollPane scrollPane;    // 테이블 스크롤바 자동으로 생성되게 하기

	private String colNames[] = {"순위","이름","시간"};  // 테이블 컬럼 값들
	private DefaultTableModel model = new DefaultTableModel(colNames, 0); //  테이블 데이터 모델 객체 생성
	
	private JLabel label;
	
	private JButton btnShowEasy;
	private JButton btnShowHard;

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;   // 리턴받아 사용할 객체 생성 ( select에서 보여줄 때 필요 )

	public  CardMatchRank(CardMatchMain frame) {
		
		setBounds(0, 0, 800, 600);
		getContentPane().setLayout(null);        // 레이아웃 배치관리자 삭제
		
		table = new JTable(model);  // 테이블에 모델객체 삽입
		
		table.setRowHeight(31); //셀 간격
		
		scrollPane = new JScrollPane(table);            // 테이블에 스크롤 생기게 하기
		scrollPane.setLocation(107, 171);
		scrollPane.setSize(616, 333);
		getContentPane().add(scrollPane);
		
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel ts = table.getColumnModel();
		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < ts.getColumnCount(); i++) {
			ts.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		
		initialize(frame);
	}
	
	private void selectSQL(String sql) {
		try {
			con = new CardMatchDBConn().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성
			model.setRowCount(0);         // 전체 테이블 화면을 지워줌
			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
				model.addRow(new Object[] {rs.getString("rownum"),rs.getString("name"), rs.getString("time") });
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close(); // 객체 생성한 반대 순으로 사용한 객체는 닫아준다.
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}
                   //초기화
	private void initialize(CardMatchMain frame) {

		label = new JLabel("순위표");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.BOLD, 33));
		label.setBounds(349, 32, 120, 54);
		getContentPane().add(label);
		

		btnShowEasy = new JButton("Easy");
		btnShowEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql = "select rownum, name, minutes||'분 '||second||'초' as time from (select * from rank order by minutes,second asc) where rownum <= 10";
				selectSQL(sql);
				btnShowEasy.setEnabled(false);
				btnShowHard.setEnabled(true);
			}
		});
		btnShowEasy.setBounds(517, 114, 97, 23);
		getContentPane().add(btnShowEasy);

		btnShowHard = new JButton("Hard");
		btnShowHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql =  "select rownum, name, minutes||'분 '||second||'초' as time from (select * from hrank order by minutes,second asc) where rownum <= 10";
				selectSQL(sql);
				btnShowEasy.setEnabled(true);
				btnShowHard.setEnabled(false);
			}
		});
		btnShowHard.setBounds(626, 114, 97, 23);
		getContentPane().add(btnShowHard);

		JButton btnReturnMain = new JButton("메인 화면");
		btnReturnMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				frame.setVisible(true);
			}
		});
		btnReturnMain.setBounds(107, 114, 97, 23);
		getContentPane().add(btnReturnMain);
		
		String sql = "select rownum, name, minutes||'분 '||second||'초' as time from (select * from rank order by minutes,second asc) where rownum <= 10";
		btnShowEasy.setEnabled(false);
		selectSQL(sql);
	}

}