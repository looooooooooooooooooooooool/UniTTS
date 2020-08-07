package uni.fvv.tts;

import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class UniTTS extends WXModule {

    private TextToSpeech mSpeech;
    private JSCallback messageHandle;

    private JSCallback playCallback = new JSCallback() {
        @Override
        public void invoke(Object o) {    }

        @Override
        public void invokeAndKeepAlive(Object o) { }
    };
    private JSCallback doneCallback = new JSCallback() {
        @Override
        public void invoke(Object o) {    }

        @Override
        public void invokeAndKeepAlive(Object o) { }
    };;
    private JSCallback errorCallback = new JSCallback() {
        @Override
        public void invoke(Object o) {    }

        @Override
        public void invokeAndKeepAlive(Object o) { }
    };;

    @JSMethod(uiThread = true)
    public void test(){
        Toast.makeText(mWXSDKInstance.getContext(),"test",Toast.LENGTH_SHORT).show();
    }

    //初始化tts引擎
    @JSMethod(uiThread = true)
    public void init(JSCallback jsCallback,String engine){
        messageHandle = jsCallback;

        mSpeech = new TextToSpeech(mWXSDKInstance.getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                messageHandle.invoke(i);
                mSpeech.setLanguage(Locale.ENGLISH);
                setPitch(50);
                setSpeechRate(65);
            }
        },engine);

        mSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String s) { playCallback.invokeAndKeepAlive(s); }

            @Override
            public void onDone(String s) {doneCallback.invokeAndKeepAlive(s);}

            @Override
            public void onError(String s) {errorCallback.invokeAndKeepAlive(s);}

        });
    }

    @JSMethod(uiThread = true)
    public void onStart(JSCallback jsCallback){
        playCallback = jsCallback;
    }
    @JSMethod(uiThread = true)
    public void onDone(JSCallback jsCallback){
        doneCallback = jsCallback;
    }
    @JSMethod(uiThread = true)
    public void onError(JSCallback jsCallback){
        errorCallback = jsCallback;
    }


    //获取已安装的引擎
    @JSMethod(uiThread = true)
    public void getInstallTTS(JSCallback jsCallback){
        List<TextToSpeech.EngineInfo> list =  mSpeech.getEngines();
        jsCallback.invoke(list);
    }

    //设置引擎
    @JSMethod(uiThread = false)
    public int setEngine(String engine){
        return mSpeech.setEngineByPackageName(engine);
    }


    //设置音调
    @JSMethod(uiThread = true)
    public void setPitch(int value){
        if(value > 100){
            value = 100;
        }
        if(value < 0){
            value = 0;
        }
        float tempValue = (float)(value * 2 * 0.01);
        mSpeech.setPitch(tempValue);
    }

    //设置语速
    @JSMethod(uiThread = true)
    public void setSpeechRate(int value){
        if(value > 100){
            value = 100;
        }
        if(value < 0){
            value = 0;
        }
        float tempValue = (float)(value * 1.5 * 0.01);
        mSpeech.setSpeechRate(tempValue);
    }

    //获取转换的长度限制
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @JSMethod(uiThread = false)
    public int getMaxSpeechInputLength(){
        return mSpeech.getMaxSpeechInputLength();
    }

    //检查是否正在播放
    @JSMethod(uiThread = false)
    public Boolean isSpeaking(){
        return mSpeech.isSpeaking();
    }

    //设置语言
    @JSMethod(uiThread = false)
    public int setLanguage(String lang){
        int result = -2;
        if(lang == null || lang == ""){
            lang = "ENGLISH";
        }
        lang = lang.toUpperCase();
        if (lang.equals("CANADA")) {
            result = mSpeech.setLanguage(Locale.CANADA);
        } else if (lang.equals("CANADA_FRENCH")) {
            result = mSpeech.setLanguage(Locale.CANADA_FRENCH);
        } else if (lang.equals("CHINA")) {
            result = mSpeech.setLanguage(Locale.CHINA);
        } else if (lang.equals("CHINESE")) {
            result = mSpeech.setLanguage(Locale.CHINESE);
        } else if (lang.equals("ENGLISH")) {
            result = mSpeech.setLanguage(Locale.ENGLISH);
        } else if (lang.equals("FRANCE")) {
            result = mSpeech.setLanguage(Locale.FRANCE);
        } else if (lang.equals("FRENCH")) {
            result = mSpeech.setLanguage(Locale.FRENCH);
        } else if (lang.equals("GERMAN")) {
            result = mSpeech.setLanguage(Locale.GERMAN);
        } else if (lang.equals("GERMANY")) {
            result = mSpeech.setLanguage(Locale.GERMANY);
        } else if (lang.equals("ITALIAN")) {
            result = mSpeech.setLanguage(Locale.ITALIAN);
        } else if (lang.equals("ITALY")) {
            result = mSpeech.setLanguage(Locale.ITALY);
        } else if (lang.equals("JAPAN")) {
            result = mSpeech.setLanguage(Locale.JAPAN);
        } else if (lang.equals("JAPANESE")) {
            result = mSpeech.setLanguage(Locale.JAPANESE);
        } else if (lang.equals("KOREA")) {
            result = mSpeech.setLanguage(Locale.KOREA);
        } else if (lang.equals("KOREAN")) {
            result = mSpeech.setLanguage(Locale.KOREAN);
        } else if (lang.equals("PRC")) {
            result = mSpeech.setLanguage(Locale.PRC);
        } else if (lang.equals("ROOT")) {
            result = mSpeech.setLanguage(Locale.ROOT);
        } else if (lang.equals("SIMPLIFIED_CHINESE")) {
            result = mSpeech.setLanguage(Locale.SIMPLIFIED_CHINESE);
        } else if (lang.equals("TAIWAN")) {
            result = mSpeech.setLanguage(Locale.TAIWAN);
        } else if (lang.equals("TRADITIONAL_CHINESE")) {
            result = mSpeech.setLanguage(Locale.TRADITIONAL_CHINESE);
        } else if (lang.equals("UK")) {
            result = mSpeech.setLanguage(Locale.UK);
        } else if (lang.equals("US")) {
            result = mSpeech.setLanguage(Locale.US);
        }
        return result;
    }

    //设置语言自定义
    @JSMethod(uiThread = false)
    public int setLanguageCustom(String lang,String country){
        return mSpeech.setLanguage(new Locale(lang,country));
    }

    //开始播放
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @JSMethod(uiThread = false)
    public int speak(JSONObject object){
        String text = SetValue(object,"text","");
        String queue = SetValue(object,"queue","flush");
        String id = SetValue(object,"id",String.valueOf(Math.round(Math.random() * 1000)));
        int queueType = queue.toUpperCase().equals("FLUSH")?0:1;
        return mSpeech.speak(text,queueType,null,id);
    }

    //停止
    @JSMethod(uiThread = true)
    public void stop(){
        mSpeech.stop();
    }

    //保存到文件
    @JSMethod(uiThread = true)
    public void saveAudioFile(JSONObject jsonObject){
        String text = SetValue(jsonObject,"text", "");
        String id = SetValue(jsonObject,"id",String.valueOf(Math.round(Math.random() * 1000)));
        String path = SetValue(jsonObject,"path", "/sdcard/1.wav");
        HashMap<String, String> myHashRender = new HashMap<>();
        myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,id);
        String tempPath = path.substring(0,path.lastIndexOf("/"));
        if(!isFolderExists(tempPath)){
            return ;
        }
        mSpeech.synthesizeToFile(text, myHashRender, path);
    }

    //打开tts设置页面
    @JSMethod(uiThread = true)
    public void openSettings(){
        mWXSDKInstance.getContext().startActivity(new Intent("com.android.settings.TTS_SETTINGS"));
    }

    boolean isFolderExists(String strFolder) {
        File file = new File(strFolder);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    //销毁
    @JSMethod(uiThread = true)
    public void destroy(){
        if(mSpeech != null){
            mSpeech.stop();
            mSpeech.shutdown();
            mSpeech = null;
        }
    }

    public int SetValue(JSONObject object,String key,int defaultValue){
        object = object == null?new JSONObject():object;
        return object.containsKey(key)?object.getInteger(key):defaultValue;
    }
    public String SetValue(JSONObject object,String key,String defaultValue){
        object = object == null?new JSONObject():object;
        return object.containsKey(key)?object.getString(key):defaultValue;
    }
    public Boolean SetValue(JSONObject object,String key,Boolean defaultValue){
        object = object == null?new JSONObject():object;
        return object.containsKey(key)?object.getBoolean(key):defaultValue;
    }


}
