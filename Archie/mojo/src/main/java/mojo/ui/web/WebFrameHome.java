package mojo.ui.web;

import mojo.ui.*;
import mojo.ui.exception.UIException;

/** @modelguid {1214A4FB-05D1-498C-A84F-FFBA64055238} */
public class WebFrameHome {
	/** @modelguid {CBAF9C81-137C-4A37-9226-02DEC11B161B} */
	private static final WebFrameHome SINGLETON = new WebFrameHome();

	/**
	 * Insert the method's description here.
	 * Creation date: (11/15/2000 8:55:04 AM)
	 * @modelguid {BA138D04-C139-4CEF-9037-C046A4EC64A1}
	 */
	public WebFrameHome() {
		initialize();
	}

	/** @modelguid {E5AE88BD-DB62-44E3-A100-DEDBE4FE36EC} */
	private void initialize() {
	}

	/** @modelguid {C9009814-C4B3-421E-9FFA-5507D4CE2D74} */
	public IFrame getFrame(String aFrameName) throws UIException {
		try {
			IFrame lNewFrame = (IFrame)Class.forName(aFrameName).newInstance();
			return (IFrame)lNewFrame;
		} catch (ClassNotFoundException lCnfExp) {
			throw new UIException("Could not find UI Class : " + aFrameName + ", " + lCnfExp);
		} catch (InstantiationException lCnfExp) {
			throw new UIException("Could not instantiate UI Class : " + aFrameName + ", " + lCnfExp);
		} catch (IllegalAccessException lCnfExp) {
			throw new UIException("Could not access UI Class : " + aFrameName + ", " + lCnfExp);
		}
	}

	/** @modelguid {D2CB9D30-F08E-4CB6-ADBA-4BFCEE47DCC8} */
	public static WebFrameHome getSingleton() {
		return SINGLETON;
	}
}