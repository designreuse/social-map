package com.winter.codefest.SocialMap.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.winter.codefest.SocialMap.R;


/**
 * Created by bethmi on 12/8/15.
 */
public class HelpActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TextView help_page = (TextView) findViewById(R.id.help_page_intro);
        help_page.setText(Html.fromHtml(getString(R.string.help_page_intro)));

        TextView page_footer = (TextView) findViewById(R.id.footer);
        page_footer.setText(Html.fromHtml(getString(R.string.footer)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.help_menu, menu);
         return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.terms:
                showTerms();
                return true;
            case R.id.privacy:
                showPrivacyPolicy();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showTerms() {
        Intent intent = new Intent(this, TermsActivity.class);
        startActivity(intent);
     }

    private void showPrivacyPolicy() {
        Intent intent = new Intent(this, PrivacyPolicyActivity.class);
        startActivity(intent);
      }

}
