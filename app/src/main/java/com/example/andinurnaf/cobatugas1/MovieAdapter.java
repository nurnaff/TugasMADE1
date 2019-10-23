package com.example.andinurnaf.cobatugas1;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {
    private ArrayList<Movie> mData = new ArrayList<>();
    private Context context;
    private LayoutInflater mLinf;
    private String description;

    public MovieAdapter (Context context){
        this.context = context;
        mLinf = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData (ArrayList<Movie>items){
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem (final Movie list){
        mData.add(list);
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        // initialize viewholder
        ViewHolder holder;
        Movie movie = mData.get(position);
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mLinf.inflate(R.layout.list_movie, null);
            holder.movieTitle = (TextView)convertView.findViewById(R.id.movie_name);
            holder.movieOverview = (TextView)convertView.findViewById(R.id.movie_overview);
            holder.movieDate = (TextView)convertView.findViewById(R.id.movie_date);
            holder.moviePath = (ImageView) convertView.findViewById(R.id.movie_path);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.movieTitle.setText(movie.getTitle());

        String overview = movie.getOverview();
        if (TextUtils.isEmpty(overview)){ description = "Not Found"; }
        else { description = overview; }
        holder.movieOverview.setText(description);

        holder.movieDate.setText(movie.getDate());

        // Using Picasso to download and display the image
//        Picasso.with(context).load(movie.getUrl())
//                .placeholder(context.getResources().getDrawable(R.drawable.ic_link_black_24dp))
//                .error(context.getResources().getDrawable(R.drawable.ic_link_black_24dp))
//                .into(holder.moviePath);
        Glide.with(context) .load(movie.url) .into(holder.moviePath);
//        Glide.with(context) .load("https://image.tmdb.org/t/p/w500/sFC1ElvoKGdHJIWRpNB3xWJ9lJA.jpg") .into(holder.moviePath);
        return convertView;
    }

    private  static class ViewHolder {
        TextView movieTitle, movieOverview, movieDate;
        ImageView moviePath;
    }
}