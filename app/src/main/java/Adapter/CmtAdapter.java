package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.both.Api;
import com.example.both.R;
import com.example.both.activitySignUp;

import java.util.List;

import model.Cmt;
import model.ResponceDelCmt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CmtAdapter extends RecyclerView.Adapter<CmtAdapter.CmtViewHolder> {
    private Context mContext;
    private List<Cmt> mListCmt;
    private PostAdapter.RecyclerViewClickListener listener;

    public CmtAdapter(Context mContext, List<Cmt> mListCmt) {
        this.mContext = mContext;
        this.mListCmt = mListCmt;
    }
    public void setData(List<Cmt> list){
        this.mListCmt = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CmtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_coment, parent, false);

        return new CmtViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CmtViewHolder holder, int position) {
        Cmt cmt = mListCmt.get(position);
        if(cmt==null){
            return;
        }
        holder.tvusnamecmt.setText(cmt.getAddby());
        holder.tvcmt.setText(cmt.getContent());

    }

    @Override
    public int getItemCount() {
        return mListCmt.size();
    }

    public class CmtViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        private TextView tvusnamecmt, tvcmt;

        public CmtViewHolder(@NonNull View itemView) {
            super(itemView);
            tvusnamecmt = itemView.findViewById(R.id.tv_usnamecmt);
            tvcmt = itemView.findViewById(R.id.tv_cmt);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            System.out.println("roi");
            Call<ResponceDelCmt> i = Api.api.delCmt(String.valueOf(tvcmt.getText()));
            i.enqueue(new Callback<ResponceDelCmt>() {
                @Override
                public void onResponse(Call<ResponceDelCmt> call, Response<ResponceDelCmt> response) {
                    Toast.makeText(mContext, "ok", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<ResponceDelCmt> call, Throwable t) {
                    Toast.makeText(mContext, "bug", Toast.LENGTH_SHORT).show();

                }
            });


        }
    }
}
