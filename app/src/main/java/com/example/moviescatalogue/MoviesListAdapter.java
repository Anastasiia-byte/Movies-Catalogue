package com.example.moviescatalogue;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviescatalogue.databases.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MyViewHolder> {

    private Context context;
    private List<Movie> movieList;
    private RecyclerViewClickListener listener;

    public MoviesListAdapter(Context context, RecyclerViewClickListener listener){
        this.context = context;
        this.listener = listener;
    }

    public MoviesListAdapter(Context context){
        this.context = context;
        this.listener = null;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMovieList(List<Movie> movieList) {
        this.movieList = new ArrayList<>(movieList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviesListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesListAdapter.MyViewHolder holder, int position) {
        holder.name.setText(this.movieList.get(position).name);
        holder.genre.setText(this.movieList.get(position).genre);
        holder.descr.setText(this.movieList.get(position).description);
        holder.rate.setText(this.movieList.get(position).rate + "/5");
    }

    @Override
    public int getItemCount() {
        if(movieList == null) return 0;
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name;
        private TextView genre;
        private TextView descr;
        private TextView rate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemName);
            genre = itemView.findViewById(R.id.itemGenre);
            descr = itemView.findViewById(R.id.itemDescription);
            rate = itemView.findViewById(R.id.itemRate);
            if(listener != null)
                itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(listener != null)
                listener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}
