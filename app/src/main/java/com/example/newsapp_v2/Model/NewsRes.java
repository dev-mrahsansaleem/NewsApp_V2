package com.example.newsapp_v2.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsRes {
    @SerializedName("status")
    private String status;
    @SerializedName("totalResults")
    private int totalResults;
    @SerializedName("articles")
    private List<Artical> articalList;

    public NewsRes(String status, int totalResults, List<Artical> articalList) {
        this.status = status;
        this.totalResults = totalResults;
        this.articalList = articalList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Artical> getArticalList() {
        return articalList;
    }

    public void setArticalList(List<Artical> articalList) {
        this.articalList = articalList;
    }
}
