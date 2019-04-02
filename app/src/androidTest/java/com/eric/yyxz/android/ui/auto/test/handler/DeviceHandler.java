package com.eric.yyxz.android.ui.auto.test.handler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.RemoteException;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import com.eric.yyxz.android.ui.auto.test.tools.TimeTools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class DeviceHandler {

    private static UiDevice device;
    private static Context context;

    public static void setDevice(UiDevice device){
        DeviceHandler.device = device;
    }

    public static void setContext(Context context){
        DeviceHandler.context = context;
    }

    public static UiDevice device(){
        return device;
    }

    public static class Find{
        public static UiObject2 findBy(BySelector selector){
            return device.findObject(selector);
        }

        public static UiObject2 findBy(BySelector selector, long timeout){
            long time = System.currentTimeMillis();
            UiObject2 element = null;
            while (element == null && TimeTools.isTimeout(time, timeout)){
                element = findBy(selector);
                TimeTools.sleepByMilliSeconds(100);
            }
            return element;
        }

        public static List<UiObject2> findsBy(BySelector selector){
            return device.findObjects(selector);
        }

        public static UiObject findBy(UiSelector selector){
            return device.findObject(selector);
        }

        public static UiObject2 findBy(UiObject2 element, BySelector selector){
            return element.findObject(selector);
        }

        public static List<UiObject2> findsBy(UiObject2 element, BySelector selector){
            return element.findObjects(selector);
        }
    }

    public static class Click{
        public static boolean click(UiObject2 element){
            if(element != null){
                element.click();
                return true;
            }
            return false;
        }

        public static boolean click(UiObject2 element, long duration){
            if(element != null){
                element.click(duration);
                return true;
            }
            return false;
        }

        public static boolean click(UiObject element){
            if(element != null){
                try {
                    return element.click();
                } catch (UiObjectNotFoundException ignored) {
                }
            }
            return false;
        }

        public static boolean clickAndWaitForNewWindow(UiObject element){
            if(element != null){
                try {
                    return element.clickAndWaitForNewWindow();
                } catch (UiObjectNotFoundException ignored) {}
            }
            return false;
        }

        public static boolean clickAndWaitForNewWindow(UiObject element, long timeout){
            if(element != null){
                try {
                    return element.clickAndWaitForNewWindow(timeout);
                } catch (UiObjectNotFoundException ignored) {}
            }
            return false;
        }
    }

    public static class Swipe{
        public static void swipe(int startX, int startY, int endX, int endY, int steps){
            device.swipe(startX, startY, endX, endY, steps);
        }

        public static void swipe(UiObject2 element, Direction direction, float percent){
            if(element != null){
                element.swipe(direction, percent);
            }
        }

        public static void swipe(UiObject2 element, Direction direction, float percent, int speed){
            if(element != null){
                element.swipe(direction, percent, speed);
            }
        }
    }

    public static class Drag{
        public static void drag(int startX, int startY, int endX, int endY, int steps){
            device.drag(startX, startY, endX, endY, steps);
        }

        public static void drag(UiObject2 element, Point dest){
            if(element != null){
                element.drag(dest);
            }
        }

        public static void drag(UiObject2 element, Point dest, int speed){
            if(element != null){
                element.drag(dest, speed);
            }
        }
    }

    public static class Press{
        public static void pressBack(){
            device.pressBack();
        }

        public static void pressHome(){
            device.pressHome();
        }

        public static void pressEnter(){
            device.pressEnter();
        }

        public static void pressMenu(){
            device.pressMenu();
        }

        public static void pressSearch(){
            device.pressSearch();
        }

        public static void pressRecentApps(){
            try { device.pressRecentApps();
            } catch (RemoteException ignored) {}
        }
    }

    public static class Data{
        public static String getPackageName(){
            return device.getCurrentPackageName();
        }

        public static String getLauncherPackageName(){
            return device.getLauncherPackageName();
        }

        public static String getProductName(){
            return device.getProductName();
        }

        public static int getHeight(){
            return device.getDisplayHeight();
        }

        public static int getWidth(){
            return device.getDisplayWidth();
        }

        public static Point getDisplay(){
            return device.getDisplaySizeDp();
        }

        public static String getText(){
            return device.getLastTraversedText();
        }
    }

    public static class Action{
        public static boolean open(String packageName){
            try {
                Intent intent = new Intent();
                intent.setPackage(packageName);
                context.startActivity(intent);
                long time = System.currentTimeMillis();
                boolean status = Data.getPackageName().equals(packageName);
                while (!status && TimeTools.isTimeout(time, 10 * 1000)){
                    status = Data.getPackageName().equals(packageName);
                    TimeTools.sleepByMilliSeconds(100);
                }
                return status;
            }catch (Exception e){
                return false;
            }
        }

        public static boolean open(String packageName, String activityName){
            return open(packageName, activityName, 60);
        }

        public static boolean open(String packageName, String activityName, long timeout){
            executeCommand(String.format("am start -n %s/%s", packageName, activityName));
            long time = System.currentTimeMillis();
            boolean status = Data.getPackageName().equals(packageName);
            while (!status && TimeTools.isTimeout(time, timeout * 1000)){
                status = Data.getPackageName().equals(packageName);
                TimeTools.sleepByMilliSeconds(100);
            }
            return status;
        }

        public static String openW(String packageName, String activityName){
            return openW(packageName, activityName, 60);
        }

        public static String openW(String packageName, String activityName, long timeout){
            String content = executeCommand(String.format("am start -n %s/%s", packageName, activityName));
            long time = System.currentTimeMillis();
            boolean status = Data.getPackageName().equals(packageName);
            while (!status && TimeTools.isTimeout(time, timeout * 1000)){
                status = Data.getPackageName().equals(packageName);
                TimeTools.sleepByMilliSeconds(100);
            }
            return content;
        }
    }

    public static class Permission{
        private static final List<BySelector> permissions = new ArrayList<>();
        private static Future future;

        static {
            permissions.add(By.text("确定"));
        }

        public static void run(){
            future = ThreadPoolHandler.submit(new Runnable() {
                @Override
                public void run() {
                    UiObject2 element;
                    for(BySelector bySelector : permissions){
                        element = Find.findBy(bySelector);
                        Click.click(element);
                    }
                }
            });
        }

        public static void run(final List<BySelector> permissions){
            future = ThreadPoolHandler.submit(new Runnable() {
                @Override
                public void run() {
                    UiObject2 element;
                    for(BySelector bySelector : permissions){
                        element = Find.findBy(bySelector);
                        Click.click(element);
                    }
                }
            });
        }

        public static void stop(){
            future.cancel(true);
        }

        public static boolean isCancelled(){
            return future != null && future.isCancelled();
        }

        public static boolean isDone(){
            return future != null && future.isDone();
        }
    }

    public static String executeCommand(String cmd){
        try {
            return device.executeShellCommand(cmd);
        } catch (IOException e) {
            LoggerHandler.e("ExecuteShellCommand", e);
            return null;
        }
    }

}
