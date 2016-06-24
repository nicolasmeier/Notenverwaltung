package com.factbz.notenverwaltung.Filter;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by Nicolas on 24.06.2016.
 */
public class InputFilterMinMax implements InputFilter {
    private float min;
    private float max;

    public InputFilterMinMax(float min, float max){
        this.min = min;
        this.max = max;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            float input = Float.parseFloat(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
        } catch (NumberFormatException nfe) { }
        return "";
    }

    private boolean isInRange(float min, float max, float input) {
        return max > min ? input >= min && input <= max : input >= max && input <= min;
    }
}
