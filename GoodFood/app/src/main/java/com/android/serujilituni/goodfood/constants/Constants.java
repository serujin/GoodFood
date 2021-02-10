package com.android.serujilituni.goodfood.constants;

import com.android.serujilituni.goodfood.R;

public class Constants {
    //RESTAURANT INFO
    private static final String RESTAURANT_ID = "restaurant_id";
    private static final String RESTAURANT_NAME = "restaurant_name";
    private static final String RESTAURANT_ADDRESS = "restaurant_address";
    //PLATE INFO
    private static final String PLATE_ID = "plate_id";
    private static final String PLATE_NAME = "plate_name";
    private static final String PLATE_PRICE = "plate_price";
    private static final String PLACE_DESC = "plate_desc";
    //USER INFO
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_ADDRESS = "user_address";
    public static final String USER_PASSWORD = "user_password";
    //ORDER INFO
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_PRICE = "order_price";
    public static final String ORDER_DATETIME = "order_datetime";

    public static final int LOGIN_REGISTER_TV_INDEX = 0;
    public static final int LOGIN_PASSWORD_TV_INDEX = 1;
    public static final int LOGIN_EMAIL_ET_INDEX = 0;
    public static final int LOGIN_PASSWORD_ET_INDEX = 1;

    public static final int REGISTER_EMAIL_ET_INDEX = 0;
    public static final int REGISTER_PASSWORD_ET_INDEX = 1;
    public static final int REGISTER_CONFIRMED_PASSWORD_ET_INDEX = 2;
    public static final int REGISTER_NAME_ET_INDEX = 3;

    public static final String DB_USERS_REFERENCE = "users";
    public static final String DB_RESTAURANTS_REFERENCE = "restaurants";

    public static final int[] RESTAURANTS_DRAWABLES = new int[] {
            R.drawable.mcdonalds,
            R.drawable.kebab,
            R.drawable.goikogrill,
            R.drawable.tacobell,
            R.drawable._00mon,
            R.drawable.telepizza,
            R.drawable.fosters,
            R.drawable.udon,
            R.drawable.kagura,
            R.drawable.superbestia
    };

}
