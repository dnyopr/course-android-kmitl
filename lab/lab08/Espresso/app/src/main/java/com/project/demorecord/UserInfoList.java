package com.project.demorecord;

import java.util.List;

public class UserInfoList {
    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public void setListClear(){
        userInfoList.clear();
    }

    private List<UserInfo> userInfoList;
}
