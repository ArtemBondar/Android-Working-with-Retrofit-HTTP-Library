package com.bondar.android_working_with_retrofit_http_library.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bondar.android_working_with_retrofit_http_library.R;
import com.bondar.android_working_with_retrofit_http_library.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private int rowLayout;
    private Context context;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout movieLayout;
        TextView movieTitle;
        TextView movieReleaseDate;
        TextView movieDescription;
        TextView movieRating;
        ImageView moviePoster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieLayout = (LinearLayout) itemView.findViewById(R.id.movies_layout);
            movieTitle = (TextView) itemView.findViewById(R.id.title);
            movieReleaseDate = (TextView) itemView.findViewById(R.id.subtitle);
            movieDescription = (TextView) itemView.findViewById(R.id.description);
            movieRating = (TextView) itemView.findViewById(R.id.rating);
            moviePoster = (ImageView) itemView.findViewById(R.id.poster);
        }

    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }


    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.movieReleaseDate.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.movieRating.setText(movies.get(position).getVoteAverage().toString());
        Uri uri = Uri.parse("http://image.tmdb.org/t/p/w500" + movies.get(position).getPosterPath());
        Picasso.with(context).load(uri).into(holder.moviePoster);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
