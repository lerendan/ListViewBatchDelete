package com.example.lerendan.listviewbatchdelete.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lerendan.listviewbatchdelete.R;
import com.example.lerendan.listviewbatchdelete.adapter.Check_Adapter;
import com.example.lerendan.listviewbatchdelete.bean.Check;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 主界面
 * Created by lerendan on 2016/7/27.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Check> mData;
    private Button btn_confirm;
    private ListView mListview;
    private Check_Adapter mAdapter;
    private int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_check_ui);

        initView();
        initData();
        setListener();
    }

    private void initView() {
        mListview = (ListView) findViewById(R.id.lv);
        btn_confirm = (Button) findViewById(R.id.confirm);
    }

    private void initData() {
        if (mAdapter == null) {
            mData = getCheckData();
            mAdapter = new Check_Adapter(MainActivity.this, mData);
            mListview.setAdapter(mAdapter);
        } else {
            mAdapter.setChecks(mData);
            mAdapter.notifyDataSetChanged();
        }

    }

    private List<Check> getCheckData() {
        List<Check> data = new ArrayList<Check>();
        Check check1 = new Check("id111111111111", "张三 11111111");
        Check check2 = new Check("id222222222222", "但超 22222222");
        Check check3 = new Check("id333333333333", "李四 33333333");
        Check check4 = new Check("id444444444444", "王五 44444444");
        Check check5 = new Check("id555555555555", "赵六 55555555");
        Check check6 = new Check("id666666666666", "张七 66666666");
        Check check7 = new Check("id777777777777", "吴八 77777777");
        Check check8 = new Check("id888888888888", "关关 88888888");
        Check check9 = new Check("id999999999999", "笑笑 99999999");
        data.add(check1);
        data.add(check2);
        data.add(check3);
        data.add(check4);
        data.add(check5);
        data.add(check6);
        data.add(check7);
        data.add(check8);
        data.add(check9);
        return data;
    }

    private void setListener() {
        mListview.setOnItemClickListener(new MyOnItemClickListner());
        btn_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                if (n != 0) {
                    dialog();//调用确认对话框
                } else {
                    Toast.makeText(this, "未点击", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }

    }

    /**
     * 确认删除的对话框
     */
    public void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认删除吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                deleteCheckedItem();//确定后删除选中的item
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.setCancelable(false);//调用这个方法时，按对话框以外的地方不起作用。按返回键也不起作用
        builder.show();
    }

    /**
     * 删除选中的Item
     */
    public void deleteCheckedItem() {
        Iterator<Check> iterator = mData.iterator();
        while (iterator.hasNext()) {
            Check check = iterator.next();
            if (check.isChecked()) {
                iterator.remove();
            }
        }
        mAdapter.setChecks(mData);
        mAdapter.notifyDataSetChanged();
        mListview.setSelection(0);
        n = 0;
        btn_confirm.setText("确认（" + n + "）");
    }

    /**
     * Listview Item的点击事件
     */
    private class MyOnItemClickListner implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Check check = mData.get(position);
            if (check.isChecked()) {
                check.setChecked(false);
                n--;
                btn_confirm.setText("确认（" + n + "）");
            } else {
                check.setChecked(true);
                n++;
                btn_confirm.setText("确认（" + n + "）");
            }
            mAdapter.setChecks(mData);
            mAdapter.notifyDataSetChanged();
        }
    }
}
