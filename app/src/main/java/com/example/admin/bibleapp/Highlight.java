package com.example.admin.bibleapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Date;

public class Highlight implements Parcelable {
    public static final Creator<Highlight> CREATOR = new C02281();
    public static final String LOCAL_DB_HIGHLIGHT_BOOK_ID = "bookId";
    public static final String LOCAL_DB_HIGHLIGHT_CONTENT = "content";
    public static final String LOCAL_DB_HIGHLIGHT_CONTENT_POST = "contentPost";
    public static final String LOCAL_DB_HIGHLIGHT_CONTENT_PRE = "contentPre";
    public static final String LOCAL_DB_HIGHLIGHT_ID = "highlightId";
    public static final String LOCAL_DB_HIGHLIGHT_PAGE = "page";
    public static final String LOCAL_DB_HIGHLIGHT_TYPE = "type";
    public static final String MEDIA_OVERLAY_STYLE = "epub-media-overlay-playing";
    String bookId;
    String content;
    String contentPost;
    String contentPre;
    int currentPagerPostion;
    int currentWebviewScrollPos;
    Date date;
    String highlightId;
    int id;
    String note;
    int page;
    String type;

    static class C02281 implements Creator<Highlight> {
        C02281() {
        }

        public Highlight createFromParcel(Parcel in) {
            return new Highlight(in);
        }

        public Highlight[] newArray(int size) {
            return new Highlight[size];
        }
    }

    public enum HighlightStyle {
        Yellow,
        Green,
        Blue,
        Pink,
        Underline,
        TextColor,
        DottetUnderline,
         Normal;

//        public static HighlightStyle styleForClass(String className) {
//            Object obj;
//            switch (className.hashCode()) {
//                case 325510387:
//                    if (className.equals("highlight-underline")) {
//                        obj = 4;
//                        break;
//                    }
//                    break;
//                case 469931475:
//                    if (className.equals("highlight-blue")) {
//                        obj = 2;
//                        break;
//                    }
//                    break;
//                case 470345455:
//                    if (className.equals("highlight-pink")) {
//                        obj = 3;
//                        break;
//                    }
//                    break;
//                case 1284329389:
//                    if (className.equals("highlight-yellow")) {
//                        obj = null;
//                        break;
//                    }
//                    break;
//                case 1687754922:
//                    if (className.equals("highlight-green")) {
//                        obj = 1;
//                        break;
//                    }
//                    break;
//            }
//            switch (obj) {
//                case 0:
//                    return Yellow;
//                case 1:
//                    return Green;
//                case 2:
//                    return Blue;
//                case 3:
//                    return Pink;
//                case 4:
//                    return Underline;
//                default:
//                    return Yellow;
//            }
//        }

        public static String classForStyle(HighlightStyle style) {
            switch (style) {
                case Yellow:
                    return "highlight-yellow";
                case Green:
                    return "highlight-green";
                case Blue:
                    return "highlight-blue";
                case Pink:
                    return "highlight-pink";
                case Underline:
                    return "highlight-underline";
                case DottetUnderline:
                    return "mediaOverlayStyle1";
                case TextColor:
                    return "mediaOverlayStyle2";
                default:
                    return "mediaOverlayStyle0";
            }
        }

        static String colorForStyle(HighlightStyle style, boolean nightMode) {
            switch (style) {
                case Yellow:
                    return "#FFEB6B";
                case Green:
                    return "#C0ED72";
                case Blue:
                    return "#ADD8FF";
                case Pink:
                    return "#FFB0CA";
                case Underline:
                    return "#F02814";
                default:
                    return "#FFEB6B";
            }
        }
    }

    public Highlight(int id, String bookId, String content, String contentPost, String contentPre, Date date, String highlightId, int page, String type, String note) {
        this.id = id;
        this.bookId = bookId;
        this.content = content;
        this.contentPost = contentPost;
        this.contentPre = contentPre;
        this.date = date;
        this.highlightId = highlightId;
        this.page = page;
        this.type = type;
        this.note = note;
    }

    public Highlight(String bookId, String content, String contentPost, String contentPre, Date date, String highlightId, int page, String type, int currentPagerPostion, int currentWebviewScrollPos, String note) {
        this.bookId = bookId;
        this.content = content;
        this.contentPost = contentPost;
        this.contentPre = contentPre;
        this.date = date;
        this.highlightId = highlightId;
        this.page = page;
        this.type = type;
        this.currentPagerPostion = currentPagerPostion;
        this.currentWebviewScrollPos = currentWebviewScrollPos;
        this.note = note;
    }

