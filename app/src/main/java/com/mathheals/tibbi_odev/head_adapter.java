package com.mathheals.tibbi_odev;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import java.util.List;

/**
 * Created by user on 22.11.2017.
 */

public class head_adapter extends ArrayAdapter<head> {
    private Activity context;
    private List<head> Bas_list;



    public head_adapter(Activity context, List<head> Bas_list)
    {
        super(context,R.layout.list_bas,Bas_list);
        this.context=context;
        this.Bas_list=Bas_list;


    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater Inflater=context.getLayoutInflater();
        View list_view=Inflater.inflate(R.layout.list_bas,null,true);

        TextView textview_tarih=(TextView)list_view.findViewById(R.id.Tarih);
        TextView textView_derece=(TextView)list_view.findViewById(R.id.Derece);
        TextView textView_sure=(TextView)list_view.findViewById(R.id.Sure);



        head bas= Bas_list.get(position);
        textview_tarih.setText(bas.getDate());
        textView_derece.setText(bas.getDerece());
        textView_sure.setText(Integer.toString(bas.getSure()));//data da integer olarak tutuyorduk stinge çevirdik
        return list_view;
    }
}

