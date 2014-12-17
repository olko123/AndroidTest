package com.olko123.android.androidtest.utils.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.olko123.android.androidtest.dto.articles.ArticlesDescriptionDTO;

public class ArticleDescription implements Parcelable,
        Comparable<ArticleDescription> {
    private String title;
    private String subtitle;
    private String imageUrl;
    private String id;
    private int ranking;

    public ArticleDescription(ArticlesDescriptionDTO articlesDescriptionDTO) {
        this.title = articlesDescriptionDTO.getTitle();
        this.subtitle = articlesDescriptionDTO.getSubtitle();
        this.imageUrl = articlesDescriptionDTO.getThumb().getLink();
        this.id = articlesDescriptionDTO.getId();
        this.ranking = articlesDescriptionDTO.getRanking();
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(imageUrl);
        dest.writeString(id);
        dest.writeInt(ranking);
    }

    public static final Parcelable.Creator<ArticleDescription> CREATOR = new Parcelable.Creator<ArticleDescription>() {

        @Override
        public ArticleDescription[] newArray(int size) {
            return new ArticleDescription[size];
        }

        @Override
        public ArticleDescription createFromParcel(Parcel source) {
            return new ArticleDescription(source);
        }
    };

    public ArticleDescription(Parcel source) {
        this.title = source.readString();
        this.subtitle = source.readString();
        this.imageUrl = source.readString();
        this.id = source.readString();
        this.ranking = source.readInt();
    }

    @Override
    public int compareTo(ArticleDescription another) {
        return (this.ranking > another.ranking ? 1
                : (this.ranking == another.ranking ? 0 : -1));
    }
}