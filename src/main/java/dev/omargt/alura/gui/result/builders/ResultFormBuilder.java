package dev.omargt.alura.gui.result.builders;

import java.awt.Window;

import dev.omargt.alura.gui.result.ResultForm;

public class ResultFormBuilder implements Builder<ResultFormBuilder> {

    private Window container;
    private String title;
    private String from;
    private String to;
    private String amount;
    private String result;
    private String unitRateTo;
    private String dateDescription;


    @Override
    public void reset() {
        container = null;
        title = null;
        from = null;
        to = null;
        amount = null;
        result = null;
        unitRateTo = null;
        dateDescription = null;
    }

    @Override
    public ResultFormBuilder setContainer(Window container) {
        this.container = container;
        return this;
    }

    @Override
    public ResultFormBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public ResultFormBuilder setFrom(String from) {
        this.from = from;
        return this;
    }

    @Override
    public ResultFormBuilder setTo(String to) {
        this.to = to;
        return this;
    }

    @Override
    public ResultFormBuilder setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public ResultFormBuilder setResult(String result) {
        this.result = result;
        return this;
    }

    @Override
    public ResultFormBuilder setUnitRateTo(String unitRateTo) {
        this.unitRateTo = unitRateTo;
        return this;
    }

    @Override
    public ResultFormBuilder setDateDescription(String dateDescription) {
        this.dateDescription = dateDescription;
        return this;
    }

    public ResultForm build(){
        ResultForm resultForm = new ResultForm(container, title, from, to, amount, result, unitRateTo, dateDescription);
        this.reset();
        return resultForm;
    }

	
}
