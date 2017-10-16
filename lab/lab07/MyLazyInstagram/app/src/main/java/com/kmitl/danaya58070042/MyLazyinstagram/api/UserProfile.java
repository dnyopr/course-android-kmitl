package com.kmitl.danaya58070042.MyLazyinstagram.api;

import java.util.List;

/**
 * Created by student on 10/6/2017 AD.
 */

public class UserProfile {
    private String user;
    private String urlProfile;
    private String bio;
    private List<Post> posts;
    private boolean isFollow;
    private int post;
    private int follower;
    private int following;

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int folowing) {
        this.following = folowing;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
}
