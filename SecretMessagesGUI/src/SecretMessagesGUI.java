import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class SecretMessagesGUI extends JFrame{
	
	private JTextField txtKey;
	private JTextArea txtIn;
	private JTextArea txtOut;
	private JSlider slider;
	public String encode(String message, int keyVal) {
		String output ="";
		char key = (char) keyVal;
		for (int x =0; x < message.length(); x++) {
			char input = message.charAt(x);
			if (input >= 'A' && input <= 'Z') 
			{
				input += key;
				if (input > 'Z')
					input -= 26;
				if(input < 'A')
					input += 26;
				}
			else if (input >= 'a' && input <= 'z') {
				input += key;
				if (input > 'z')
					input -= 26;
				if(input < 'a')
					input += 26;
		   }
			else if (input >= '0'&& input <= '9') {
				input += (keyVal % 10);
				if (input > '9')
					input -= 10;
				if (input < '0')
					input += 10;
			}
			output += input;
		}
		return output;
	}



public SecretMessagesGUI(){
	setTitle("Secret Messages");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	getContentPane().setLayout(null);
	txtIn = new JTextArea();
	txtIn.setBounds(10,11,564,140);
	getContentPane().add(txtIn);
	
	txtOut = new JTextArea();
	txtOut.setBounds(10, 210, 564, 140);
	getContentPane().add(txtOut);
	
	txtKey = new JTextField();
	txtKey.setBounds(258,173,44,20);
	getContentPane().add(txtKey);
	
	JLabel lblKey = new JLabel("Key:");
	lblKey.setBounds(202, 176, 46 ,14);
	getContentPane().add(lblKey);
	
	slider = new JSlider();
	slider.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent arg0) {
			txtKey.setText("" + slider.getValue());
			String message = txtIn.getText();
			int key = slider.getValue();
			String output = encode(message,key);
			txtOut.setText(output);
			
		}
	});
	slider.setValue(3);
	slider.setPaintTicks(true);
	slider.setMajorTickSpacing(13);
	slider.setMinorTickSpacing(1);
	slider.setMaximum(26);
	slider.setMinimum(-26);
	slider.setPaintLabels(true);
	slider.setBounds(10,162,200, 45);
	getContentPane().add(slider);
	
	JButton btnEncodedecode = new JButton("Encode/Decode");
	btnEncodedecode.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				String message = txtIn.getText();
				int key = Integer.parseInt(txtKey.getText());
				String output = encode (message, key);
				txtOut.setText(output);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Please enter a whole number value for the encryption key");
				txtKey.requestFocus();
				txtKey.selectAll();
				
			}
			
	}
		
	});
	btnEncodedecode.setBounds(312,172,144,23);
	getContentPane().add(btnEncodedecode);
}

public static void main(String[] args) {
	SecretMessagesGUI theApp = new SecretMessagesGUI();
	theApp.setSize(new java.awt.Dimension(600,400));
	theApp.setVisible(true);
	theApp.setResizable(false);
	
	
}

	
	
	
	       
	
}
