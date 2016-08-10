package pullrecyclerview.com;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tt on 2016/6/22.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ListViewHolder> {

    private List<String> data;
    private Context mContext;
    private static final int IS_NORMAL = 1;//正常
    private static final int IS_HEADER = 2;//头部
    private static final int IS_FOOTER = 3;//尾部

    public HomeAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IS_HEADER;
        } else if (position == data.size() + 1) {
            return IS_FOOTER;
        } else {
            return IS_NORMAL;
        }
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == IS_NORMAL) {

            View view = View.inflate(mContext, R.layout.home_fragment_item, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new ListViewHolder(view, IS_NORMAL);
        } else if (viewType == IS_HEADER) {

            View view = View.inflate(mContext, R.layout.home_fragment_header, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new ListViewHolder(view, IS_HEADER);
        } else if (viewType == IS_FOOTER) {

            View view = View.inflate(mContext, R.layout.home_fragment_footer, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new ListViewHolder(view, IS_FOOTER);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        if (position != 0 && position != data.size() + 1 && holder.viewType == IS_NORMAL) {

            String datas = data.get(position-1);
            holder.setData(datas);
        } else if (position == 0 && holder.viewType == IS_HEADER) {


        } else if (position == data.size() + 1 && holder.viewType == IS_FOOTER) {

        }

    }

    @Override
    public int getItemCount() {
        return data.size()+2;
    }


    public class ListViewHolder
            extends RecyclerView.ViewHolder {
        private TextView pastTime;
        private int viewType;
        private TextView headerTv;
        private TextView footerTV;

        public ListViewHolder(View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;

            if (viewType == IS_NORMAL) {
                pastTime = (TextView) itemView.findViewById(R.id.tv_past_time);

            } else if (viewType == IS_HEADER) {

                headerTv = (TextView) itemView.findViewById(R.id.tv_header);
                headerTv.setText("recyclerView_Header");
            } else if (viewType == IS_FOOTER) {
                footerTV = (TextView) itemView.findViewById(R.id.tv_footer);
                footerTV.setText("recyclerView_FOOTER");
            }

        }

        public void setData(String data) {
            pastTime.setText(data);
        }
    }
}
