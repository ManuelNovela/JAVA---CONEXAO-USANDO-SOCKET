package pkg005;
 import java.rmi.*;
 import java.net.*;
 import java.io.*;
 import java.util.Scanner;
 import java.awt.Container;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.*;
 
 public class Cliente extends JFrame implements ActionListener{
	Container contentor=getContentPane();
	
	
	//Variaveis
	
	String nome,nota1,snota,trabalho,situacao,media;
	
	JLabel lbNome= new JLabel("Nome");
	JLabel lbNota1= new JLabel("Primeira nota");
	JLabel lbSnota= new JLabel("Segunda nota");
	JLabel lbTrabalho= new JLabel(" Nota do Trabalho");
	JLabel lbMedia= new JLabel("Media");
	JLabel lbSituacao= new JLabel("Situacao do Aluno");
	
	
	JTextField txtNome = new JTextField();
	JTextField txtNota1= new JTextField();
	JTextField txtSnota= new JTextField();
	JTextField txtTrabalho= new JTextField();
	JTextField txtMedia= new JTextField();
	JTextField txtSituacao= new JTextField();
	
	
	JButton btSend=new JButton("Enviar");
	
	
	
		public Cliente() {
			setaLayout();
			setaComponentes();
			alocaComponentes();
			addActionEvent();
		
		}
		
		public void setaLayout(){
			contentor.setLayout(null);
			
		}
		
		
		public void setaComponentes(){
			lbNome.setBounds(50,50,100,30);
			lbNota1.setBounds(50,100,100,30);
			lbSnota.setBounds(50,150,100,30);
			lbTrabalho.setBounds(40,200,100,30);
			btSend.setBounds(50,250,100,30);
			lbMedia.setBounds(200,250,170,30);
			lbSituacao.setBounds(190,300,150,30);
			
			
			txtNome.setBounds(150,50,300,30);
			txtNota1.setBounds(150,100,300,30);
			txtSnota.setBounds(150,150,300,30);
			txtTrabalho.setBounds(150,200,300,30);
			txtMedia.setBounds(300,250,100,30);
			txtSituacao.setBounds(300,300,100,30);
			
			
			
		
		}
		
			public void alocaComponentes(){
			contentor.add(lbNome);
		    contentor.add(lbNota1);
			contentor.add(lbSnota);
			contentor.add(lbTrabalho);
			contentor.add(lbMedia);
			contentor.add(lbSituacao);
			
			contentor.add(txtNome);
			contentor.add(txtNota1);
			contentor.add(txtSnota);
			contentor.add(txtTrabalho);
			contentor.add(txtMedia);
			contentor.add(txtSituacao);
			contentor.add(btSend);
		}
		public void addActionEvent(){
			btSend.addActionListener(this);
		}
		//Metodo para Limpar os Campos
	public void limpaCampos(){
		txtSituacao.setText(null);
		txtTrabalho.setText(null);
		txtNome.setText(null);
		txtNota1.setText(null);
       txtSnota.setText(null);
		
	}
	
	
		public void actionPerformed(ActionEvent ev){
			if(ev.getSource()==btSend) {
				nome=txtNome.getText();
				nota1=txtNota1.getText();
				snota=txtSnota.getText();
				trabalho=txtTrabalho.getText();
			try{
				
				Socket soc= new Socket("127.0.0.1",9001);
			
				DataOutputStream out= new DataOutputStream(soc.getOutputStream());
				out.writeBytes(nome+'\n');
				out.writeBytes(nota1+'\n');
				out.writeBytes(snota+'\n');
				out.writeBytes(trabalho+'\n');
              
              
				JOptionPane.showMessageDialog(null,"Dados Enviados");
				limpaCampos();
				
				DataInputStream in= new DataInputStream(soc.getInputStream());
                                txtNome.setText(in.readLine());
				txtMedia.setText(in.readLine());
				txtSituacao.setText(in.readLine());
				
				//System.out.print(in.readLine()+" | "+in.readLine()+" | "+in.readLine());
	
				
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
			}
		
		}
		
		public static void main(String [] args){
		
		Cliente cliente= new Cliente();
		cliente.setTitle("Client");
		cliente.setSize(600, 400);
		cliente.setVisible(true);
		cliente.setResizable(false);
		cliente.setDefaultCloseOperation(cliente.EXIT_ON_CLOSE);
       }
       }