package com.demon.domatorbeast.logic;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.demon.domatorbeast.data.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import io.realm.Realm;

/**
 * Created by Piotr on 2016-01-04.
 */
public class MongoReader extends AsyncTask<Void, Void, Void> {

    static String serverResponse;
    static String tmp;
    Context context;

    public MongoReader(Context context) {
        this.context = context;
    }

    private void jsonRead(JsonReader reader) throws IOException {

        Exercise mExercise = new Exercise();

        reader.beginObject();
        while (reader.hasNext()) {
            String key = reader.nextName();
            if (key.equals("id")) {
                mExercise.setId(Integer.parseInt(reader.nextString()));
            } else if (key.equals("description")) {
                mExercise.setDescription(reader.nextString());
            } else if (key.equals("name")) {
                mExercise.setName(reader.nextString());
            }else if (key.equals("series")) {
                mExercise.setSeries(reader.nextString());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.e("EXERCISE", mExercise.getSeries());
        //Realm mRealm = Realm.getDefaultInstance();
        Realm mRealm = Realm.getDefaultInstance();

        mRealm.beginTransaction();
        mRealm.copyToRealm(mExercise);
        mRealm.commitTransaction();


    }


    @Override
    protected Void doInBackground(Void... params) {

        //ExData contact = (ExData) params[0];

        try {

            QueryBuilder qb = new QueryBuilder();
            URL url = new URL(qb.buildContactsGetURL());
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() == 200) {
                InputStreamReader mInputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader mBufferedReader = new BufferedReader(mInputStreamReader);
                while ((tmp = mBufferedReader.readLine()) != null) {
                    serverResponse = tmp;
                    serverResponse = serverResponse.replace("[", "");
                    serverResponse = serverResponse.replace("]", "");
                    String[] array = serverResponse.split(", \\{");
                    JsonReader mJsonReader;
                    int counter = 0;
                    for (String s : array
                            ) {
                        if (counter == 0) {
                            counter = 1;
                            mJsonReader = new JsonReader(new StringReader(s));
                            jsonRead(mJsonReader);
                        } else {
                            s = "{ " + s;
                            mJsonReader = new JsonReader(new StringReader(s));
                            jsonRead(mJsonReader);
                            Log.e("STRING",s);
                        }


                    }


                }

                Log.e("SERV RESPONSE", serverResponse);

            }

        } catch (Exception e) {
            e.printStackTrace();
            //return false;
        }

        return null;
    }

}
