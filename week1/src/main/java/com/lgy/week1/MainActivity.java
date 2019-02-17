package com.lgy.week1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.lgy.week1.api.Api;
import com.lgy.week1.api.adaoter.NameAdapter;
import com.lgy.week1.bean.ByNameBean;
import com.lgy.week1.bean.LgyBean;
import com.lgy.week1.presenter.Presenter;
import com.lgy.week1.view.IView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.names)
    EditText names;
    @BindView(R.id.eachs)
    Button eachs;
    @BindView(R.id.recy)
    RecyclerView recy;
    private NameAdapter nameAdapter;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new Presenter(this);
        GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recy.setLayoutManager(manager);
        recy.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        nameAdapter = new NameAdapter(this);
        recy.setAdapter(nameAdapter);
    }

    /**
     * 搜索按钮
     */
    @OnClick(R.id.eachs)
    public void onViewClicked() {
        presenter.getPre(Api.BYNAMEURL + "?keyword=" +
                names.getText().toString() + "&page=" + "1" + "&count=5", null, ByNameBean.class);
    }

    /**
     * 重写Iview的方法
     *
     * @param o
     */
    @Override
    public void getView(Object o) {
        if (o instanceof ByNameBean) {
            final ByNameBean byName = (ByNameBean) o;
            nameAdapter.setData(byName.getResult());
            nameAdapter.result(new NameAdapter.Cicklistener() {
                @Override
                public void setonclicklisener(int index) {
                    int commodityId = byName.getResult().get(index).getCommodityId();
                    dispaly(commodityId);
                }
            });

            nameAdapter.result(new NameAdapter.Cicklistener() {
                @Override
                public void setonclicklisener(int index) {
                    String masterPic = byName.getResult().get(index).getMasterPic();
                    int price = byName.getResult().get(index).getPrice();
                    EventBus.getDefault().postSticky(String.valueOf(price));
                    EventBus.getDefault().postSticky(masterPic);
                    startActivity(new Intent(MainActivity.this, ShowActivity.class));
                }
            });
        }
    }

    /**
     * @param commodityId
     */
    private void dispaly(int commodityId) {
        presenter.getPre(Api.SHOOPURL + "?commodityId=" + commodityId, null, LgyBean.class);
    }
}
