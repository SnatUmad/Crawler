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

public class ParserGUI {

	private JFrame frame;
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
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setLayout(null);

		txtHttp = new JTextField();
		txtHttp.setText("http://twitch.tv");
		txtHttp.setBounds(104, 539, 289, 20);
		frame.getContentPane().add(txtHttp);
		txtHttp.setColumns(10);
		

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 383, 517);
		frame.getContentPane().add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setText("");

		JButton btnUrl = new JButton("URL");
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
		frame.getContentPane().add(btnUrl);
		
		frame.setBounds(100, 100, 420, 609);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
