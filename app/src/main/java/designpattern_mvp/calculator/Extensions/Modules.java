package designpattern_mvp.calculator.Extensions;

import designpattern_mvp.calculator.Presenter.ResultPresenter;
import designpattern_mvp.calculator.Presenter.ResultPresenterInterface;
import toothpick.config.Module;

public class Modules extends Module {

    public static Module getInstancesPerLifetimeScope(){
        return new Module()
            {{
                bind(ResultPresenterInterface.class).toInstance(new ResultPresenter());
            }};
    }
}
