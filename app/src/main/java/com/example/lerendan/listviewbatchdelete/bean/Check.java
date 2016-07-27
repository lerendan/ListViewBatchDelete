package com.example.lerendan.listviewbatchdelete.bean;

/**
 * Created by lerendan on 2016/7/27.
 */
public class Check {

    private String id;  //ID
    private String name;   //姓名
    private boolean isChecked;  //选中状态

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    public Check(String id, String name) {
        this.id = id;
        this.name = name;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

