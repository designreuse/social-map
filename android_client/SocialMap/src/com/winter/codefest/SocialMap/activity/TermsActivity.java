package com.winter.codefest.SocialMap.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.winter.codefest.SocialMap.R;

/**
 * Created by bethmi on 12/8/15.
 */
public class TermsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        TextView heading = (TextView) findViewById(R.id.terms_conditions);
        heading.setText(Html.fromHtml(getString(R.string.terms_conditions)));

        TextView content = (TextView) findViewById(R.id.terms_conditions_content);
        content.setText(Html.fromHtml(getString(R.string.terms_conditions_content)));

        TextView page_footer = (TextView) findViewById(R.id.footer);
        page_footer.setText(Html.fromHtml(getString(R.string.footer)));

    }
}