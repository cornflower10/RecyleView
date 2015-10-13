package com.example.cornflower.recyleview;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView re_view;
    private ReAdapter mReAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();
    }

    private void initDatas() {
        List<String> mDatas = new ArrayList<>();
        for (int i = 'A'; i <'z';i++){
            mDatas.add((char)i+"");
        }
        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        mReAdapter = new ReAdapter(MainActivity.this,mDatas);
        re_view.setAdapter(mReAdapter);
        re_view.setLayoutManager(linearLayoutManager);
        re_view.setItemAnimator(new DefaultItemAnimator());

        mReAdapter.setOnItemClickListener(new ReAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int postion) {
                /**
                 * SnackBar使用时候有几点需要注意:
                 1. make()方法的第一个参数的view,不能是有一个ScrollView.
                    因为SnackBar的实现逻辑是往这个View去addView.而ScrollView我们知道,是只能有一个Child的.否则会Exception.
                 2. 如果大家在想把Toast替换成SnackBar.需要注意的是,Toast和SnackBar的区别是,前者是悬浮在所有布局之上的包括键盘,
                    而SnackBar是在View上直接addView的.
                    所以SnackBar.show()的时候,要注意先把Keyboard.hide()了.不然,键盘就会遮住SnackBar.
                 3. 在Android2.3,SnackBar即使用了CoordinatorLayout也是不支持Swipe功能.
                    个人觉得,这一点点小瑕疵就可以忽略啦哈哈.
                 4. 在Android2.3,SnackBar的深色背景颜色和字体颜色相近.可以用SpannableString换一下String的颜色在传给make()方法.
                 */
                Snackbar.make(v,"postion:"+postion,Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View v, int postion) {

            }
        });

    }

    private void initViews() {
        re_view= (RecyclerView) findViewById(R.id.re_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
       switch (id){
           case R.id.add:
               mReAdapter.addItem(1);
               break;
           case R.id.delete:
               mReAdapter.deleteItem(1);
               break;
       }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
