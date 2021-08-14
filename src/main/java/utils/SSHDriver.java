package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHDriver {

	//Creating jsch variable
	private JSch mJschSSH=null;

	//Creating ssh session
	private Session mSShSession=null;

	//Creating a channel ssh
	private Channel mSSHChannel=null;

	//Now we will change In/Out
	private InputStream mSShInput=null;
	private OutputStream mSSHOutput=null;

	//Creating a new ssh connection
	public boolean SSHConnection(String strHost, int iPort, String strUserName, String strPassword, int iTimeout) throws JSchException, IOException {
		//Creating boolean variable
		//boolean blResult = false;

		this.mJschSSH = new JSch();
		
		mJschSSH.addIdentity(System.getProperty("user.dir")+ConfigReader.getValue("privatekey"));
			

		//set sftp server no check key when login
		java.util.Properties config=new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		this.mJschSSH.setConfig(config);

		//get session
		this.mSShSession=this.mJschSSH.getSession(strUserName, strHost, iPort);

		//set password for ssh
		//this.mSShSession.setPassword(strPassword);
		this.mSShSession.connect(iTimeout);

		//get channel let connect to ssh server
		this.mSSHChannel=this.mSShSession.openChannel("shell");

		//connect to channel
		this.mSSHChannel.connect();

		//After connect to channel successed u can get in/out stream
		this.mSShInput=this.mSSHChannel.getInputStream();
		this.mSSHOutput=this.mSSHChannel.getOutputStream();

		return true;
	}

	//Creating a new ssh connection
		public boolean SSHConnection() throws JSchException, IOException {
			//Creating boolean variable
			//boolean blResult = false;

			this.mJschSSH = new JSch();
			
			mJschSSH.addIdentity(System.getProperty("user.dir")+ConfigReader.getValue("privatekey"));
					
			//set sftp server no check key when login
			java.util.Properties config=new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			this.mJschSSH.setConfig(config);

			//get session
			this.mSShSession=this.mJschSSH.getSession(ConfigReader.getValue("Username"), ConfigReader.getValue("Host"), ConfigReader.getIntValue("Port"));

			//set password for ssh
			//this.mSShSession.setPassword(ConfigReader.getValue("Password"));
			this.mSShSession.connect(ConfigReader.getIntValue("Timeout"));

			//get channel let connect to ssh server
			this.mSSHChannel=this.mSShSession.openChannel("shell");

			//connect to channel
			this.mSSHChannel.connect();

			//After connect to channel successed u can get in/out stream
			this.mSShInput=this.mSSHChannel.getInputStream();
			this.mSSHOutput=this.mSSHChannel.getOutputStream();

			return true;
		}

	//creating a new function let send command to ssh server
	public boolean sendCommand(String strCommand) {
		boolean blResult =false;
		try {
			if (this.mSSHOutput != null) {
				//u should use output stream let send data
				this.mSSHOutput.write(strCommand.getBytes());
				this.mSSHOutput.flush();
				blResult = true;
			}
		}catch (Exception exp) {
			exp.printStackTrace();
		}
		return blResult;
	}

	//Creating a new function let recv data from ssh server
	public String recvData() {
		String strData="";
		try {
			if(this.mSShInput != null) {
				//inhere u  can use input  stream let check have or not data from
				//input stream
				//let recv
				int iAvailable=this.mSShInput.available();

				//receiving all data
				while(iAvailable>0) {
					//creating buffer let recv data
					byte[] btBuffer =new byte[iAvailable];

					//check byte read from input stream
					int iByteRead=this.mSShInput.read(btBuffer);
					//System.out.println("iAvailable=="+iAvailable);
					//System.out.println("iByteRead=="+iByteRead);
					//check byte read finished or not
					//iAvailable=iAvailable-iByteRead;
					strData+=new String(btBuffer);
					//String[] splitData = strData.split("#");
					//strData=splitData[1];
					//String[] splitData1 = strData.split("\\n");
					//strData=splitData1[1];
					System.out.println("strData *****"+strData);
					iAvailable=0;
				}
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return strData;
	}

	//creating close function let close all in/out stream of ssh
	public void close() {
		if(this.mSShSession != null) {
			this.mSShSession.disconnect();
		}

		if(this.mSSHChannel != null) {
			this.mSSHChannel.isClosed();
		}
		this.mJschSSH=null;
	}

}
