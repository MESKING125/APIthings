package com.example.apithings;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NewsModel implements Parcelable {
    private String source;
    private String author;
    private String title;
    private String desc;
    private String publish;
    private String image;

    protected NewsModel(Parcel in) {
        source = in.readString();
        author = in.readString();
        title = in.readString();
        desc = in.readString();
        publish = in.readString();
        image = in.readString();
    }

    NewsModel(){}

    public static final Creator<NewsModel> CREATOR = new Creator<NewsModel>() {
        @Override
        public NewsModel createFromParcel(Parcel in) {
            return new NewsModel(in);
        }

        @Override
        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
        }
    };

    public String getSource() {
         return source;
     }

     public void setSource(String source) {
         this.source = source;
     }

     public String getAuthor() {
         return author;
     }

     public void setAuthor(String author) {
         this.author = author;
     }

     public String getTitle() {
         return title;
     }

     public void setTitle(String title) {
         this.title = title;
     }

     public String getDesc() {
         return desc;
     }

     public void setDesc(String desc) {
         this.desc = desc;
     }

     public String getPublish() {
         return publish;
     }

     public void setPublish(String publish) {
         this.publish = publish;
     }

     public String getImage() {
         return image;
     }

     public void setImage(String image) {
         this.image = image;
     }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(source);
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(publish);
        dest.writeString(image);
    }
}
