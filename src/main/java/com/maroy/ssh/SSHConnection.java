package com.maroy.ssh;

import java.util.HashMap;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHConnection {

	private Session session;
	
	private HashMap<String, Channel> channelMap = new HashMap<String, Channel>();

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public HashMap<String, Channel> getChannelMap() {
		return channelMap;
	}

	public Channel getChannel(String channelKey) {
		
		if(this.channelMap.containsKey(channelKey))
			return this.channelMap.get(channelKey);
		
		Channel channel = null;
		try {
			channel = this.session.openChannel(channelKey);
		} catch (JSchException e) {
			e.printStackTrace();
		}
		this.addChannel(channelKey, channel);
		
		return channel;
	}
	
	public void addChannel(String channelKey, Channel channel){
		
		this.channelMap.put(channelKey, channel);
	}
}
