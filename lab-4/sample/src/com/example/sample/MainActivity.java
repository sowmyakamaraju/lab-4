package com.example.sample;

import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	public static void connectSSH()
	{
		// Initialize a SSHExec instance without referring any object. 
		SSHExec ssh = null;
		// Wrap the whole execution jobs into try-catch block   
		try {
		    // Initialize a ConnBean object, parameter list is ip, username, password
		    ConnBean cb = new ConnBean("134.193.136.147", "group10","group10");
		    // Put the ConnBean instance as parameter for SSHExec static method getInstance(ConnBean) to retrieve a real SSHExec instance
		    ssh = SSHExec.getInstance(cb);              
		
		    // Connect to server
		    ssh.connect();
		    // Upload sshxcute_test.sh to /home/tsadmin on remote system
		    ssh.uploadSingleDataToServer("Homegroup\\sowmya kamaraju\\Downloads\\sensorsowmya.txt", "/home/group10/");
		    
	
		} 
		catch (TaskExecFailException e) 
		{
		    System.out.println(e.getMessage());
		    e.printStackTrace();
		} 
		catch (Exception e) 
		{
		    System.out.println(e.getMessage());
		    e.printStackTrace();
		} 
		finally 
		{
		    ssh.disconnect();   
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startService(new Intent(this,ConnectionService.class));
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		/*Intent i = new Intent(this,ConnectionService.class);
		startService(i);*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
