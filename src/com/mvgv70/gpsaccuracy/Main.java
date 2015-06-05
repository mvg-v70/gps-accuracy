package com.mvgv70.gpsaccuracy;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import android.util.Log;

public class Main implements IXposedHookLoadPackage {
	
  final static String TAG = "gpsAccuracy";
	
  @Override
  public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
    if (!lpparam.packageName.equals("com.navitel")) return;
    // TODO: XC_MethodReplacement.returnConstant(5)
    findAndHookMethod("android.location.Location", lpparam.classLoader, "getAccuracy", new XC_MethodHook() {
      
      @Override
      protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        // 5 метров
        param.setResult(5);
      }
    });
    Log.d(TAG,"navitel hook OK");
  }
}
	