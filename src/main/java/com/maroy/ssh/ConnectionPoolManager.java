package com.maroy.ssh;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

public class ConnectionPoolManager {

	private int MAX_CAPACITY = 5;
	
	private String JUMP_BOX_HOST = "__HOST_MACHINE_IP__";
	
	LinkedList<String> _connectionQueue = new LinkedList<String>();
	
	private HashMap<String, SSHConnection> connectionPool = new HashMap<String, SSHConnection>(MAX_CAPACITY);;
	
	private void addConnection(String host, SSHConnection sshConnection){

		if(_connectionQueue.size() == MAX_CAPACITY){

			String connectionKey = _connectionQueue.poll();
			connectionPool.remove(connectionKey);
			System.out.println("Removed : "+connectionKey);

			connectionPool.put(host, sshConnection);
			_connectionQueue.add(host);
			System.out.println("Added : "+sshConnection);
		}
		else{

			connectionPool.put(host, sshConnection);
			_connectionQueue.add(host);
		}
	}
	
	public SSHConnection getConnection(String hostName){
		
		SSHConnection sshConnection = null;
		if(connectionPool.containsKey(hostName)){
			_connectionQueue.remove(hostName);
			_connectionQueue.add(hostName);
			sshConnection = connectionPool.get(hostName);
		}
		else{
			
			sshConnection = this.createNewSSHConnection(hostName);
			this.addConnection(hostName, sshConnection);
		}
		return sshConnection;
	}
	public SSHConnection createNewSSHConnection(String host){
		
		SSHConnection sshConnection = new SSHConnection();
		Session session = null;
		try{
			JSch jsch=new JSch();

			//Connect to jump host
			session=jsch.getSession("__USER_NAME__", JUMP_BOX_HOST, 22);
			MyUserInfo ui=new MyUserInfo();
			ui.setPassword("__PASSWORD__");
			session.setUserInfo(ui);
			session.connect();
			
			Channel channel=session.openChannel(SSHConstants.CHANNEL_TYPE_SHELL);
			channel.setInputStream(System.in);
			channel.setOutputStream(System.out);
			channel.connect();
			
			//Connect to remote machine
			session=jsch.getSession("manishkumar_roy", host, 22);
			session.setUserInfo(ui);
			session.connect();
			
			channel=session.openChannel(SSHConstants.CHANNEL_TYPE_SHELL);
			channel.setInputStream(System.in);
			channel.setOutputStream(System.out);
			channel.connect();
			
			sshConnection.setSession(session);
			sshConnection.addChannel(SSHConstants.CHANNEL_TYPE_SHELL, channel);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		return sshConnection;
	}
	
public static class MyUserInfo implements UserInfo, UIKeyboardInteractive{
		
		String passwd;
		
		public String getPassword(){ return passwd; }
		public void setPassword(String password){
			passwd = password;
		}
		public boolean promptYesNo(String str){
			return true;
		}

		JTextField passwordField=(JTextField)new JPasswordField(20);

		public String getPassphrase(){ return null; }
		public boolean promptPassphrase(String message){ return true; }
		public boolean promptPassword(String message){
			Object[] ob={passwordField}; 
			int result=JOptionPane.showConfirmDialog(null, ob, message,
					JOptionPane.OK_CANCEL_OPTION);
			if(result==JOptionPane.OK_OPTION){
				passwd=passwordField.getText();
				return true;
			}
			else{ 
				return false; 
			}
		}
		public void showMessage(String message){
			//JOptionPane.showMessageDialog(null, message);
		}
		final GridBagConstraints gbc = 
				new GridBagConstraints(0,0,1,1,1,1,
						GridBagConstraints.NORTHWEST,
						GridBagConstraints.NONE,
						new Insets(0,0,0,0),0,0);
		//private Container panel;
		public String[] promptKeyboardInteractive(String destination,
				String name,
				String instruction,
				String[] prompt,
				boolean[] echo){

			String[] response=new String[prompt.length];
			response[0] = passwd;
			return response;
		}
	}
}