    protected Highlight(Parcel in) {
        readFromParcel(in);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentPost() {
        return this.contentPost;
    }

    public void setContentPost(String contentPost) {
        this.contentPost = contentPost;
    }

    public String getContentPre() {
        return this.contentPre;
    }

    public void setContentPre(String contentPre) {
        this.contentPre = contentPre;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHighlightId() {
        return this.highlightId;
    }

    public void setHighlightId(String highlightId) {
        this.highlightId = highlightId;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCurrentPagerPostion() {
        return this.currentPagerPostion;
    }

    public void setCurrentPagerPostion(int currentPagerPostion) {
        this.currentPagerPostion = currentPagerPostion;
    }

    public int getCurrentWebviewScrollPos() {
        return this.currentWebviewScrollPos;
    }

    public void setCurrentWebviewScrollPos(int currentWebviewScrollPos) {
        this.currentWebviewScrollPos = currentWebviewScrollPos;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Highlight highlight = (Highlight) o;
        if (this.id != highlight.id || this.page != highlight.page) {
            return false;
        }
        if (this.bookId != null) {
            if (!this.bookId.equals(highlight.bookId)) {
                return false;
            }
        } else if (highlight.bookId != null) {
            return false;
        }
        if (this.content != null) {
            if (!this.content.equals(highlight.content)) {
                return false;
            }
        } else if (highlight.content != null) {
            return false;
        }
        if (this.contentPost != null) {
            if (!this.contentPost.equals(highlight.contentPost)) {
                return false;
            }
        } else if (highlight.contentPost != null) {
            return false;
        }
        if (this.contentPre != null) {
            if (!this.contentPre.equals(highlight.contentPre)) {
                return false;
            }
        } else if (highlight.contentPre != null) {
            return false;
        }
        if (this.date != null) {
            if (!this.date.equals(highlight.date)) {
                return false;
            }
        } else if (highlight.date != null) {
            return false;
        }
        if (this.highlightId != null) {
            if (!this.highlightId.equals(highlight.highlightId)) {
                return false;
            }
        } else if (highlight.highlightId != null) {
            return false;
        }
        if (this.type != null) {
            z = this.type.equals(highlight.type);
        } else if (highlight.type != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = ((this.id * 31) + (this.bookId != null ? this.bookId.hashCode() : 0)) * 31;
        if (this.content != null) {
            hashCode = this.content.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode2 + hashCode) * 31;
        if (this.contentPost != null) {
            hashCode = this.contentPost.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode2 + hashCode) * 31;
        if (this.contentPre != null) {
            hashCode = this.contentPre.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode2 + hashCode) * 31;
        if (this.date != null) {
            hashCode = this.date.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode2 + hashCode) * 31;
        if (this.highlightId != null) {
            hashCode = this.highlightId.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (((hashCode2 + hashCode) * 31) + this.page) * 31;
        if (this.type != null) {
            i = this.type.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "Highlight{id=" + this.id + ", bookId='" + this.bookId + '\'' + ", content='" + this.content + '\'' + ", contentPost='" + this.contentPost + '\'' + ", contentPre='" + this.contentPre + '\'' + ", date=" + this.date + ", highlightId='" + this.highlightId + '\'' + ", page=" + this.page + ", type='" + this.type + '\'' + '}';
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.bookId);
        dest.writeString(this.content);
        dest.writeString(this.contentPost);
        dest.writeString(this.contentPre);
        dest.writeSerializable(this.date);
        dest.writeString(this.highlightId);
        dest.writeInt(this.page);
        dest.writeString(this.type);
        dest.writeInt(this.currentPagerPostion);
        dest.writeInt(this.currentWebviewScrollPos);
        dest.writeString(this.note);
    }

    private void readFromParcel(Parcel in) {
        this.id = in.readInt();
        this.bookId = in.readString();
        this.content = in.readString();
        this.contentPost = in.readString();
        this.contentPre = in.readString();
        this.date = (Date) in.readSerializable();
        this.highlightId = in.readString();
        this.page = in.readInt();
        this.type = in.readString();
        this.currentPagerPostion = in.readInt();
        this.currentWebviewScrollPos = in.readInt();
        this.note = in.readString();
    }
}
