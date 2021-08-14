package utils;

import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

//class using for sftp function
public class SFTPDriver {
	//variable let using for sftp channel
	private JSch mJschSession = null;
	private Session mSSHSession = null;
	
	//SFTP channel
	private ChannelSftp mChannelSftp = null;
	
	//connect function let connect to sftp server
	//in here or any protocol please remember some of the other variable very important
	//timeout but in this demo i only demo with normal function
	//still having many function u can add for sftp: create, delete, set right... for sftp server
	public SFTPDriver(String strHostAddress, int iPort, String strUserName, String strPassword) throws JSchException {
		//creating a new jsch session
			this.mJschSession = new JSch();
			
			//Set sftp server no check key when login
			java.util.Properties config=new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			this.mJschSession.setConfig(config);
			
			//creating session with user, host port
			this.mSSHSession = mJschSession.getSession(strUserName, strHostAddress, iPort);
			
			//set password
			this.mSSHSession.setPassword(strPassword);
			
			//connect to host
			this.mSSHSession.connect();
			
			//open sftp connection
			this.mChannelSftp= (ChannelSftp) this.mSSHSession.openChannel("sftp");
			
			//connect to sftp session
			this.mChannelSftp.connect();
		}
	
	
	//list file on sftp server
	public Vector<LsEntry> listFile(String strPath){
		Vector<LsEntry> vtFile = null;
		try {
			vtFile = this.mChannelSftp.ls(strPath);
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		return vtFile;
	}
	
	//download file
	public boolean downloadFile(String strSftpFile, String strLocalFile) {
		boolean blResult = false;
		
		try {
			this.mChannelSftp.get(strSftpFile, strLocalFile);
			blResult=true;
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return blResult;
	}
	
	//upload file
	public boolean uploadFile(String strSftpFile, String strLocalFile) {
		boolean blResult = false;
		
		try {
			this.mChannelSftp.put(strSftpFile, strLocalFile);
			blResult=true;
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return blResult;
	}
	
	//close session
	public void close() {
		try {
			this.mChannelSftp.disconnect();
		}catch (Exception exp) {
			exp.printStackTrace();
		}
	}

}
