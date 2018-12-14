package com.example.govert.trivia;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class HighScorePostRequest extends Request<JSONObject> {

    private HighScore highScore;

    public HighScorePostRequest(int method, String url, HighScore highScore, Response.ErrorListener listener) {
        super(method, url, listener);
        this.highScore = highScore;
    }

    protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("nickname", highScore.getNickName());
        params.put("score", Integer.toString(highScore.getScore()));
        return params;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        // auto generated method stub
    }
}


