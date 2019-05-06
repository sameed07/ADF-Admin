package Adapters;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sameedshah.adfapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import Model.ModelClassCalanderAdapter;


public class CalenderClassAdapter extends RecyclerView.Adapter<CalenderClassAdapter.ViewHolderClassReminder> {

    Context context;
    String[] colorArray = {"#3f51b5", "#007ee5", "#ff3300", "#0097A7", "#34495e", "#3498db", "#8e44ad", "#699f55", "#2979FF", "#E91E63", "#f44336", "#6D4C41", "#78909C", "#009688", "#1A237E", "#DD2C00", "#d50000", "#1A237E"};

    ArrayList<ModelClassCalanderAdapter> modelClassAllReminders;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-m-yyyy", Locale.getDefault());

    public CalenderClassAdapter(Context context, ArrayList<ModelClassCalanderAdapter> modelClassAllReminders) {
        this.context = context;
        this.modelClassAllReminders = modelClassAllReminders;
    }
    @NonNull
    @Override
    public ViewHolderClassReminder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.res_new_notes_layout,
                viewGroup, false);
        return new ViewHolderClassReminder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderClassReminder holder, final int position) {

        final ModelClassCalanderAdapter modelClass = modelClassAllReminders.get(position);


        holder.title1.setText(modelClass.getCalanderTitle());
        holder.subTitle1.setText(modelClass.getCalanderSubTitle());


        holder.dayOfMonth.setText(modelClass.getDayofmonth());
        holder.month.setText(modelClass.getMonth());
        holder.timeOfEvent1.setText(modelClass.getTime());
        Random random = new Random();
        int index = random.nextInt(colorArray.length);
        holder.colorLine.setBackgroundColor(Color.parseColor(colorArray[index]));
    
        holder.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                

                final Context context = view.getContext();


                final BottomSheetDialog dialog;
//                View view2 = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_fragment_layout, null);
                View sheetReminder = LayoutInflater.from(context).inflate(R.layout.sheet_reminder, null);

                TextView sheetTitle, sheetSubtitle, sheetDate, sheetTime, sheetNotification;
                LinearLayout sheetDeleteBtn, sheetEditBtn;

                sheetTitle = (TextView) sheetReminder.findViewById(R.id.sheet_title);
                sheetSubtitle = (TextView) sheetReminder.findViewById(R.id.sheet_subtitle);
                sheetDate = (TextView) sheetReminder.findViewById(R.id.sheet_date);
                sheetTime = (TextView) sheetReminder.findViewById(R.id.sheet_time);


                sheetDeleteBtn = (LinearLayout) sheetReminder.findViewById(R.id.sheet_delete_btn);
//
//                sheetEditBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(context, "Edited", Toast.LENGTH_SHORT).show();
//                    }
//                });

                sheetDeleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();


                    }
                });


                sheetTitle.setText(holder.title1.getText());
                sheetSubtitle.setText(holder.subTitle1.getText());
                sheetDate.setText(modelClass.getDate() + "");
                sheetTime.setText(modelClass.getTime() + "");


//              TextView bottomSheetTitle, bottomSheetSubtitle;
//                bottomSheetTitle = (TextView) view2.findViewById(R.id.bottom_sheet_title);
//                bottomSheetSubtitle = (TextView) view2.findViewById(R.id.bottom_sheet_subtitle);
//
//                bottomSheetTitle.setText(holder.title.getText());
//                bottomSheetSubtitle.setText(holder.subTitle.getText());

                dialog = new BottomSheetDialog(context);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(sheetReminder);
                dialog.show();


            }
        });



    }

    @Override
    public int getItemCount() {
        return modelClassAllReminders.size();
    }

    public class ViewHolderClassReminder extends RecyclerView.ViewHolder {
        TextView title1;
        TextView subTitle1;
        TextView dayOfMonth;
        TextView timeOfEvent1;
        TextView month;
        CardView card1;
        View colorLine;


        public ViewHolderClassReminder(@NonNull View itemView) {
            super(itemView);
           title1 =  itemView.findViewById(R.id.rec_title);
           subTitle1 =  itemView.findViewById(R.id.rec_subTile);
            dayOfMonth = itemView.findViewById(R.id.rec_day_of_the_month_large);
            month = itemView.findViewById(R.id.rec_day_of_the_week_small);

            timeOfEvent1 = itemView.findViewById(R.id.recTimeOfEvent);
            colorLine = itemView.findViewById(R.id.recColorLine);
            card1 =  itemView.findViewById(R.id.rec_card);
        }
    }
}
