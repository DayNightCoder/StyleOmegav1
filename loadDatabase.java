package com.example.sachinpc.styleomegav10;

import android.content.Context;

/**
 * Created by SachinPC on 8/12/2017.
 */

public class loadDatabase {
    String[] Mphotos;

    public void loadDatabase(Context context) {
        Mphotos = new String[]{
                "https://image.ibb.co/nkhDaQ/mens10.jpg",

                "https://image.ibb.co/kBLMUk/mens9.jpg",
                "https://image.ibb.co/mL1jh5/mens8.jpg",
                "https://image.ibb.co/dyhqN5/mens7.jpg",
                "https://image.ibb.co/eiDKFQ/mens6.jpg",
                "https://image.ibb.co/g93KFQ/mens5.jpg",
                "https://image.ibb.co/muneFQ/mens4.jpg",
                "https://image.ibb.co/iZ2Ph5/mens3.jpg",
                "https://image.ibb.co/fpVAN5/mens2.jpg",
                "https://image.ibb.co/eJs1Uk/mens1_new.jpg"};
        String[] WPhotos = new String[]{
                " https://image.ibb.co/m2etaQ/women10.jpg",
                "https://image.ibb.co/fU0YaQ/women9.jpg",
                "https://image.ibb.co/mWtVN5/women8.jpg",
                "https://image.ibb.co/kh6jh5/women7.jpg",
                "https://image.ibb.co/fZQc25/women6.jpg",
                "https://image.ibb.co/c4X1Uk/women4.jpg",
                "https://image.ibb.co/e7D89k/women3.jpg",
                "https://image.ibb.co/eLLAN5/women2.jpg",
                "https://image.ibb.co/fUwvpk/women1.jpg"};


        dbClass db = dbClass.getInstance(context);

        db.addProduct("Navy Blue", "Shirt", "200", Mphotos[1], "2450", "MEN","Men's designer wear, desinged in UK");
        db.addProduct("Royal Blue", "Shirt", "200", Mphotos[2], "2499", "MEN","Men's designer wear, desinged in UK");
        db.addProduct("Paradise Island", "Shirt", "200", Mphotos[3], "2450", "MEN","Men's designer wear, desinged in UK");
        db.addProduct("CK 2017", "Shirt", "200", Mphotos[4], "2450", "MEN","Men's designer wear, desinged in UK");
        db.addProduct("Tommy Hilfiger", "Shirt", "200", Mphotos[5], "2450", "MEN","Men's designer wear, desinged in UK");
        db.addProduct("German White", "Shirt", "200", Mphotos[6], "2450", "MEN","Men's designer wear, desinged in UK");
        db.addProduct("Rio Vibes", "Shirt", "200", Mphotos[7], "2450", "MEN","Men's designer wear, desinged in UK");
        db.addProduct("Office Wear", "Shirt", "200", Mphotos[8], "2450", "MEN","Men's designer wear, desinged in UK");
        db.addProduct("Yesterday", "Shirt", "200", Mphotos[9], "2450", "MEN","Men's designer wear, desinged in UK");
        db.addProduct("White House style", "Shirt", "200", Mphotos[0], "2450", "MEN","Men's designer wear, desinged in UK");
        db.addProduct("Exotic ornaments", "Blouse", "200", WPhotos[0], "2450", "WMN","Women's designer wear, get yourself the best of marks and spencer");
        db.addProduct("Ornamental sunday", "Blouse", "200", WPhotos[1], "3450", "WMN","Women's designer wear, get yourself the best of marks and spencer");
        db.addProduct("Casual top", "Blouse", "200", WPhotos[2], "2750", "WMN","Women's designer wear, get yourself the best of marks and spencer");
        db.addProduct("Forest", "Blouse", "200", WPhotos[3], "2850", "WMN","Women's designer wear, get yourself the best of marks and spencer");
        db.addProduct("Wildest dream", "Blouse", "200", WPhotos[4], "4350", "WMN","Women's designer wear, get yourself the best of marks and spencer");
        db.addProduct("Sunny Holiday", "Blouse", "200", WPhotos[5], "1450", "WMN","Women's designer wear, get yourself the best of marks and spencer");
        db.addProduct("Typical summer", "Blouse", "200", WPhotos[6], "2450", "WMN","Women's designer wear, get yourself the best of marks and spencer");
        db.addProduct("Autumn vacay", "Blouse", "200", WPhotos[7], "3550", "WMN","Women's designer wear, get yourself the best of marks and spencer");
        db.addProduct("Last holiday", "Blouse", "200", WPhotos[8], "2450", "WMN","Women's designer wear, get yourself the best of marks and spencer");

    }

}