package br.com.heiderlopes.androidservice.foregroundservice;

/**
 * Created by heider on 28/11/16.
 */

public class Constantes {

    public interface ACTION {
        public static String MAIN_ACTION = "br.com.heiderlopes.androidservice.action.main";
        public static String PREV_ACTION = "br.com.heiderlopes.androidservice.action.prev";
        public static String PLAY_ACTION = "br.com.heiderlopes.androidservice.action.play";
        public static String NEXT_ACTION = "br.com.heiderlopes.androidservice.action.next";
        public static String STARTFOREGROUND_ACTION = "br.com.heiderlopes.androidservice.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "br.com.heiderlopes.androidservice.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}
