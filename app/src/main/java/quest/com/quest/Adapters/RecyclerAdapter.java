package quest.com.quest.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import quest.com.quest.R;

/**
 * Created by kumbh on 11-03-2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.HolderView>{
private Context ctx;


    public RecyclerAdapter(Context context){
        this.ctx =context;
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upcoming_exam,parent,false);
        return new HolderView(itemView);
    }

    @Override
    public void onBindViewHolder(HolderView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }



    public  class HolderView extends RecyclerView.ViewHolder{

        public HolderView(View itemView) {
            super(itemView);

        }
    }
}
