package com.example.tmdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmdb.R;
import com.example.tmdb.model.Movie;
import com.example.tmdb.view.MovieActivity;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    Context context;
    ArrayList<Movie> movieArrayList;

    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.movieTitle.setText(movieArrayList.get(position).getOriginalTitle());
        holder.movieRating.setText(Double.toString(movieArrayList.get(position).getVoteAverage()));
        String imageUrl="https://image.tmdb.org/t/p/w500/"+movieArrayList.get(position).getPosterPath();

        Log.e("MyApp", "onBindViewHolder: "+imageUrl );
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.place_holder)
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView movieTitle;
        ImageView movieImage;
        TextView movieRating;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage=itemView.findViewById(R.id.ivMovie);
            movieTitle=itemView.findViewById(R.id.tvTitle);
            movieRating=itemView.findViewById(R.id.tvRating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION) {
                        Movie selectedMovie = movieArrayList.get(position);
                        Intent iNext=new Intent(context, MovieActivity.class);
                        iNext.putExtra("movie",selectedMovie);
                        context.startActivity(iNext);
                    }

                }
            });
        }
    }
}
