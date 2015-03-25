package com.samz.cordova.mixpanel;

import android.content.Context;
import android.text.TextUtils;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import java.util.Map;
import java.util.HashMap;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.LOG;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MixpanelPlugin extends CordovaPlugin {

    private static String LOG_TAG = "MIXPANEL PLUGIN";

    private static MixpanelAPI mixpanel;

    private enum Action {

        FLUSH("flush"),
        IDENTIFY("identify"),
//cranberrygame start
        SET("set"),
		INCREMENT("increment"),//cranberrygame
		DELETE_USER("deleteUser"),//cranberrygame
//cranberrygame end         
        INIT("init"),
        RESET("reset"),
        TRACK("track");
       
        private final String name;
        private static final Map<String, Action> lookup = new HashMap<String, Action>();

        static {
            for (Action a : Action.values())
                lookup.put(a.getName(), a);
        }

        private Action(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static Action get(String name) {
            return lookup.get(name);
        }

    }

    private void error(CallbackContext cbCtx, String message) {
        LOG.e(LOG_TAG, message);
        cbCtx.error(message);
    }


    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext cbCtx){
        // throws JSONException
        Action act = Action.get(action);
//        JSONObject properties;

        if (act == null){
            this.error(cbCtx, "unknown action");
            return false;
        }

        if (mixpanel == null && Action.INIT != act) {
            this.error(cbCtx, "you must initialize mixpanel first using \"init\" action");
            return false;
        }

        switch (act) {
            case FLUSH:
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        mixpanel.flush();
                        cbCtx.success();
                    }
                };
                cordova.getThreadPool().execute(runnable);
                cbCtx.success();
                break;

            case IDENTIFY:
                String uniqueId = args.optString(0, "");
                if (TextUtils.isEmpty(uniqueId)) {
                    this.error(cbCtx, "missing unique id");
                    return false;
                }
                //mixpanel.identify(uniqueId);//ok//cranberrygame
				mixpanel.getPeople().identify(uniqueId);//ok//cranberrygame
                cbCtx.success();
                break;
//cranberrygame start
			//http://mixpanel.github.io/mixpanel-android/com/mixpanel/android/mpmetrics/MixpanelAPI.People.html
            case SET:
				JSONObject properties = args.optJSONObject(0);
                if (properties == null) {
                    properties = new JSONObject();
                }				
				mixpanel.getPeople().set(properties);
                cbCtx.success();
                break;
            case INCREMENT:
				String name = args.optString(0, "");
				int value = args.optInt(1, 1);				
				Map<String, Integer> properties_increment = new HashMap<String, Integer>();
				properties_increment.put(name, value);
				mixpanel.getPeople().increment(properties_increment);
                cbCtx.success();
                break;
			case DELETE_USER:
				mixpanel.getPeople().deleteUser();
                cbCtx.success();
                break;
//cranberrygame end
            case INIT:
                String token = args.optString(0, "");
                if (TextUtils.isEmpty(token)) {
                    this.error(cbCtx, "missing token for mixpanel project");
                    return false;
                }
                Context ctx = cordova.getActivity();
                mixpanel = MixpanelAPI.getInstance(ctx, token);
                //people = mixpanel.getPeople();//cranberrygame
                cbCtx.success();
                break;

            case RESET:
                mixpanel.reset();
                cbCtx.success();
                break;

            case TRACK:
                String event = args.optString(0, "");
                if (TextUtils.isEmpty(event)) {
                    this.error(cbCtx, "missing event name");
                    return false;
                }
				JSONObject properties_track = args.optJSONObject(1);
                if (properties_track == null) {
                    properties_track = new JSONObject();
                }
                mixpanel.track(event, properties_track);
                cbCtx.success();
                break;

            default:
                this.error(cbCtx, "unknown action");
                return false;
                //break;
        }

        //success if got here
        return true;
    }


    @Override
    public void onDestroy() {
        if (mixpanel != null) {
            mixpanel.flush();
        }
        super.onDestroy();
    }
}
