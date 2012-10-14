package info.metadude.android.shitappens;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockActivity;

public class MainActivity extends SherlockActivity implements OnClickListener, ActionBar.TabListener {

	protected Button mLoginButton;
	protected Button mLogoutButton;
	protected ActionBar.Tab mTabScan;
	protected ActionBar.Tab mTabToilets;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setTheme(R.style.Sherlock___Theme_DarkActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mTabScan = addTab(getString(R.string.tab_scan_text));
        mTabToilets = addTab(getString(R.string.tab_toilets_text));

        mLoginButton = (Button)findViewById(R.id.loginButton);
        mLogoutButton = (Button)findViewById(R.id.logoutButton);
        mLoginButton.setOnClickListener(this);
        mLogoutButton.setOnClickListener(this);
    }

	public void onClick(View view) {
		if (view.getId() == mLoginButton.getId()) {
			Toast.makeText(this, "Login Button clicked", Toast.LENGTH_LONG).show();
		}
		if (view.getId() == mLogoutButton.getId()) {
			Toast.makeText(this, "Logout Button clicked", Toast.LENGTH_LONG).show();
		}
	}

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction transaction) {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction transaction) {
		Toast.makeText(this, tab.getText() + " selected.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction transaction) {
    }

    private ActionBar.Tab addTab(String tabText) {
        ActionBar.Tab tabToilets = getSupportActionBar().newTab();
        tabToilets.setText(tabText);
        tabToilets.setTabListener(this);
        getSupportActionBar().addTab(tabToilets);
        return tabToilets;
    }

}
