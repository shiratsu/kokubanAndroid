package jp.co.hiratsuka.kokuban.conf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;


public class CodeDefine
{
    private CodeDefine() {
    }


    public static final String DOWNLOAD_DEFAULT_DATE = "1990-10-01 00:00:00";







    public static final int MESSAGE_WHAT_IMAGE_SELECT_END = 22;
    public static final int MESSAGE_WHAT_IMAGE_SELECT_ERROR = -21;

    public static final int MESSAGE_WHAT_NOT_FOUND_ERROR = -1;
    public static final int MESSAGE_WHAT_BAD_ACCESS_ERROR = -2;

    public static final int ACTION_LIST = 1;
    public static final int ACTION_ALL_HISTORY_LIST = 10;
    public static final int ACTION_DETAIL = 2;
    public static final int ACTION_MAP_LIST = 3;

    public static final int ACTION_PHOTO = 0;
    public static final int ACTION_MOVIE = 1;


    public static final int REQUEST_PICK_CONTACT = 1;
    public static final int REQUEST_PICK_CONTACT_MOVIE = 2;
    public static final int REQUEST_PICK_CONTACT_PEOPLE = 3;

    public static final int REQUEST_IMAGE_CAPTURED = 999;
    public final static int REQUEST_VIDEO_CAPTURED = 1000;



    public static final int MAX_PROGRESS = 100;

    public static final int LIST_PAGING_COUNT = 10;
    public static final int IMAGELIST_PAGING_COUNT = 60;

    public static final int ACTION_CAMERA = 0;
    public static final int ACTION_GALLERY = 1;

    public static final int listImageWidth = 120;
    public static final int listImageHeight = 90;

    public static final int gridImageWidth = 150;
    public static final int gridImageHeight = 110;

    public static final int detailImageWidth = 160;
    public static final int detailImageHeight = 120;

    public static final int slidePortraitImageWidth = 420;
    public static final int slidePortraitImageHeight = 700;
    public static final int slideLandscapeImageWidth = 700;
    public static final int slideLandscapeImageHeight = 420;

    public static final int defaultImageWidth = 0;
    public static final int defaultImageHeight = 0;


    public static final int IMAGE_NEXT = 1;
    public static final int IMAGE_PREV = -1;


    public static final int VALID_REQUIRED = 0;
    public static final int VALID_MAIL_ADDRESS = 1;
    public static final int VALID_HANKAKU = 2;
    public static final int VALID_HANKAKU_NUM = 3;
    public static final int VALID_LENGTH = 99;


    public static final int SORT_LASTUPDATE_ASC = 1;
    public static final int SORT_LASTUPDATE_DESC = -1;

    public static final int SORT_REGISTERDATE_ASC = 2;
    public static final int SORT_REGISTERDATE_DESC = -2;

    public static final int SORT_BONJI_ASC = 2;
    public static final int SORT_BONJI_DESC = 22;




    public static final String AUTH_UPDATE = "1";
    public static final String NOT_AUTH_UPDATE = "0";


    public static final String AUTH_ADMIN = "1";
    public static final String NOT_AUTH_ADMIN = "0";



    public static final int SWIPE_MIN_DISTANCE = 120;
    public static final int SWIPE_MAX_OFF_PATH = 250;
    public static final int SWIPE_THRESHOLD_VELOCITY = 200;

    public static final double ERROR_DOUBLE = 10000.0;




    public static final int PROGRESS_DEFAULT_DOWNLOAD = 0;
    public static final int PROGRESS_FILE_UPLOAD = 1;
    public static final int PROGRESS_POST_DATA = 2;
    public static final int PROGRESS_EDIT_DATA = 3;
    public static final int PROGRESS_DATA_LOAD = 4;
    public static final int CONFIRM_DIALOG = 5;
    public static final int PROGRESS_DELETE_DATA = 10;
    public static final int PROGRESS_IMAGE_LOAD = 11;


    public static final int SQLITE_COLUMN_REQUIRED 	= 1;
    public static final int SQLITE_COLUMN_AUTO 		= 9;
    public static final int SQLITE_COLUMN_OPTION 	= 0;

    public static final int MENU_0 = 0;
    public static final int MENU_1 = 1;
    public static final int MENU_2 = 2;
    public static final int MENU_3 = 3;
    public static final int MENU_4 = 4;
    public static final int MENU_5 = 5;
    public static final int MENU_6 = 6;
    public static final int MENU_7 = 7;


    public static List<String> feedNowDate() {
        Date date1 = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf3 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf4 = new SimpleDateFormat("dd");

        String yyyymmdd = sdf1.format(date1);
        String yyyy = sdf2.format(date1);
        String mm = sdf3.format(date1);
        String dd = sdf4.format(date1);

        List<String> dateAry = new ArrayList<String>();
        dateAry.add(yyyymmdd);
        dateAry.add(yyyy);
        dateAry.add(mm);
        dateAry.add(dd);
        return dateAry;
    }


}

