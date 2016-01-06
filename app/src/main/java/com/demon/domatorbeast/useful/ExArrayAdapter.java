package com.demon.domatorbeast.useful;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demon.domatorbeast.R;

import java.util.ConcurrentModificationException;

/**
 * Created by Piotr on 2016-01-06.
 */
public class ExArrayAdapter extends ArrayAdapter<String> {

    private final Context mContext;
    private final String[] names;


    public ExArrayAdapter(Context context, String[] objects) {
        super(context, R.layout.list_view_main, objects);
        this.mContext = context;
        this.names = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = mLayoutInflater.inflate(R.layout.list_view_main,parent,false);
        ImageView mImageView = (ImageView) mView.findViewById(R.id.imageList);
        TextView mTextView = (TextView) mView.findViewById(R.id.textGroupName);
        TextView mTextViewGone = (TextView) mView.findViewById(R.id.textNoShowed);
        Button mButton = (Button) mView.findViewById(R.id.buttonMore);
        mTextView.setText(names[position]);
        mTextViewGone.setText("Opisby był tego wlasnie elo elo 3 dwa zero " + names[position]);
        //String name = names[position];
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, names[position],Toast.LENGTH_SHORT).show();
            }
        });
        return mView;
        //return super.getView(position, convertView, parent);
    }
}
