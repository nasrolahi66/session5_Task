package com.example.session5_task;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.session5_task.MovieClass.Search;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
List<Search>  movieList;
    public  RecyclerViewAdapter(List<Search> movies){
    movieList=movies;
}

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item,viewGroup,false);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view);
        return  recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {

            txtTitle.setText("Title : " + movieList.get(i).getTitle());
            txtType.setText("Type: " + movieList.get(i).getType());
            txtYear.setText("Year: " + movieList.get(i).getYear());
            txtImdbId.setText("IMDB ID: " + movieList.get(i).getImdbID());
            Picasso.get().load(movieList.get(i).getPoster()).into(imgMovie);



    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
ImageView imgMovie;
    TextView txtTitle;
    TextView txtYear;
    TextView txtType;
    TextView txtImdbId;

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtYear=itemView.findViewById(R.id.txtYear);
            txtType=itemView.findViewById(R.id.txtType);
            imgMovie=itemView.findViewById(R.id.imgMovie);
            txtImdbId=itemView.findViewById(R.id.txtImdbId);
        }
    }

}
