package com.qiusuo.community.chat.model;

import java.util.Date;

public class CommunityUser implements Comparable<CommunityUser> {

    private String username;
    private Date joinedAt = new Date();

    public CommunityUser() {

    }

    public CommunityUser(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Date getJoinedAt() {
        return joinedAt;
    }

    @Override
    public String toString() {
        return this.username;
    }

    @Override
    public int compareTo(CommunityUser chatRoomUser) {
        return this.username.compareTo(chatRoomUser.getUsername());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CommunityUser other = (CommunityUser) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }
}
