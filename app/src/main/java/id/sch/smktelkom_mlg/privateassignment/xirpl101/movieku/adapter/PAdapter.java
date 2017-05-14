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
import id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.model.Popular;

/**
 * Created by user on 14/5/2017.
 */

public class PAdapter extends RecyclerView.Adapter<PAdapter.ViewHolder> {
    ArrayList<Popular> popularList;
    IPopularAdapter mIPopularAdapter;
    Context context;

    public PAdapter(Context context, ArrayList<Popular> popularList) {

        this.popularList = popularList;
        this.context = context;
        mIPopularAdapter = (IPopularAdapter) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_1, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Popular popular = popularList.get(position);
        holder.tvJudul.setText(popular.title);
        holder.tvDeskripsi.setText(popular.overview);
        holder.tvDate.setText(popular.release_date);
        Glide.with(context).load("http://image.tmdb.org/t/p/w500" + popular.poster_path)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.ivPoster);

    }

    @Override
    public int getItemCount() {
        if (popularList != null)
            return popularList.size();
        return 0;

    }

    public interface IPopularAdapter {
        void doWatchP(String title, String overview);
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
                    Popular popular = popularList.get(getAdapterPosition());
                    mIPopularAdapter.doWatchP(popular.title, popular.overview);
                }
            });

        }
    }
}
