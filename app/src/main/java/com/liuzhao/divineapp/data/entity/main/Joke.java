package com.liuzhao.divineapp.data.entity.main;

import java.io.Serializable;

/**
 * Created by liuzhao on 2017/6/22.
 */

public class Joke implements Serializable {
    String content;
    String updatetime;
    String unixtime;
    String hashId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(String unixtime) {
        this.unixtime = unixtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
