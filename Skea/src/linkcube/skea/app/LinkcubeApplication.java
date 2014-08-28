package linkcube.skea.app;

//import me.linkcube.app.core.Timber;
//import me.linkcube.app.service.IToyServiceCall;

import linkcube.skea.service.IToyServiceCall;
import linkcube.skea.util.Timber;
import me.linkcube.app.BuildConfig;
import android.app.Application;

public class LinkcubeApplication extends Application {

	public static IToyServiceCall toyServiceCall;

	public LinkcubeApplication() {
		super();
	}

	@Override
	public void onCreate() {
		if (BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree());
		} else {
			Timber.plant(new Timber.HollowTree());
		}
	}

}
