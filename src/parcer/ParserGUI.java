package parcer;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;

public class ParserGUI {

	private JFrame frmLoic;
	private JTextField txtHttp;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParserGUI window = new ParserGUI();
					window.frmLoic.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ParserGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoic = new JFrame();
		frmLoic.setTitle("LOIC");
		frmLoic.getContentPane().setEnabled(false);
		frmLoic.getContentPane().setLayout(null);

		txtHttp = new JTextField();
		txtHttp.setText("http://jsoup.org");
		txtHttp.setBounds(104, 539, 289, 20);
		frmLoic.getContentPane().add(txtHttp);
		txtHttp.setColumns(10);
		

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 383, 517);
		frmLoic.getContentPane().add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 9));
		textArea.setEditable(false);
		textArea.setForeground(new Color(0, 255, 0));
		textArea.setBackground(Color.BLACK);
		scrollPane.setViewportView(textArea);
		textArea.setText("");

		JButton btnUrl = new JButton("hax");
		btnUrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Document doc = null;
				try {
					doc = Jsoup.connect(txtHttp.getText()).get();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Elements links = doc.select("a[href]");
				String saved_links = "";
				for(Element link : links) {
					saved_links += link.text()+"\n"+link.attr("abs:href")+ link.attr("rel")+"\n\n";
				}
				textArea.setText(saved_links);

			}});
		btnUrl.setBounds(10, 538, 89, 23);
		frmLoic.getContentPane().add(btnUrl);
		
		frmLoic.setBounds(100, 100, 420, 609);
		frmLoic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
