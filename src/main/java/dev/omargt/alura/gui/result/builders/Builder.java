package dev.omargt.alura.gui.result.builders;

import java.awt.Window;

public interface Builder<T> {

    void reset();
    T setContainer(Window container);
    T setTitle(String title);
    T setFrom(String from);
    T setTo(String to);
    T setAmount(String amount);
    T setResult(String result);
    T setUnitRateTo(String unitRateTo);
    T setDateDescription(String dateDescription);

}
