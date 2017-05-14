package id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.model.NowPlaying;

/**
 * Created by user on 14/5/2017.
 */

public class NPAdapter extends RecyclerView.Adapter<NPAdapter.ViewHolder> {
    ArrayList<NowPlaying> nowPlayingList;
    INowPlayingAdapter mINowPlayingAdapter;
    Context context;

    public NPAdapter(Context context, ArrayList<NowPlaying> nowPlayingList) {

        this.nowPlayingList = nowPlayingList;
        this.context = context;
        mINowPlayingAdapter = (INowPlayingAdapter) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_1, parent, false);
        NPAdapter.ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NowPlaying nowPlaying = nowPlayingList.get(position);
        holder.tvJudul.setText(nowPlaying.title);
        holder.tvDeskripsi.setText(nowPlaying.overview);
        holder.tvDate.setText(nowPlaying.release_date);
        Glide.with(context).load("http://image.tmdb.org/t/p/w500" + nowPlaying.poster_path)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.ivPoster);

    }

    @Override
    public int getItemCount() {
        if (nowPlayingList != null)
            return nowPlayingList.size();
        return 0;

    }

    public interface INowPlayingAdapter {
        void doWatchNP(String title, String overview);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPoster;
        TextView tvJudul;
        TextView tvDeskripsi;
        TextView tvDate;
        ImageButton ibWatch;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            tvJudul = (TextView) itemView.findViewById(R.id.textViewJudul);
            tvDate = (TextView) itemView.findViewById(R.id.textViewDate);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewOverview);
            ibWatch = (ImageButton) itemView.findViewById(R.id.buttonWatch);

            ibWatch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NowPlaying nowPlaying = nowPlayingList.get(getAdapterPosition());
                    mINowPlayingAdapter.doWatchNP(nowPlaying.title, nowPlaying.overview);
                }
            });

        }
    }
}
