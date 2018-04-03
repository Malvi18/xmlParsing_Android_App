package c.example.xmlparsingsax;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Dell on 08-02-2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private ArrayList<News> newsArrayLists;
    private Context context;

    public NewsAdapter(ArrayList<News> newsArrayLists, Context context) {
        this.newsArrayLists = newsArrayLists;
        this.context = context;
    }


    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.MyViewHolder holder, int position) {
       final News news=newsArrayLists.get(position);
       holder.tvTitle.setText(news.getTitle());
       holder.tvPubDate.setText(news.getPubDate());
       holder.tvDescription.setText(news.getDescription());
        Picasso.with(context).load(news.getFullImage()).into(holder.imgFullImage);
        holder.view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(news.getLink()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsArrayLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvPubDate,tvDescription;
        ImageView imgFullImage;
        View view;
        public MyViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvPubDate=itemView.findViewById(R.id.tvPubDate);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            imgFullImage=itemView.findViewById(R.id.imgFullImage);


        }
    }
}
