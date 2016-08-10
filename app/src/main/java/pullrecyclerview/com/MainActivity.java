package pullrecyclerview.com;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private HomeAdapter mHomeAdapter;

    private List<String> mData;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:

                    mData.clear();
                    for (int i = 0; i < 50; i++) {
                        mData.add("item" + i);
                    }
                    mSwipeRefreshLayout.setRefreshing(false);
                    mHomeAdapter.notifyDataSetChanged();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout =  (SwipeRefreshLayout) findViewById(R.id.swipeRefreshlayout);
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerview);

        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mData.add("item" + i);
        }

        //布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置是否反向显示
        layoutManager.setReverseLayout(false);
        //设置显示的方向
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mHomeAdapter = new HomeAdapter(this, mData);
        mRecyclerView.setAdapter(mHomeAdapter);
        //下拉刷新

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageAtTime(1, 3000);
            }
        });
    }
}
