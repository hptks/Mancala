package com.armadillo.team.sunkagame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

/**
 * Created by Hristo Tanev.
 */
public class NamesDialogFragment extends Dialog implements android.view.View.OnClickListener {
    public static boolean shown=false;
    EditText p1,p2;

    public NamesDialogFragment(Activity a) {
        super(a);
    }

    public void onCreate(Bundle savedInstanceStates) {
        super.onCreate(savedInstanceStates);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        Button sb=(Button)findViewById(R.id.submit_button);
        sb.setOnClickListener(this);
        p1=(EditText)findViewById(R.id.player1name_dialog);
        p2=(EditText)findViewById(R.id.player2name_dialog);
    }

    @Override
    public void onClick(View v) {
        dismiss();
        Context c=getContext();
        Intent i=new Intent(c,SunkaGameActivity.class);
        ArrayList<String> playersNames=new ArrayList<String>();
        playersNames.add(p1.getText().toString());
        playersNames.add(p2.getText().toString());
        i.putStringArrayListExtra("playersNames", playersNames);
        c.startActivity(i);
    }
}
