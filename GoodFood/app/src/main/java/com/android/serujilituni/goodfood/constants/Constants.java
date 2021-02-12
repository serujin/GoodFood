package com.android.serujilituni.goodfood.constants;

import com.android.serujilituni.goodfood.R;

public class Constants {
    public static final int LOGIN_REGISTER_TV_INDEX = 0;
    public static final int LOGIN_PASSWORD_TV_INDEX = 1;
    public static final int LOGIN_EMAIL_ET_INDEX = 0;
    public static final int LOGIN_PASSWORD_ET_INDEX = 1;

    public static final int REGISTER_EMAIL_ET_INDEX = 0;
    public static final int REGISTER_PASSWORD_ET_INDEX = 1;
    public static final int REGISTER_CONFIRMED_PASSWORD_ET_INDEX = 2;
    public static final int REGISTER_NAME_ET_INDEX = 3;

    public static final int ORDER_SUMMARY_FINALIZE_BTN_INDEX = 0;
    public static final int ORDER_SUMMARY_CANCEL_BTN_INDEX = 1;

    public static final int ORDER_SUMMARY_ADDRESS_TV = 0;
    public static final int ORDER_SUMMARY_DISTANCE_TV = 1;
    public static final int ORDER_SUMMARY_PRICE_TV = 2;
    public static final int ORDER_SUMMARY_DELIVER_TV = 3;
    public static final int ORDER_SUMMARY_FINAL_PRICE_TV = 4;

    public static final int CHANGE_DATA_PASSWORD_BTN = 0;
    public static final int CHANGE_DATA_NAME_BTN = 1;

    public static final String DB_USERS_REFERENCE = "users";
    public static final String DB_RESTAURANTS_REFERENCE = "restaurants";
    public static final String DB_ORDER_REFERENCE = "orders";

    public static final String PLATE_NAME_KEY = "name";
    public static final String PLATE_PRICE_KEY = "price";
    public static final String RESTAURANTS_ADDRESS_KEY = "address";
    public static final String RESTAURANTS_PLATES_KEY = "plates";

    public static final String MENU_EXTRA_INTENT = "restaurant";

    public static final int[] RESTAURANTS_DRAWABLES = new int[] {
            R.drawable.mcdonalds,
            R.drawable.kebab_restaurant,
            R.drawable.goikogrill,
            R.drawable.tacobell,
            R.drawable._00mon,
            R.drawable.telepizza,
            R.drawable.fosters,
            R.drawable.udon,
            R.drawable.tagli_restaurante,
            R.drawable.kfc_restaurna
    };

    public static final int[][] PLATES_DRAWABLES = new int[][] {
            {R.drawable.cbo, R.drawable.double_bacon, R.drawable.big_mac, R.drawable.mcpollo, R.drawable.chicken_cheese},
            {R.drawable.durum, R.drawable.ensalada_kebab, R.drawable.kebab, R.drawable.patatas, R.drawable.fingers},
            {R.drawable.kiki, R.drawable.yankee, R.drawable.pigma, R.drawable.chipotle, R.drawable.goiko},
            {R.drawable.gsb, R.drawable.chicken, R.drawable.quesarito, R.drawable.quesadilla, R.drawable.crunchy},
            {R.drawable.chori, R.drawable.pulled, R.drawable.pollo, R.drawable.cabra, R.drawable.lechuga},
            {R.drawable.libra, R.drawable.gourment, R.drawable.nachos, R.drawable.carbonara, R.drawable.crispy},
            {R.drawable.cesar, R.drawable.director, R.drawable.pork, R.drawable.smoked, R.drawable.ribs},
            {R.drawable.gyoza, R.drawable.curry, R.drawable.spicy, R.drawable.chicken_yaki, R.drawable.nabe},
            {R.drawable.foie, R.drawable.canneloni, R.drawable.insalata, R.drawable.insa_tagli, R.drawable.pepe},
            {R.drawable.infame, R.drawable.bbc, R.drawable.wrap, R.drawable.double_krucnh, R.drawable.kfc_menu}
    };

}
