//package com.example.admin.bibleapp.util;
//
//import android.util.Log;
//import com.example.admin.bibleapp.Highlight;
//import java.util.Calendar;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import static com.example.admin.bibleapp.R.string.highlight;
//
//public class HighlightUtil {
//    private static final String TAG = HighlightUtil.class.getSimpleName();
//    public static final int mHighlightRange = 30;
//
//    public static Highlight matchHighlight(String html, String highlightId, String bookTitle, int pageNo) {
//        Exception e;
//        String contentPre = "";
//        String contentPost = "";
//        Highlight highlight = null;
//        try {
//            Matcher matcher = Pattern.compile("<highlight id=\"" + highlightId + "\" onclick=\".*?\" class=\"(.*?)\">(.*?)</highlight>", 34).matcher(html);
//            if (!matcher.find()) {
//                return null;
//            }
//            contentPre = html.substring(matcher.start() - 30, matcher.start());
//            contentPost = html.substring(matcher.end(), matcher.end() + 30);
//            if (contentPre != null && contentPre.contains(">") && Pattern.compile("((?=[^>]*$)(.|\\s)*$)", 34).matcher(contentPre).find()) {
//                contentPre = contentPre.substring(contentPre.lastIndexOf(62) + 1, contentPre.length());
//            }
//            if (contentPost != null && contentPost.contains("<") && Pattern.compile("^((.|\\s)*?)(?=<)", 34).matcher(contentPost).find()) {
//                contentPost = contentPost.substring(0, contentPost.indexOf(60));
//            }
//            //Highlight highlight2 = new Highlight();
////            try {
////                highlight2.setContentPre(contentPre);
////                highlight2.setType(matcher.group(1));
////                highlight2.setContentPost(contentPost);
////                highlight2.setHighlightId(highlightId);
////                highlight2.setContent(matcher.group(2));
////                highlight2.setBookId(bookTitle);
////                highlight2.setPage(pageNo);
////                highlight2.setDate(Calendar.getInstance().getTime());
////                return highlight2;
////            } catch (Exception e2) {
////                e = e2;
////                highlight = highlight2;
////                Log.d(TAG, e.getMessage());
////                return highlight;
//            }
//        } catch (Exception e3) {
//            e = e3;
//            Log.d(TAG, e.getMessage());
//            return highlight;
//        }
//    }
//}
