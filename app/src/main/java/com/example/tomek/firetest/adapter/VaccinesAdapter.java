package com.example.tomek.firetest.adapter;

/**
 * Created by Tomek on 2016-10-22.
 */

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tomek.firetest.R;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.example.tomek.firetest.activities.AddVaccineActivity;
import com.example.tomek.firetest.activities.ShowAnimalDetails;
import com.example.tomek.firetest.model.Vaccine;

/**
 * Created by Tomek on 17/10/16.
 */

public class VaccinesAdapter extends RecyclerView.Adapter<VaccinesAdapter.VaccinesViewHolder> {


    private ArrayList<Vaccine> vaccines;


    public VaccinesAdapter() {
        this.vaccines = new ArrayList<>();
    }

    public static class VaccinesViewHolder extends RecyclerView.ViewHolder {

        android.content.Context context;
        CardView cvVaccines;
        TextView tvVaccineType;
        TextView tvVaccineNextdate;


        public VaccinesViewHolder(View view) {
            super(view);
            context = view.getContext();
            tvVaccineType = (TextView) view.findViewById(R.id.tv_vaccine_type);
            tvVaccineNextdate = (TextView) view.findViewById(R.id.tv_vaccine_nextdate);
            cvVaccines = (CardView) view.findViewById(R.id.cv_vaccines);
        }

    }

    @Override
    public VaccinesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vaccines, parent, false);

        return new VaccinesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VaccinesViewHolder holder, int position) {
        final Vaccine vaccine = vaccines.get(position);
        holder.tvVaccineType.setText(vaccine.getType());
        holder.tvVaccineNextdate.setText(vaccine.getNextdate());


     holder.cvVaccines.setOnLongClickListener(new View.OnLongClickListener() {
         @Override
         public boolean onLongClick(View v) {

            // Toast.makeText(holder.context, "to boli", Toast.LENGTH_LONG).show();

             AlertDialog.Builder alert = new AlertDialog.Builder(holder.context);

             alert.setTitle(vaccine.getType());
             alert.setMessage("Dodać szczepienie do terminarza?");

             alert.setPositiveButton("Tak", new DialogInterface.OnClickListener() {

                 @Override
                 public void onClick(DialogInterface dialog, int which) {

                     ContentValues values = new ContentValues();
                     Calendar cal = Calendar.getInstance();

                     // Add to Android db; duration is null for nonrecurring events.
                     values.put (CalendarContract.Events.CALENDAR_ID, 1);
                     values.put (CalendarContract.Events.DTSTART, cal.getTimeInMillis() + 1000 *60*15);
                     values.put (CalendarContract.Events.DTEND, cal.getTimeInMillis()+ 1000 *60*30);
                     values.put (CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
                     values.put (CalendarContract.Events.TITLE, "SZCZEPIENIE KURWA");
                     values.put (CalendarContract.Events.DESCRIPTION, "IDZ ZASZCZEP PSA GNOJU");

                     Uri baseUri;
                     if (Build.VERSION.SDK_INT >= 8) {
                         baseUri = Uri.parse("content://com.android.calendar/events");
                     } else {
                         baseUri = Uri.parse("content://calendar/events");
                     }

                     Uri event = holder.context.getContentResolver().insert(baseUri, values);

                     values = new ContentValues();
                     values.put(CalendarContract.Reminders.EVENT_ID, Long.parseLong(event.getLastPathSegment()));
                     values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
                     values.put(CalendarContract.Reminders.MINUTES, 14);

                     Uri baseUri2;
                     if (Build.VERSION.SDK_INT >= 8) {
                         baseUri2 = Uri.parse("content://com.android.calendar/reminders");
                     } else {
                         baseUri2 = Uri.parse("content://calendar/reminders");
                     }

                     holder.context.getContentResolver().insert(baseUri2, values);

                     dialog.dismiss();

                 }
             });
             alert.setNegativeButton("Nie", new DialogInterface.OnClickListener() {

                 @Override
                 public void onClick(DialogInterface dialog, int which) {

                     dialog.dismiss();
                 }
             });


             alert.show();

             return true;
         }
     });

    }

    @Override
    public int getItemCount() {
        return vaccines.size();
    }

    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines.clear();
        this.vaccines.addAll(vaccines);
        notifyDataSetChanged();
    }
}

