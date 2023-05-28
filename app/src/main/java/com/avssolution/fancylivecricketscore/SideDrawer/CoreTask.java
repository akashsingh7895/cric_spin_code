package com.avssolution.fancylivecricketscore.SideDrawer;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.avssolution.fancylivecricketscore.R;


public class CoreTask {


    public void navTask(Context activity, int position){

        if(position==0){
            try{
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id="+activity.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 activity.startActivity(intent);
            }
            catch (ActivityNotFoundException e){
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id="+activity.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
            }

        }
        if (position==1){
            // share
            try {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");
                intent.putExtra("android.intent.extra.TEXT", activity.getString(R.string.str_share_app_pre_text)+"\nhttp://play.google.com/store/apps/details?id=" + activity.getPackageName());
                activity.startActivity(Intent.createChooser(intent, ""+activity.getString(R.string.str_share_with))
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            } catch (Exception unused) {
                Toast.makeText(activity, ""+activity.getString(R.string.str_unable_to_send_try_again), Toast.LENGTH_LONG).show();
            }


        }

        if (position==2){
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"alien01plays@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
          // i.putExtra(Intent.EXTRA_TEXT   , "body of email");
            try {
                activity.startActivity(Intent.createChooser(i, "Send mail...")
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            } catch (ActivityNotFoundException ex) {
                Toast.makeText(activity, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }

        }

    }
}

