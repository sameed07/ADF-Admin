package Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.sameedshah.adfapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import Model.Notes;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    ArrayList<Notes> mList;
    Context context;

    public NotesAdapter(Context context, ArrayList<Notes> mList){

        this.context  = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.notes_adapter,viewGroup,false);

        return new NotesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Notes data = mList.get(i);
        viewHolder.notes_text.setText(data.Notes);
        Picasso.get().load(data.Image).into(viewHolder.notes_image);


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView notes_image;
        //ExpandableTextView notes_text;
        TextView notes_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            notes_image = itemView.findViewById(R.id.notes_image);
            notes_text = itemView.findViewById(R.id.txt_note);
        }
    }
}
