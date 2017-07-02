package com.maroy.ssh;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;


public class DevToolSSHOperations {

	@Autowired
	private ConnectionPoolManager connections;
	
	private StringBuilder outputBuffer;
	
	public StringBuilder getOutputBuffer(){
		return outputBuffer;
	}
	
	public void sendCommand(String command, String hostname)
	  {
	     try
	     {
	    	 
	    	 Channel channel= connections.getConnection(hostname).getChannel(SSHConstants.CHANNEL_TYPE_EXEC);
	    	 
	    	 
	    	 if(channel.isClosed() || !channel.isConnected()){
	    		 ((ChannelExec)channel).setCommand(command);

	    		 channel.setInputStream(null);

	    		 ((ChannelExec)channel).setErrStream(System.err);

	    		 channel.connect();
	    		 outputBuffer = new StringBuilder();
	    	 }
	         
	    	 InputStream in=channel.getInputStream();
	    	 
	         try{Thread.sleep(1000);}catch(Exception ee){}
	         
	        
	         byte[] tmp=new byte[1024];
	           while(in.available()>0){
	             int i=in.read(tmp, 0, 1024);
	             if(i<0)break;
	             
	             outputBuffer.append(new String(tmp, 0, i));
	             
	           }
	           try{Thread.sleep(1000);}catch(Exception ee){}
	     }
	     catch(IOException ioX)
	     {
	    	 ioX.printStackTrace();
	     }
	     catch(JSchException jschX)
	     {
	    	 jschX.printStackTrace();
	     }
	     finally {

				

			}

	  }
}
