package info.metadude.android.shitappens;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	protected Button loginButton;
	protected Button logoutButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (Button)findViewById(R.id.loginButton);
        logoutButton = (Button)findViewById(R.id.logoutButton);
        loginButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
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
