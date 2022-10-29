package com.shageldi.androidlessons.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.shageldi.androidlessons.Common.Fonts;
import com.shageldi.androidlessons.Common.Utils;
import com.shageldi.androidlessons.R;

public class AppTextView extends androidx.appcompat.widget.AppCompatTextView {
    public AppTextView(Context context) {
        super(context);
    }

    public AppTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        try{
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.AppTextView,
                    0, 0);
            int type=a.getInteger(R.styleable.AppTextView_tvFont,3);
            initFont(type,context);
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void initFont(int type,Context context) {
        switch (type){
            case 0:
                this.setTypeface(Utils.getFontByName(context, Fonts.BOLD));
                break;
            case 1:
                this.setTypeface(Utils.getFontByName(context, Fonts.EXTRABOLD));
                break;
            case 2:
                this.setTypeface(Utils.getFontByName(context, Fonts.LIGHT));
                break;
            case 4:
                this.setTypeface(Utils.getFontByName(context, Fonts.SEMIBOLD));
                break;
            default:
                this.setTypeface(Utils.getFontByName(context, Fonts.REGULAR));
                break;

        }
    }


}
