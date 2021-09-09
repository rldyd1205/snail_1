package snail_1;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Swing02 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextField 				textField;
	private JTextArea 				textArea;
	private GridBagLayout 			gridBagLayout;
	private JButton 				btnSend, btnClear;
	private GridBagConstraints 		gbc_textField, gbc_textArea, gbc_btnSend, gbc_btnClear;
	private JScrollPane 			jsp;
	
	public Swing02() {
		super("Swing_Test");
		
		this.init();
		this.setLayout();
		this.addListener();
		this.showFrame();
	}

	private void init() {
		this.gridBagLayout 					= new GridBagLayout();
		this.gridBagLayout.columnWidths 	= new int[]{0, 0, 0, 0, 0};
		this.gridBagLayout.rowHeights 		= new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		this.gridBagLayout.columnWeights 	= new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.gridBagLayout.rowWeights 		= new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		this.textArea 						= new JTextArea();
		this.jsp	  						= new JScrollPane(this.textArea);
		
		this.gbc_textArea 					= new GridBagConstraints();
		this.gbc_textArea.gridheight 		= 11;
		this.gbc_textArea.gridwidth 		= 4;
		this.gbc_textArea.insets 			= new Insets(0, 0, 5, 5);
		this.gbc_textArea.fill 				= GridBagConstraints.BOTH;
		this.gbc_textArea.gridx 			= 0;
		this.gbc_textArea.gridy 			= 0;
		
		this.textField 						= new JTextField();
		this.gbc_textField 					= new GridBagConstraints();
		this.gbc_textField.gridwidth 		= 2;
		this.gbc_textField.insets 			= new Insets(0, 0, 0, 5);
		this.gbc_textField.fill 			= GridBagConstraints.HORIZONTAL;
		this.gbc_textField.gridx 			= 0;
		this.gbc_textField.gridy 			= 11;
		
		this.btnSend 						= new JButton("보내기");
		this.gbc_btnSend 					= new GridBagConstraints();
		this.gbc_btnSend.insets 			= new Insets(0, 0, 0, 5);
		this.gbc_btnSend.gridx 				= 2;
		this.gbc_btnSend.gridy 				= 11;
		
		this.btnClear 						= new JButton("지우기");
		this.gbc_btnClear 					= new GridBagConstraints();
		this.gbc_btnClear.gridx 			= 3;
		this.gbc_btnClear.gridy 			= 11;
	}

	private void setLayout() {
		this.setLayout(gridBagLayout);
		this.textField.setColumns(10);
		this.textArea.setEditable(false);
		
		this.add(jsp, gbc_textArea);
		this.add(textField, gbc_textField);
		this.add(btnSend, gbc_btnSend);
		this.add(btnClear, gbc_btnClear);

	}

	private void addListener() {
		textField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				columns();
			}
		});
		
		btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				columns();
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
	}
	
	public void columns() {
		String str = textField.getText();
		int a = Integer.parseInt(str);
		if (!str.equals("")) {
			int[][] snail = new int[a][a];
			
			int print  = a;  // 행이 움직일때 감소하는 변수
			int k      = 1;  // 배열의 값을 담기위한 변수
			int right  = -1; // 좌 우로 움직이는 변수
			int bottom = 0;  // 위 아래로 움직이는 변수
			int top    = 1;  // 스위치 변수
			
			// right를 좌,우로 움직이며 배열에 값을 담는 2중 for문
			for (int i = a; i > 0; i--) {
				for (int j = 0; j < print; j++) {
					right += top;
					snail[bottom][right] = k;
					
					k++;
				}
				print--;
				
				// bottom을 위,아래로 움직이며 배열에 값을 담는 for문
				for (int j = 0; j < print; j++) {
					bottom += top;
					snail[bottom][right] = k;
					
					k++;
				}
				// for문이 끝난 후 스위치 코드로 상하좌우 변경
				top = top * (-1);
			}
			
			// 출력문을 작성하기 위한 2중 for문
			for (int i = 0; i < snail.length; i++) {
				for (int j = 0; j < snail.length; j++) {
					textArea.append(snail[i][j] + "\t");
				}
				textArea.append("\n");
			}
			textField.setText("");
		}
	}
	
	private void showFrame() {
		this.setSize(500,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Swing02();
	} // main
}
