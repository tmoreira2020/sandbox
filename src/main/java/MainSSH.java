import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Hashtable;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class MainSSH {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String server = "";
		String username = "";
		String password = "";

		JSch jsch = new JSch();
		jsch.setKnownHosts(server);
		Session session = jsch.getSession(username, server, 22);

		session.setPassword(password);
		Hashtable<String, String> properties = new Hashtable<String, String>();
		properties.put("HashKnownHosts", "yes");
		session.setConfig(properties);
		session.connect();

		// exec 'scp -f rfile' remotely
		String command = "scp -f .bash_history";
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command);

		// get I/O streams for remote scp
		OutputStream out = channel.getOutputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				channel.getInputStream()));

		channel.connect();

		String line = null;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
	}

}
