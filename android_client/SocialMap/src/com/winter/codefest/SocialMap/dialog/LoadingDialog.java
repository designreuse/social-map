package com.winter.codefest.SocialMap.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;
import com.winter.codefest.SocialMap.R;


/**
 * Created by thilina on 12/10/14.
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_loading);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = 0.0f;
    }

}
