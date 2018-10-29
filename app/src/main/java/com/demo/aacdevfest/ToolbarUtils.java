package com.demo.aacdevfest;

/**
 * Created by Darshan on 27-10-2018.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */
public class ToolbarUtils {

    public static void showToolbar(MainActivity mainActivity) {
        if(mainActivity.getSupportActionBar() == null) return;

        mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}
