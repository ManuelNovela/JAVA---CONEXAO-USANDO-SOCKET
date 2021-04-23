package pkg005;

import java.rmi.*;
import java.net.*;
import java.io.*;

public class Servidor{
	public static void main(String [] args){
				String nome,situacao =" Sem Dados ";
				double nota_1=0, nota_2=0, trabalho=0, media = 0;
		
		
		
		try{
			
				ServerSocket sock= new ServerSocket(9001);
				
				while(true){
				Socket soc=sock.accept();	
				
			
				DataInputStream in= new DataInputStream(soc.getInputStream());
				nome = in.readLine();
                                nota_1 =  Double.parseDouble(in.readLine());
                                nota_2 =  Double.parseDouble(in.readLine());
                                trabalho =  Double.parseDouble(in.readLine());
                                
                                media =  (((nota_1 + nota_2) / 2 )* 0.75 + (trabalho * 0.25));
				
				
				if(media<10){
                                    situacao = "Excluido";
                                }else{
                                    if(media<14){
                                        situacao = "Admitido";
                                    }else{
                                        situacao = "Dispensado";
                                    }
                                }
				//System.out.println(""+nome+'\n');
                                
				DataOutputStream out= new DataOutputStream(soc.getOutputStream());
				out.writeBytes(nome+'\n');
				out.writeBytes(""+media+'\n');
                                out.writeBytes(""+situacao+'\n');
                                
                                
				}
			
		}catch(Exception ex){
			
			System.out.println(ex.getMessage());
		
	}
	}
}