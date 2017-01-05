package com.bq2015.myview.widget;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.bq2015.myview.R;
import com.bq2015.myview.activity.BaseActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/************************************************************
 * Author:  FangQiQuan
 * Description:
 * Date:2016/6/20
 ************************************************************/
public class TestActivity extends BaseActivity implements TextWatcher {
    @BindView(R.id.et_content)
    EditText mEtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        mEtContent.addTextChangedListener(this);
        checkContent();
    }

    private void checkContent() {
        String strWhereText=mEtContent.getText().toString().trim();
        if (TextUtils.isEmpty(strWhereText)) {
            return;
        }
        boolean b = StringFilter(strWhereText);
        //不是中文的情况
        if (!b) {
            mEtContent.setText("");
        } else {

        }
    }

    //通过正则表达式来判断。 只允许显示中文
    public Boolean StringFilter(String str){
        String regEX="^[\u4E00-\u9FA5\uF900-\uFA2D]+$";
        Pattern p=Pattern.compile(regEX);
        Matcher m=p.matcher(str);
        Boolean b=m.matches();
        return b;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        checkContent();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
