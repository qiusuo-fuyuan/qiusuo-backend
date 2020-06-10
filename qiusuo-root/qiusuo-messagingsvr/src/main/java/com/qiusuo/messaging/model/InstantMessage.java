package com.qiusuo.messaging.model;

import com.google.common.base.Strings;
import com.qiusuo.community.chat.utils.SystemUsers;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;

@Table("messages")
public class InstantMessage {

    @PrimaryKey
    private InstantMessageKey messageKey;

    private String fromUser;
    private String toUser;
    private String text;

    public InstantMessage() {
        this.messageKey.date = new Date();
    }

    public boolean isPublic() {
        return Strings.isNullOrEmpty(this.toUser);
    }
    public boolean isFromAdmin() {
        return this.fromUser.equals(SystemUsers.ADMIN.getUsername());
    }
    public String getUsername() {
        return this.messageKey.username;
    }
    public void setUsername(String username) {
        this.messageKey.username = username;
    }
    public String getChatRoomId() {
        return this.messageKey.chatRoomId;
    }
    public void setChatRoomId(String chatRoomId) {
        this.messageKey.chatRoomId = chatRoomId;
    }
    public Date getDate() {
        return this.messageKey.date;
    }
    public void setDate(Date date) {
        this.messageKey.date = date;
    }
    public String getFromUser() {
        return fromUser;
    }
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }
    public String getToUser() {
        return toUser;
    }
    public void setToUser(String toUser) {
        this.toUser = toUser;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.messageKey.chatRoomId == null) ? 0 : this.messageKey.chatRoomId.hashCode());
        result = prime * result + ((fromUser == null) ? 0 : fromUser.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((toUser == null) ? 0 : toUser.hashCode());
        result = prime * result + ((this.messageKey.username == null) ? 0 : this.messageKey.username.hashCode());
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
        InstantMessage other = (InstantMessage) obj;
        if (this.messageKey.chatRoomId == null) {
            if (other.messageKey.chatRoomId != null)
                return false;
        } else if (!this.messageKey.chatRoomId.equals(other.messageKey.chatRoomId))
            return false;
        if (fromUser == null) {
            if (other.fromUser != null)
                return false;
        } else if (!fromUser.equals(other.fromUser))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        if (toUser == null) {
            if (other.toUser != null)
                return false;
        } else if (!toUser.equals(other.toUser))
            return false;
        if (this.messageKey.username == null) {
            if (other.messageKey.username != null)
                return false;
        } else if (!this.messageKey.username.equals(other.messageKey.username))
            return false;
        return true;
    }
}
