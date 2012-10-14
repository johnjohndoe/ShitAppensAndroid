package info.metadude.android.shitappens;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;

public class MainActivity extends SherlockActivity implements OnClickListener {

	protected Button loginButton;
	protected Button logoutButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setTheme(R.style.Sherlock___Theme_DarkActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (Button)findViewById(R.id.loginButton);
        logoutButton = (Button)findViewById(R.id.logoutButton);
        loginButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
    }

	public void onClick(View view) {
		if (view.getId() == loginButton.getId()) {
			Toast.makeText(this, "Login Button clicked", Toast.LENGTH_LONG).show();
		}
		if (view.getId() == logoutButton.getId()) {
			Toast.makeText(this, "Logout Button clicked", Toast.LENGTH_LONG).show();
		}
	}
}
